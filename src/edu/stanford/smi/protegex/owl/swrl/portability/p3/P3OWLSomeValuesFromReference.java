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
import edu.stanford.smi.protegex.owl.swrl.portability.OWLPropertyReference;
import edu.stanford.smi.protegex.owl.swrl.portability.OWLSomeValuesFromReference;

public class P3OWLSomeValuesFromReference extends P3OWLRestrictionReference implements OWLSomeValuesFromReference
{
	private OWLClassReference someValuesFrom;

	public P3OWLSomeValuesFromReference(OWLClassReference owlClass, OWLPropertyReference onProperty, OWLClassReference someValuesFrom)
	{
		super(owlClass, onProperty);
		this.someValuesFrom = someValuesFrom;
	}

	public OWLClassReference getSomeValuesFrom()
	{
		return someValuesFrom;
	}

	public String toString()
	{
		return "someValuesFrom(" + asOWLClass().getURI() + ", " + getProperty().getURI() + ", " + getSomeValuesFrom().getURI() + ")";
	}

	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if ((obj == null) || (obj.getClass() != this.getClass()))
			return false;
		P3OWLSomeValuesFromReference impl = (P3OWLSomeValuesFromReference)obj;
		return (asOWLClass().getURI() == impl.asOWLClass().getURI() || (asOWLClass().getURI() != null && asOWLClass().getURI().equals(impl.asOWLClass().getURI())))
				&& (getProperty().getURI() == impl.getProperty().getURI() || (getProperty().getURI() != null && getProperty().getURI().equals(
						impl.getProperty().getURI())))
				&& (getSomeValuesFrom().getURI() == impl.getSomeValuesFrom().getURI() || (getSomeValuesFrom().getURI() != null && getSomeValuesFrom().getURI().equals(
						impl.getSomeValuesFrom().getURI())));
	}

	public int hashCode()
	{
		int hash = 232;

		hash = hash + (null == asOWLClass().getURI() ? 0 : asOWLClass().getURI().hashCode());
		hash = hash + (null == getProperty().getURI() ? 0 : getProperty().getURI().hashCode());
		hash = hash + (null == getSomeValuesFrom().getURI() ? 0 : getSomeValuesFrom().getURI().hashCode());

		return hash;
	}

}
