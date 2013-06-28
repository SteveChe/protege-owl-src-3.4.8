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


package edu.stanford.smi.protegex.owl.swrl.bridge.ui;

import java.awt.Container;

import edu.stanford.smi.protegex.owl.model.OWLModel;

/**
 * This interface must be implemented by a plugin to provide a mechanism to create screen real estate on the SWTLTab for the plugin's GUI. A plugin
 * registers itself with the bridge using the registerPlugin method of the BridgePluginManager class. In addition to a plugin display name,
 * a tool tip string, and an icon, this method is expecting an instance of a class that implements this interface. The plugin manager uses
 * this implementation to get the GUI for the plugin, which will be displayed in the SWRLTab when the plugin is activated. <p>
 *
 * The createPluginContainer method is called once when the plugin is registered and is expecting a Java Swing component that is a subclass of a
 * Java AWT Container class. The getPluginContainer may be called repeatedly after registration and should return the Container instance
 * created on initialization.
 *
 * See <a href="http://protege.cim3.net/cgi-bin/wiki.pl?SWRLRuleEngineBridgeFAQ#nid6QJ">here</a> for a discussion on using this inferface.
 */
public interface SWRLPluginGUIAdapter
{
  Container createPluginContainer(OWLModel owlModel, String pluginName, String ruleEngineName);
  Container getPluginContainer();
}
