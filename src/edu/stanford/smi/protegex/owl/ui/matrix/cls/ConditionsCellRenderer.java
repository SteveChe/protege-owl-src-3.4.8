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

package edu.stanford.smi.protegex.owl.ui.matrix.cls;

import edu.stanford.smi.protege.model.Cls;
import edu.stanford.smi.protege.ui.FrameRenderer;
import edu.stanford.smi.protegex.owl.model.OWLNamedClass;
import edu.stanford.smi.protegex.owl.ui.icons.OWLIcons;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * @author Holger Knublauch  <holger@knublauch.com>
 */
public class ConditionsCellRenderer extends FrameRenderer {


    private Collection getDirectSuperclasses(Cls cls) {
        if (cls instanceof OWLNamedClass) {
            OWLNamedClass namedCls = (OWLNamedClass) cls;
            List results = new ArrayList();
            results.addAll(namedCls.getEquivalentClasses());
            results.addAll(namedCls.getPureSuperclasses());
            return results;
        }
        else {
            return cls.getDirectSuperclasses();
        }
    }


    protected void loadCls(Cls cls) {

        Collection cs = getDirectSuperclasses(cls);

        setGrayedSecondaryText(false);
        String previousIconName = null;
        for (Iterator it = cs.iterator(); it.hasNext();) {
            Cls superCls = (Cls) it.next();
            String nextIconName = null;
            if (superCls.hasDirectSuperclass(cls)) {
                nextIconName = "OWLEquivalentClass";
            }
            else {
                nextIconName = "Superclass";
            }
            if (!nextIconName.equals(previousIconName)) {
                addIcon(OWLIcons.getImageIcon(nextIconName));
                previousIconName = nextIconName;
            }
            String str = " " + superCls.getBrowserText();
            if (it.hasNext()) {
                str += "    ";
            }
            setGrayedText(false);
            addText(str);
        }
    }
}
