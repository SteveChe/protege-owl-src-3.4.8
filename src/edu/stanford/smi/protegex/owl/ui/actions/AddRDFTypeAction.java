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

package edu.stanford.smi.protegex.owl.ui.actions;

import edu.stanford.smi.protegex.owl.model.RDFIndividual;
import edu.stanford.smi.protegex.owl.model.RDFResource;
import edu.stanford.smi.protegex.owl.model.RDFSClass;
import edu.stanford.smi.protegex.owl.ui.ProtegeUI;
import edu.stanford.smi.protegex.owl.ui.icons.OWLIcons;

import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * @author Holger Knublauch  <holger@knublauch.com>
 */
public class AddRDFTypeAction extends ResourceAction {

    public final static String GROUP = "rdf:types";


    public AddRDFTypeAction() {
        super("Add rdf:type...",
                OWLIcons.getAddIcon(OWLIcons.PRIMITIVE_OWL_CLASS), GROUP);
    }


    public void actionPerformed(ActionEvent e) {
        RDFSClass type = ProtegeUI.getSelectionDialogFactory().selectClass(getComponent(), getOWLModel(), "Select an additional type...");
        if (type != null && !getResource().hasProtegeType(type)) {
            getResource().addProtegeType(type);
        }
    }


    public boolean isSuitable(Component component, RDFResource resource) {
        return resource instanceof RDFIndividual && resource.isEditable();
    }
}
