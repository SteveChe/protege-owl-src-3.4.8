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

package edu.stanford.smi.protegex.owl.swrl.ui.actions;

import java.util.logging.Level;

import edu.stanford.smi.protege.model.WidgetDescriptor;
import edu.stanford.smi.protege.ui.ProjectManager;
import edu.stanford.smi.protege.ui.ProjectView;
import edu.stanford.smi.protege.util.Log;
import edu.stanford.smi.protege.util.ModalDialog;
import edu.stanford.smi.protege.widget.TabWidget;
import edu.stanford.smi.protegex.owl.model.OWLModel;
import edu.stanford.smi.protegex.owl.swrl.ui.icons.SWRLIcons;
import edu.stanford.smi.protegex.owl.swrl.ui.tab.SWRLTab;
import edu.stanford.smi.protegex.owl.ui.actions.AbstractOWLModelAction;
import edu.stanford.smi.protegex.owl.ui.actions.OWLModelActionConstants;

public class EnableSWRLTabAction extends AbstractOWLModelAction {

    private static final String SWRL_TAB_JAVA_CLASS_NAME = SWRLTab.class.getName();
    
	public final static String GROUP = OWLModelActionConstants.QUERY_GROUP;


    public String getIconFileName() {
        return "SWRLImps";
    }

    @Override
    public Class<?> getIconResourceClass() {    
    	return SWRLIcons.class;
    }

    public String getMenubarPath() {
        return REASONING_MENU + PATH_SEPARATOR + GROUP;
    }


    public String getName() {
        return "Open SWRL Tab";
    }


    public boolean isSuitable(OWLModel owlModel) {
    	return true;
    }
	

	public void run(OWLModel owlModel) {
		try {
			ProjectView prjView = ProjectManager.getProjectManager().getCurrentProjectView();
			TabWidget tabWidget = prjView.getTabByClassName(SWRL_TAB_JAVA_CLASS_NAME);
			
			if (tabWidget != null) {
				prjView.setSelectedTab(tabWidget);
				return;
			}
			
			WidgetDescriptor d = owlModel.getProject().getTabWidgetDescriptor(SWRL_TAB_JAVA_CLASS_NAME);
			d.setVisible(true);
						
			prjView.addTab(d);
			tabWidget = prjView.getTabByClassName(SWRL_TAB_JAVA_CLASS_NAME);
			prjView.setSelectedTab(tabWidget);
			
		} catch (Exception e) {
			Log.getLogger().log(Level.WARNING, "Cannot enable SWRL Tab", e);
			ModalDialog.showMessageDialog(ProjectManager.getProjectManager().getCurrentProjectView(), "Cannot enable SWRLTab. See console for more details.");
		}		
	}

}
