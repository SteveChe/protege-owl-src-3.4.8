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

package edu.stanford.smi.protegex.owl.testing.sanity;

import edu.stanford.smi.protege.model.Slot;
import edu.stanford.smi.protegex.owl.model.OWLObjectProperty;
import edu.stanford.smi.protegex.owl.model.OWLProperty;
import edu.stanford.smi.protegex.owl.model.RDFProperty;
import edu.stanford.smi.protegex.owl.testing.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * @author Holger Knublauch  <holger@knublauch.com>
 */
public class InverseOfTopLevelPropertyMustBeTopLevelPropertyTest extends AbstractOWLTest
        implements RDFPropertyTest, AutoRepairableOWLTest {

    public InverseOfTopLevelPropertyMustBeTopLevelPropertyTest() {
        super(SANITY_GROUP, null);
    }


    public static boolean fails(RDFProperty property) {
        if (property instanceof OWLObjectProperty) {
            Slot inverseSlot = property.getInverseProperty();
            return inverseSlot != null &&
                    property.getSuperpropertyCount() == 0 &&
                    inverseSlot.getDirectSuperslotCount() > 0;
        }
        return false;
    }


    public boolean repair(OWLTestResult testResult) {
        return repair((OWLProperty) testResult.getHost());
    }


    public static boolean repair(OWLProperty property) {
        if (property instanceof OWLObjectProperty) {
            Slot inverseSlot = property.getInverseProperty();
            if (inverseSlot != null) {
                for (Iterator it = new ArrayList(inverseSlot.getDirectSuperslots()).iterator(); it.hasNext();) {
                    Slot superSlot = (Slot) it.next();
                    inverseSlot.removeDirectSuperslot(superSlot);
                }
                return !fails(property);
            }
        }
        return false;
    }


    public List test(RDFProperty property) {
        if (fails(property)) {
            return Collections.singletonList(new DefaultOWLTestResult("The inverse of a top-level property should also be top-level property.",
                    property,
                    OWLTestResult.TYPE_WARNING,
                    this));
        }
        else {
            return Collections.EMPTY_LIST;
        }
    }
}
