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

import edu.stanford.smi.protegex.owl.swrl.portability.OWLNamedIndividualReference;
import edu.stanford.smi.protegex.owl.swrl.portability.OWLSameIndividualAxiomReference;

public class P3OWLSameIndividualAxiomReference extends P3OWLNaryIndividualAxiomReference implements OWLSameIndividualAxiomReference
{
	private OWLNamedIndividualReference individual1, individual2;

	public P3OWLSameIndividualAxiomReference(OWLNamedIndividualReference individual1, OWLNamedIndividualReference individual2)
	{
		addIndividual(individual1);
		addIndividual(individual2);
		this.individual1 = individual1;
		this.individual2 = individual2;
	}

	public OWLNamedIndividualReference getIndividual1()
	{
		return individual1;
	}

	public OWLNamedIndividualReference getIndividual2()
	{
		return individual2;
	}

	public String toString()
	{
		return "sameAs" + super.toString();
	}
}
