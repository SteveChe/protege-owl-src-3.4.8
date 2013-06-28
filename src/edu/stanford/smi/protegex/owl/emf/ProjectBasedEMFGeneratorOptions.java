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

package edu.stanford.smi.protegex.owl.emf;

import edu.stanford.smi.protegex.owl.model.OWLModel;
import edu.stanford.smi.protegex.owl.model.project.OWLProject;

import java.io.File;

/**
 * @author Holger Knublauch  <holger@knublauch.com>
 */
public class ProjectBasedEMFGeneratorOptions implements EditableEMFGeneratorOptions {

    public final static String FILE_NAME = "EMFFileName";

    public final static String PACKAGE = "EMFPackage";

    private OWLProject project;


    public ProjectBasedEMFGeneratorOptions(OWLModel owlModel) {
        this.project = owlModel.getOWLProject();
    }


    public File getOutputFolder() {
        String fileName = project.getSettingsMap().getString(FILE_NAME);
        if (fileName == null) {
            return new File("");
        }
        else {
            return new File(fileName);
        }
    }


    public String getPackage() {
        return project.getSettingsMap().getString(PACKAGE);
    }


    public void setOutputFolder(File file) {
        if (file == null) {
            project.getSettingsMap().remove(FILE_NAME);
        }
        else {
            project.getSettingsMap().setString(FILE_NAME, file.getAbsolutePath());
        }
    }


    public void setPackage(String value) {
        if (value == null) {
            project.getSettingsMap().remove(PACKAGE);
        }
        else {
            project.getSettingsMap().setString(PACKAGE, value);
        }
    }
}
