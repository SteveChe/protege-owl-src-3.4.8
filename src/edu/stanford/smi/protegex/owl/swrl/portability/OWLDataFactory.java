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


package edu.stanford.smi.protegex.owl.swrl.portability;

import java.util.List;
import java.util.Set;

import edu.stanford.smi.protegex.owl.swrl.bridge.BuiltInArgument;
import edu.stanford.smi.protegex.owl.swrl.bridge.OWLPropertyPropertyAssertionAxiomReference;
import edu.stanford.smi.protegex.owl.swrl.bridge.exceptions.OWLFactoryException;

/**
 * Factory to create OWLAPI-like entities. Provides a rough starting point for a port to the OWLAPI.
 */
public interface OWLDataFactory
{
	Set<SWRLRuleReference> getSWRLRules() throws OWLFactoryException;

	SWRLRuleReference getSWRLRule(String ruleName) throws OWLFactoryException;

	SWRLBuiltInAtomReference getSWRLBuiltInAtom(String builtInURI, String builtInPrefixedName, List<BuiltInArgument> arguments);

	OWLClassReference getOWLClass(String classURI);

	OWLNamedIndividualReference getOWLIndividual(String individualURI);

	OWLObjectPropertyReference getOWLObjectProperty(String propertyURI);

	OWLDataPropertyReference getOWLDataProperty(String propertyURI);

	OWLDataPropertyAssertionAxiomReference getOWLDataPropertyAssertionAxiom(OWLNamedIndividualReference subject, OWLPropertyReference property,
																																					OWLLiteralReference object);

	OWLObjectPropertyAssertionAxiomReference getOWLObjectPropertyAssertionAxiom(OWLNamedIndividualReference subject, OWLPropertyReference property,
																																							OWLNamedIndividualReference object);

	OWLDifferentIndividualsAxiomReference getOWLDifferentIndividualsAxiom(OWLNamedIndividualReference individual1, OWLNamedIndividualReference individual2);

	OWLDifferentIndividualsAxiomReference getOWLDifferentIndividualsAxiom(Set<OWLNamedIndividualReference> individuals);

	OWLSameIndividualAxiomReference getOWLSameIndividualAxiom(OWLNamedIndividualReference individual1, OWLNamedIndividualReference individual2);

	OWLClassAssertionAxiomReference getOWLClassAssertionAxiom(OWLNamedIndividualReference individual, OWLClassReference description);

	OWLSubClassAxiomReference getOWLSubClassAxiom(OWLClassReference subClass, OWLClassReference superClass);

	OWLSomeValuesFromReference getOWLSomeValuesFrom(OWLClassReference owlClass, OWLPropertyReference onProperty, OWLClassReference someValuesFrom);

	OWLDeclarationAxiomReference getOWLDeclarationAxiom(OWLEntityReference owlEntity);

	OWLTypedLiteralReference getOWLTypedLiteral(int value);

	OWLTypedLiteralReference getOWLTypedLiteral(float value);

	OWLTypedLiteralReference getOWLTypedLiteral(double value);

	OWLTypedLiteralReference getOWLTypedLiteral(boolean value);

	OWLTypedLiteralReference getOWLTypedLiteral(String value);

	// The following do not have corresponding methods in the OWLAPI.
	OWLClassPropertyAssertionAxiomReference getOWLClassPropertyAssertionAxiom(OWLNamedIndividualReference subject, OWLPropertyReference property,
																																						OWLClassReference object);

	OWLPropertyPropertyAssertionAxiomReference getOWLPropertyPropertyAssertionAxiom(OWLNamedIndividualReference subject, OWLPropertyReference property,
																																									OWLPropertyReference object);
}
