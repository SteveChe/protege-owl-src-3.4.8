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


package edu.stanford.smi.protegex.owl.swrl.portability.p3;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import edu.stanford.smi.protegex.owl.model.NamespaceManager;
import edu.stanford.smi.protegex.owl.swrl.portability.OWLOntology;
import edu.stanford.smi.protegex.owl.swrl.portability.PrefixManager;

public class P3PrefixManager implements PrefixManager
{
	private NamespaceManager namespaceManager;

	public P3PrefixManager(OWLOntology activeOntology)
	{
		namespaceManager = activeOntology.getOWLModel().getNamespaceManager();
	}

	public String getDefaultPrefix()
	{
		return namespaceManager.getPrefix(namespaceManager.getDefaultNamespace());
	}

	public boolean containsPrefixMapping(String prefixName)
	{
		return namespaceManager.getPrefix(prefixName) != null;
	}

	public String getPrefix(String prefixName)
	{
		return namespaceManager.getPrefix(prefixName);
	}

	public Set<String> getPrefixNames()
	{
		return new HashSet<String>(namespaceManager.getPrefixes());
	}

	public Map<String, String> getPrefixName2PrefixMap()
	{
		Map<String, String> result = new HashMap<String, String>();

		for (String prefix : namespaceManager.getPrefixes())
			result.put(prefix, namespaceManager.getNamespaceForPrefix(prefix));

		return result;
	}
}
