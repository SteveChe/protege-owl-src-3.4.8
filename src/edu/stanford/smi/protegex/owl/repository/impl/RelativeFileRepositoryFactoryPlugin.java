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

package edu.stanford.smi.protegex.owl.repository.impl;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.logging.Level;

import edu.stanford.smi.protege.util.Log;
import edu.stanford.smi.protege.util.URIUtilities;
import edu.stanford.smi.protegex.owl.jena.JenaKnowledgeBaseFactory;
import edu.stanford.smi.protegex.owl.model.OWLModel;
import edu.stanford.smi.protegex.owl.repository.Repository;
import edu.stanford.smi.protegex.owl.repository.factory.RepositoryFactoryPlugin;
import edu.stanford.smi.protegex.owl.repository.util.RepositoryUtil;

/**
 * User: matthewhorridge<br>
 * The University Of Manchester<br>
 * Medical Informatics Group<br>
 * Date: Sep 22, 2005<br><br>
 * <p/>
 * matthew.horridge@cs.man.ac.uk<br>
 * www.cs.man.ac.uk/~horridgm<br><br>
 */
public class RelativeFileRepositoryFactoryPlugin implements RepositoryFactoryPlugin {


    public boolean isSuitable(OWLModel model, String s) {
		if (model.getProject() != null) {

			URI owlFileUri = model.getProject().getProjectURI();

			if (owlFileUri == null && model.getKnowledgeBaseFactory() instanceof JenaKnowledgeBaseFactory) {
				String uriString = JenaKnowledgeBaseFactory.getOWLFilePath(model.getProject().getSources());
				owlFileUri = URIUtilities.createURI(uriString);
			}

			if (owlFileUri != null) {
				try {
					URI uri = new URI(s.trim());
					if (uri.isAbsolute() == false) {
						File file = RepositoryUtil.getRepositoryFileFromRelativePath(model, RepositoryUtil.stripQuery(s));
						return (file != null);
					} else {
						return false;
					}
				} catch (URISyntaxException e) {
					return false;
				}
			}
		}
		return false;
	}
  
    
    public Repository createRepository(OWLModel model, String s) {
		URI owlFileUri = model.getProject().getProjectURI();

		if (owlFileUri == null
				&& model.getKnowledgeBaseFactory() instanceof JenaKnowledgeBaseFactory) {
			String uriString = JenaKnowledgeBaseFactory.getOWLFilePath(model.getProject().getSources());
			owlFileUri = URIUtilities.createURI(uriString);
		}

		if (owlFileUri == null) {
			return null;
		}
		File file = RepositoryUtil.getRepositoryFileFromRelativePath(model, s);			
		if (file != null) {			
			try {
				return new RelativeFileRepository(file, owlFileUri.toURL(), s.trim());
			} catch (MalformedURLException e) {
				return null;
			} catch (URISyntaxException e) {
				return null;			
			}
		}
		return null;
	}
    
    
 
}

