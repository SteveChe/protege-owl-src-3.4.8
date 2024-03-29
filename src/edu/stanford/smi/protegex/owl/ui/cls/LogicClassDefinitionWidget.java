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

package edu.stanford.smi.protegex.owl.ui.cls;

import edu.stanford.smi.protege.model.Model;
import edu.stanford.smi.protegex.owl.ui.clsproperties.PropertyRestrictionsTreeWidget;
import edu.stanford.smi.protegex.owl.ui.conditions.ConditionsWidget;

import javax.swing.*;
import java.awt.*;

/**
 * @author Holger Knublauch  <holger@knublauch.com>
 */
public class LogicClassDefinitionWidget extends AbstractClassDefinitionWidget {

    private ConditionsWidget conditionsWidget;

    private PropertyRestrictionsTreeWidget clsPropertiesWidget;

    protected void createNestedWidgets() {

        conditionsWidget = new ConditionsWidget();
        addNestedWidget(conditionsWidget, Model.Slot.DIRECT_SUPERCLASSES,
                "Conditions", "Conditions");

//        clsPropertiesWidget = new PropertyRestrictionsTreeWidget();
//        addNestedWidget(clsPropertiesWidget, Model.Slot.DIRECT_TEMPLATE_SLOTS,
//                "Properties", "Properties");

        super.createNestedWidgets();
    }

    protected void initAllPanel(JPanel allPanel, java.util.List widgets) {

//        JSplitPane bottomPanel = new MySplitPane(JSplitPane.HORIZONTAL_SPLIT,
//              disjointClassesWidget  , assertedSuperClassesWidget, 0.5);
        JSplitPane mainPanel = new MySplitPane(JSplitPane.VERTICAL_SPLIT,
                conditionsWidget, disjointClassesWidget, 0.75);
//                conditionsWidget, rightPanel, 0.65);

        allPanel.setLayout(new BorderLayout());
        allPanel.add(BorderLayout.CENTER, mainPanel);
    }
}
