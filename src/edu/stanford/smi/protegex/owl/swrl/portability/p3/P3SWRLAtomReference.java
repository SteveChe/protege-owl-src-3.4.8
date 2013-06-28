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

import java.util.HashSet;
import java.util.Set;

import edu.stanford.smi.protegex.owl.swrl.portability.SWRLAtomReference;

/**
 * Base class representing information about atoms in a SWRL rule
 */
public abstract class P3SWRLAtomReference implements SWRLAtomReference
{
	private Set<String> referencedClassURIs;
	private Set<String> referencedPropertyURIs;
	private Set<String> referencedIndividualURIs;
	private Set<String> referencedVariableNames; // We need to know ordering of variables

	public P3SWRLAtomReference()
	{
		referencedClassURIs = new HashSet<String>();
		referencedPropertyURIs = new HashSet<String>();
		referencedIndividualURIs = new HashSet<String>();
		referencedVariableNames = new HashSet<String>();
	}

	public boolean hasReferencedClasses()
	{
		return referencedClassURIs.size() != 0;
	}

	public Set<String> getReferencedClassURIs()
	{
		return referencedClassURIs;
	}

	public boolean hasReferencedProperties()
	{
		return referencedPropertyURIs.size() != 0;
	}

	public Set<String> getReferencedPropertyURIs()
	{
		return referencedPropertyURIs;
	}

	public boolean hasReferencedIndividuals()
	{
		return referencedIndividualURIs.size() != 0;
	}

	public Set<String> getReferencedIndividualURIs()
	{
		return referencedIndividualURIs;
	}

	public boolean hasReferencedVariables()
	{
		return referencedVariableNames.size() != 0;
	}

	public Set<String> getReferencedVariableNames()
	{
		return referencedVariableNames;
	}

	public void addReferencedClassURI(String classURI)
	{
		if (!referencedClassURIs.contains(classURI))
			referencedClassURIs.add(classURI);
	}

	public void addReferencedPropertyURI(String propertyURI)
	{
		if (!referencedPropertyURIs.contains(propertyURI))
			referencedPropertyURIs.add(propertyURI);
	}

	public void addReferencedIndividualURI(String individualURI)
	{
		if (!referencedIndividualURIs.contains(individualURI))
			referencedIndividualURIs.add(individualURI);
	}

	public void addReferencedVariableName(String variableName)
	{
		if (!referencedVariableNames.contains(variableName))
			referencedVariableNames.add(variableName);
	}

}
