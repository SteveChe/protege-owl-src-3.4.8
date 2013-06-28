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


package edu.stanford.smi.protegex.owl.swrl.sqwrl.ui;

import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import edu.stanford.smi.protegex.owl.model.OWLModel;
import edu.stanford.smi.protegex.owl.swrl.bridge.BridgePluginManager;
import edu.stanford.smi.protegex.owl.swrl.bridge.ui.SWRLPluginGUIAdapter;
import edu.stanford.smi.protegex.owl.swrl.exceptions.SWRLRuleEngineException;
import edu.stanford.smi.protegex.owl.swrl.sqwrl.SQWRLQueryEngine;
import edu.stanford.smi.protegex.owl.swrl.sqwrl.SQWRLQueryEngineFactory;
import edu.stanford.smi.protegex.owl.swrl.sqwrl.ui.icons.QueryIcons;

public class SQWRLQueryTab extends JTabbedPane implements SWRLPluginGUIAdapter
{
  static {
    // Register this plugin with the plugin manager so that the SWRL tab can activate and deactivate it.
    BridgePluginManager.registerPlugin("SQWRLQueryTab", "Jess", "Activate/deactivate SQWRLQueryTab", QueryIcons.getQueryIcon(), new SQWRLQueryTab());
  } // static

  private SQWRLQueryEngine queryEngine;

  public Container getPluginContainer() { return this; }

  public Container createPluginContainer(OWLModel owlModel, String pluginName, String ruleEngineName)
  {
    try {
      queryEngine = SQWRLQueryEngineFactory.create(owlModel);
    } catch (SWRLRuleEngineException e) {
      System.err.println(e.toString());
      return makeErrorWindow(e.toString());
    } // try

    removeAll();

    SQWRLQueryControlPanel controlPanel = new SQWRLQueryControlPanel(queryEngine);
    addTab("SQWRLQueryTab", QueryIcons.getQueryIcon(), controlPanel, "Control Panel");

    return this;
  } 

  private Container makeErrorWindow(String text) 
  { 
    removeAll();
    JPanel panel = new JPanel(false); 
    JLabel filler = new JLabel(text); 
    filler.setHorizontalAlignment(JLabel.CENTER); 
    panel.setLayout(new GridLayout(1, 1)); 
    panel.add(filler); 
    
    add(panel);

    return this;
  } 
}
