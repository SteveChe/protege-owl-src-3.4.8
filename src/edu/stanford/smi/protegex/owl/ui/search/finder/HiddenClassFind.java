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

package edu.stanford.smi.protegex.owl.ui.search.finder;

import edu.stanford.smi.protege.model.Cls;
import edu.stanford.smi.protege.model.Frame;
import edu.stanford.smi.protegex.owl.model.OWLModel;

/**
 * @author Nick Drummond, Medical Informatics Group, University of Manchester
 *         24-Feb-2006
 */
public class HiddenClassFind extends ResultsViewModelFind {

    public HiddenClassFind(OWLModel owlModel, int type) {
        super(owlModel, type);
    }

    protected boolean isValidFrameToSearch(Frame f) {
        return f instanceof Cls && !f.isVisible();
    }

    public String getDescription() {
        return "Find Hidden Class";
    }
}
