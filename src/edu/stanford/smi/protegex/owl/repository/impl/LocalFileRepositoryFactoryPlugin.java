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

import edu.stanford.smi.protegex.owl.model.OWLModel;
import edu.stanford.smi.protegex.owl.repository.Repository;
import edu.stanford.smi.protegex.owl.repository.factory.RepositoryFactoryPlugin;
import edu.stanford.smi.protegex.owl.repository.util.RepositoryUtil;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * User: matthewhorridge<br>
 * The University Of Manchester<br>
 * Medical Informatics Group<br>
 * Date: Sep 22, 2005<br><br>
 * <p/>
 * matthew.horridge@cs.man.ac.uk<br>
 * www.cs.man.ac.uk/~horridgm<br><br>
 */
public class LocalFileRepositoryFactoryPlugin implements RepositoryFactoryPlugin {
    public final static String FILE_PREFIX = "file:";
    
    public boolean isSuitable(OWLModel model, String s) {
        return getRepositoryFile(model, s) != null;
    }


    public Repository createRepository(OWLModel model, String s) {
        try {
            URI u = new URI(s);
            return new LocalFileRepository(getRepositoryFile(model, s), 
                                           RepositoryUtil.isForcedToBeReadOnly(u.getQuery()));
        }
        catch (URISyntaxException e) {
            return null;
        }
    }
    
    private File getRepositoryFile(OWLModel model, String s) {
        if (s.trim().startsWith(FILE_PREFIX)) {
            try {
                File f = new File(new URI(s).getPath());
                if (f.isFile() && f.canRead()) {
                    return f;
                }
            }
            catch (Throwable t) {
                return null;
            }
        }
        return null;
    }
}

