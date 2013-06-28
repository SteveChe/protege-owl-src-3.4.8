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

import edu.stanford.smi.protegex.owl.swrl.portability.OWLClassReference;
import edu.stanford.smi.protegex.owl.swrl.portability.OWLNamedIndividualReference;

/**
 * Class representing an OWL individual.
 */
public class P3OWLNamedIndividualReference implements OWLNamedIndividualReference
{
	// NOTE: equals() method defined in this class

	private String individualURI;
	private Set<OWLClassReference> definingClasses;
	private Set<OWLNamedIndividualReference> sameAsIndividuals, differentFromIndividuals;

	public P3OWLNamedIndividualReference(String individualURI)
	{
		initialize(individualURI);
	}

	public String getURI()
	{
		return individualURI;
	}

	public Set<OWLClassReference> getTypes()
	{
		return definingClasses;
	}

	public void addType(OWLClassReference definingClass)
	{
		definingClasses.add(definingClass);
	}

	public void addSameAsIndividual(OWLNamedIndividualReference sameAsIndividual)
	{
		sameAsIndividuals.add(sameAsIndividual);
	}

	public void addDifferentFromIndividual(OWLNamedIndividualReference differentFromIndividual)
	{
		differentFromIndividuals.add(differentFromIndividual);
	}

	public Set<OWLNamedIndividualReference> getSameIndividuals()
	{
		return sameAsIndividuals;
	}

	public Set<OWLNamedIndividualReference> getDifferentIndividuals()
	{
		return differentFromIndividuals;
	}

	public boolean hasType(String classURI)
	{
		for (OWLClassReference owlClass : definingClasses)
			if (owlClass.getURI().equals(classURI))
				return true;

		return false;
	}

	public String toString()
	{
		return getURI();
	}

	public int compareTo(Object o)
	{
		return individualURI.compareTo(((P3OWLNamedIndividualReference)o).getURI());
	}

	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if ((obj == null) || (obj.getClass() != this.getClass()))
			return false;
		P3OWLNamedIndividualReference impl = (P3OWLNamedIndividualReference)obj;
		return (getURI() == impl.getURI() || (getURI() != null && getURI().equals(impl.getURI())));
	}

	public int hashCode()
	{
		int hash = 76;

		hash = hash + (null == getURI() ? 0 : getURI().hashCode());

		return hash;
	}

	private void initialize(String individualURI)
	{
		this.individualURI = individualURI;

		definingClasses = new HashSet<OWLClassReference>();
		sameAsIndividuals = new HashSet<OWLNamedIndividualReference>();
		differentFromIndividuals = new HashSet<OWLNamedIndividualReference>();
	}
}
