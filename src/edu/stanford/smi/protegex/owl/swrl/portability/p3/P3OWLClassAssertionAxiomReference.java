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

import edu.stanford.smi.protegex.owl.swrl.portability.OWLClassAssertionAxiomReference;
import edu.stanford.smi.protegex.owl.swrl.portability.OWLClassReference;
import edu.stanford.smi.protegex.owl.swrl.portability.OWLNamedIndividualReference;

public class P3OWLClassAssertionAxiomReference implements OWLClassAssertionAxiomReference
{
	private OWLNamedIndividualReference individual;
	private OWLClassReference description;

	public P3OWLClassAssertionAxiomReference(OWLNamedIndividualReference individual, OWLClassReference description)
	{
		this.individual = individual;
		this.description = description;
	}

	public OWLClassReference getDescription()
	{
		return description;
	}

	public OWLNamedIndividualReference getIndividual()
	{
		return individual;
	}

	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if ((obj == null) || (obj.getClass() != this.getClass()))
			return false;
		P3OWLClassAssertionAxiomReference impl = (P3OWLClassAssertionAxiomReference)obj;
		return (super.equals((P3OWLClassAssertionAxiomReference)impl) && (description != null && impl.description != null && description.equals(impl.description)) && (individual != null
				&& impl.individual != null && individual.equals(impl.individual)));
	}

	public int hashCode()
	{
		int hash = 49;
		hash = hash + super.hashCode();
		hash = hash + (null == description ? 0 : description.hashCode());
		hash = hash + (null == individual ? 0 : individual.hashCode());
		return hash;
	}

	public String toString()
	{
		return "" + getDescription() + "(" + getIndividual() + ")";
	}
}
