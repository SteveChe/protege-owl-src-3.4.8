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

package edu.stanford.smi.protegex.owl.ui.testing;

import edu.stanford.smi.protegex.owl.model.OWLModel;
import edu.stanford.smi.protegex.owl.model.Task;
import edu.stanford.smi.protegex.owl.testing.OWLTest;
import edu.stanford.smi.protegex.owl.testing.OWLTestLibrary;
import edu.stanford.smi.protegex.owl.testing.RDFResourceTest;
import edu.stanford.smi.protegex.owl.testing.todo.TodoAnnotationOWLTest;
import edu.stanford.smi.protegex.owl.ui.icons.OWLIcons;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Holger Knublauch  <holger@knublauch.com>
 */
public class ShowTodoListAction extends AbstractOWLModelTestAction {

    public String getIconFileName() {
        return OWLIcons.TODO;
    }


    public String getName() {
        return "Show TODO list...";
    }


    protected OWLTest[] getOWLTests() {
        return new OWLTest[]{
                OWLTestLibrary.getOWLTest(TodoAnnotationOWLTest.class)
        };
    }


    protected OWLTestResultsPanel getTestResultsPanel(OWLModel owlModel, List results) {
        return new OWLTestResultsPanel(owlModel, results, null, false) {
            public String getTabName() {
                return "TODO List";
            }


            public Icon getIcon() {
                return OWLIcons.getTODOIcon();
            }
        };
    }


    protected List run(OWLTest[] tests, Task task) {
        List results = new ArrayList();
        runOWLInstanceTest(results, (RDFResourceTest) tests[0]);
        return results;
    }


    protected void showAllTestsPassedMessage(int count) {
        // JOptionPane.showMessageDialog(null, "No to-do list items found.");
    }
}
