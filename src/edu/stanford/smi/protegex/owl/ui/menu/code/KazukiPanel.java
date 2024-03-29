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

package edu.stanford.smi.protegex.owl.ui.menu.code;

import edu.stanford.smi.protege.resource.Icons;
import edu.stanford.smi.protege.util.LabeledComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

/**
 * @author Holger Knublauch  <holger@knublauch.com>
 */
public class KazukiPanel extends JPanel {

    private JFileChooser fileChooser = new JFileChooser(".");

    private JTextField javaCTextField;

    private JCheckBox overwriteCheckBox;

    private JTextField packageTextField;

    private JTextField rootFolderTextField;


    public KazukiPanel() {
        packageTextField = new JTextField();
        rootFolderTextField = new JTextField("kazuki");
        javaCTextField = new JTextField();

        fileChooser.setDialogTitle("Select output folder");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        overwriteCheckBox = new JCheckBox("Overwrite all files");

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        LabeledComponent lc = new LabeledComponent("Root output folder", rootFolderTextField);
        lc.addHeaderButton(new AbstractAction("Select folder...", Icons.getAddIcon()) {
            public void actionPerformed(ActionEvent e) {
                selectFolder();
            }
        });
        add(lc);
        add(Box.createVerticalStrut(8));
        add(new LabeledComponent("Base Java package", packageTextField));
        add(Box.createVerticalStrut(8));
        add(new LabeledComponent("Java Compiler binary", javaCTextField));
        add(Box.createVerticalStrut(8));
        overwriteCheckBox.setPreferredSize(new Dimension(400, 24));
        add(overwriteCheckBox);
    }


    public String getJavaC() {
        return javaCTextField.getText();
    }


    public String getPackage() {
        return packageTextField.getText();
    }


    public String getRootFolder() {
        return rootFolderTextField.getText();
    }


    public boolean isOverwriteMode() {
        return overwriteCheckBox.isSelected();
    }


    private void selectFolder() {
        if (fileChooser.showDialog(this, "Select") == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            rootFolderTextField.setText(file.toString());
        }
    }


    public void setJavaC(String javac) {
        javaCTextField.setText(javac);
    }


    public void setOverwriteMode(boolean value) {
        overwriteCheckBox.setSelected(value);
    }


    public void setPackage(String packageName) {
        packageTextField.setText(packageName);
    }


    public void setRootFolder(String folder) {
        rootFolderTextField.setText(folder);
    }
}
