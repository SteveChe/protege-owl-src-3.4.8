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

package edu.stanford.smi.protegex.owl.swrl.ui.subtab;

import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import edu.stanford.smi.protegex.owl.model.OWLModel;
import edu.stanford.smi.protegex.owl.swrl.SWRLRuleEngine;
import edu.stanford.smi.protegex.owl.swrl.bridge.SWRLRuleEngineFactory;
import edu.stanford.smi.protegex.owl.swrl.bridge.ui.SWRLPluginGUIAdapter;
import edu.stanford.smi.protegex.owl.swrl.exceptions.SWRLRuleEngineException;
import edu.stanford.smi.protegex.owl.swrl.ui.icons.SWRLIcons;

public abstract class TargetRuleEngineSubTab extends JTabbedPane implements SWRLPluginGUIAdapter
{
  private SWRLRuleEngine ruleEngine;

  public Container getPluginContainer() { return this; }
  
  public Container createPluginContainer(OWLModel owlModel, String pluginName, String ruleEngineName)
  {
    try {
      ruleEngine = createRuleEngine(owlModel, pluginName);
    } catch (SWRLRuleEngineException e) {
      System.err.println(e.toString());
      return makeErrorWindow(e.toString());
    } // try

    removeAll();

    ControlPanel controlPanel = new ControlPanel(ruleEngine, pluginName, ruleEngineName);
    addTab(pluginName, getImpsIcon(), controlPanel, "Control Tab");

    RulesPanel rulesPanel = new RulesPanel(ruleEngine);
    addTab("Rules", getImpsIcon(), rulesPanel, "Rules Tab");

    ImportedClassDeclarationsPanel importedClassesPanel = new ImportedClassDeclarationsPanel(ruleEngine);
    addTab("Classes", getImpsIcon(), importedClassesPanel, "Imported OWL Class Declarations Tab");

    ImportedIndividualDeclarationsPanel importedIndividualsPanel = new ImportedIndividualDeclarationsPanel(ruleEngine);
    addTab("Individuals", getImpsIcon(), importedIndividualsPanel, "Imported OWL Individual Declarations Tab");

    ImportedAxiomsPanel importedRestrictionsPanel = new ImportedAxiomsPanel(ruleEngine);
    addTab("Axioms", getImpsIcon(), importedRestrictionsPanel, "Imported OWL Axioms Tab");

    InferredAxiomsPanel inferredAxiomsPanel = new InferredAxiomsPanel(ruleEngine);
    addTab("Inferred Axioms", getImpsIcon(), inferredAxiomsPanel, "Inferred OWL Axioms Tab");

    return this;
  }

  private SWRLRuleEngine createRuleEngine(OWLModel owlModel, String pluginName) throws SWRLRuleEngineException
  {
    return SWRLRuleEngineFactory.create(pluginName, owlModel);
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

  private Icon getImpsIcon() { return SWRLIcons.getImpsIcon(); }
}
