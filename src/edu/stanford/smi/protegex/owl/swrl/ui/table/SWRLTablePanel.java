/*
 * The contents of this file are subject to the Mozilla Public License
 * Version 1.1 (the "License");  you may not use this file except in 
 * compliance with the License.  You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for
 * the specific language governing rights and limitations under the License.
 *
 * The Original Code is Protege-2000.
 *
 * The Initial Developer of the Original Code is Stanford University. Portions
 * created by Stanford University are Copyright (C) 2007.  All Rights Reserved.
 *
 * Protege was developed by Stanford Medical Informatics
 * (http://www.smi.stanford.edu) at the Stanford University School of Medicine
 * with support from the National Library of Medicine, the National Science
 * Foundation, and the Defense Advanced Research Projects Agency.  Current
 * information about Protege can be obtained at http://protege.stanford.edu.
 *
 */

package edu.stanford.smi.protegex.owl.swrl.ui.table;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;

import edu.stanford.smi.protege.util.Disposable;
import edu.stanford.smi.protege.util.LabeledComponent;
import edu.stanford.smi.protegex.owl.model.OWLModel;
import edu.stanford.smi.protegex.owl.model.RDFResource;
import edu.stanford.smi.protegex.owl.swrl.bridge.BridgePluginManager;
import edu.stanford.smi.protegex.owl.swrl.bridge.ui.ViewPluginAction;
import edu.stanford.smi.protegex.owl.swrl.ui.tab.SWRLTab;
import edu.stanford.smi.protegex.owl.ui.OWLLabeledComponent;

/**
 * A JPanel consisting of a SWRLTable and buttons to create and delete rules. It may have buttons to activate/deactivate any registered rule
 * engined.
 */
public class SWRLTablePanel extends JPanel implements Disposable 
{
  private SWRLTable table;
  private SWRLTableModel tableModel;

  public SWRLTablePanel(OWLModel owlModel, RDFResource resource)  
  {
    initialize(owlModel, resource);
  }

  public SWRLTablePanel(OWLModel owlModel, RDFResource resource, SWRLTab swrlTab) 
  {
    LabeledComponent lc = initialize(owlModel, resource);

    // Iterate through all registered rule engine and add an enable button for each one.
    for (BridgePluginManager.PluginRegistration registration : BridgePluginManager.getRegisteredPlugins()) {
      lc.addHeaderButton(new ViewPluginAction(registration.getPluginName(), registration.getToolTip(), 
                                              registration.getIcon(), swrlTab, owlModel));
      add(BorderLayout.CENTER, lc);
    } // for
  }

  public void dispose() { if (table != null) table.dispose(); }

  private LabeledComponent initialize(OWLModel owlModel, RDFResource RDFResource) 
  {
    tableModel = RDFResource == null ? new SWRLTableModel(owlModel) : new SWRLTableModel(RDFResource);
    table = new SWRLTable(tableModel, owlModel);
   
    /* Start of test group
  	OWLDataFactory owlFactory = new OWLDataFactoryImpl(owlModel);
  	SWRLRuleGroupTreeTableModel model = null;
  	
  	try {
  	  model = new SWRLRuleGroupTreeTableModel(owlFactory);
  	} catch (OWLFactoryException e) {
  		ProtegeUI.getModalDialogFactory().showErrorMessageDialog(owlModel, "Could not activate SWRLTab: " + e +
  															     "\n. Your project might be in an inconsistent state now.");
      Log.getLogger().log(Level.SEVERE, "Exception caught", e);
  	}
  	
  	SWRLRuleGroupTreeTable table = new SWRLRuleGroupTreeTable(model);
    */ // End of test group
  	
    JScrollPane scrollPane = new JScrollPane(table);
    JViewport viewPort = scrollPane.getViewport();
    viewPort.setBackground(table.getBackground());
    
    LabeledComponent lc = new OWLLabeledComponent("SWRL Rules", scrollPane);
    lc.addHeaderButton(new ViewRuleAction(table));
    lc.addHeaderButton(new CreateRuleAction(table, owlModel));
    lc.addHeaderButton(new CloneRuleAction(table, owlModel));
    lc.addHeaderButton(new DeleteRuleAction(table));
    
    setLayout(new BorderLayout());
    add(BorderLayout.CENTER, lc);
    
    return lc;
  } 
} 
