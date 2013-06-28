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

import edu.stanford.smi.protegex.owl.swrl.portability.OWLClassReference;
import edu.stanford.smi.protegex.owl.swrl.portability.OWLSubClassAxiomReference;

public class P3OWLSubClassAxiomReference implements OWLSubClassAxiomReference
{
	private OWLClassReference subClass, superClass;

	public P3OWLSubClassAxiomReference(OWLClassReference subClass, OWLClassReference superClass)
	{
		this.subClass = subClass;
		this.superClass = superClass;
	}

	public OWLClassReference getSubClass()
	{
		return subClass;
	}

	public OWLClassReference getSuperClass()
	{
		return superClass;
	}

	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if ((obj == null) || (obj.getClass() != this.getClass()))
			return false;
		P3OWLSubClassAxiomReference impl = (P3OWLSubClassAxiomReference)obj;
		return (super.equals((P3OWLSubClassAxiomReference)impl) && (subClass != null && impl.subClass != null && subClass.equals(impl.subClass)) && (superClass != null
				&& impl.superClass != null && superClass.equals(impl.superClass)));
	}

	public int hashCode()
	{
		int hash = 49;
		hash = hash + super.hashCode();
		hash = hash + (null == subClass ? 0 : subClass.hashCode());
		hash = hash + (null == superClass ? 0 : superClass.hashCode());
		return hash;
	}

	public String toString()
	{
		return "" + getSubClass() + " subclass of " + getSuperClass() + "";
	}
}
