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

import edu.stanford.smi.protegex.owl.swrl.portability.OWLDataPropertyAssertionAxiomReference;
import edu.stanford.smi.protegex.owl.swrl.portability.OWLLiteralReference;
import edu.stanford.smi.protegex.owl.swrl.portability.OWLNamedIndividualReference;
import edu.stanford.smi.protegex.owl.swrl.portability.OWLPropertyReference;

public class P3OWLDatatypePropertyAssertionAxiom extends P3OWLPropertyAssertionAxiomReference implements OWLDataPropertyAssertionAxiomReference
{
	private OWLLiteralReference object;

	public P3OWLDatatypePropertyAssertionAxiom(OWLNamedIndividualReference subject, OWLPropertyReference property, OWLLiteralReference object)
	{
		super(subject, property);
		this.object = object;
	}

	public OWLLiteralReference getObject()
	{
		return object;
	}

	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if ((obj == null) || (obj.getClass() != this.getClass()))
			return false;
		P3OWLDatatypePropertyAssertionAxiom impl = (P3OWLDatatypePropertyAssertionAxiom)obj;
		return (super.equals((P3OWLPropertyAssertionAxiomReference)impl) && (object != null && impl.object != null && object.equals(impl.object)));
	}

	public int hashCode()
	{
		int hash = 45;
		hash = hash + super.hashCode();
		hash = hash + (null == object ? 0 : object.hashCode());
		return hash;
	}

	public String toString()
	{
		String result = "" + getProperty() + "(" + getSubject() + ", ";

		if (object.isOWLStringLiteral())
			result += "\"" + object + "\"";
		else
			result += "" + object;

		result += ")";

		return result;
	}
}
