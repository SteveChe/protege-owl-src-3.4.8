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

package edu.stanford.smi.protegex.owl.testing.owldl;

import edu.stanford.smi.protege.model.Cls;
import edu.stanford.smi.protege.model.Instance;
import edu.stanford.smi.protege.model.Slot;
import edu.stanford.smi.protegex.owl.model.OWLEnumeratedClass;
import edu.stanford.smi.protegex.owl.model.RDFSClass;
import edu.stanford.smi.protegex.owl.testing.*;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * @author Holger Knublauch  <holger@knublauch.com>
 */
public class NoClassesOrPropertiesInEnumerationsOWLDLTest extends AbstractOWLTest implements OWLDLTest, RDFSClassTest {

    public NoClassesOrPropertiesInEnumerationsOWLDLTest() {
        super(GROUP, null);
    }


    public static boolean fails(RDFSClass aClass) {
        if (aClass instanceof OWLEnumeratedClass) {
            for (Iterator it = aClass.getInstances(false).iterator(); it.hasNext();) {
                Instance instance = (Instance) it.next();
                if (instance instanceof Cls || instance instanceof Slot) {
                    return true;
                }
            }
        }
        return false;
    }


    public List test(RDFSClass aClass) {
        if (fails(aClass)) {
            return Collections.singletonList(new DefaultOWLTestResult("Enumerations cannot contain classes or properties in OWL DL.",
                    aClass,
                    OWLTestResult.TYPE_OWL_FULL,
                    this));
        }
        else {
            return Collections.EMPTY_LIST;
        }
    }
}
