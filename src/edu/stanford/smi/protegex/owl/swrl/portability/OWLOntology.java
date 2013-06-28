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

import java.util.Set;

import edu.stanford.smi.protegex.owl.model.OWLModel;
import edu.stanford.smi.protegex.owl.swrl.bridge.exceptions.BuiltInException;
import edu.stanford.smi.protegex.owl.swrl.bridge.exceptions.OWLConversionFactoryException;
import edu.stanford.smi.protegex.owl.swrl.parser.SWRLParseException;
import edu.stanford.smi.protegex.owl.swrl.sqwrl.exceptions.DataValueConversionException;
import edu.stanford.smi.protegex.owl.swrl.sqwrl.exceptions.SQWRLException;

/**
 * Interface that is very roughly equivalent to the OWLAPI interfaces OWLOntology and OWLOntologyManager. All SWRLTab code in Protege-OWL will eventually use
 * this interface to interact with an OWL ontology, thus easing the port to the OWLAPI.
 * 
 * At present, this interface is not fully aligned with the OWLAPI.
 */
public interface OWLOntology
{
	boolean containsClassInSignature(String classURI, boolean includeImportsClosure);

	boolean containsObjectPropertyInSignature(String propertyURI, boolean includeImportsClosure);

	boolean containsDataPropertyInSignature(String propertyURI, boolean includeImportsClosure);

	boolean containsIndividualInSignature(String individualURI, boolean includeImportsClosure);

	Set<OWLSameIndividualAxiomReference> getSameIndividualAxioms() throws OWLConversionFactoryException;

	Set<OWLDifferentIndividualsAxiomReference> getOWLDifferentIndividualsAxioms() throws OWLConversionFactoryException;

	// Considers sub property and equivalence relationships
	Set<OWLPropertyAssertionAxiomReference> getOWLPropertyAssertionAxioms(String individualURI, String propertyURI)
		throws OWLConversionFactoryException, DataValueConversionException;

	Set<OWLPropertyAssertionAxiomReference> getOWLPropertyAssertionAxioms(String propertyURI) throws OWLConversionFactoryException, DataValueConversionException;

	SWRLRuleReference getSWRLRule(String ruleName) throws OWLConversionFactoryException;

	OWLClassReference getOWLClass(String classURI) throws OWLConversionFactoryException;

	OWLNamedIndividualReference getOWLIndividual(String individualURI) throws OWLConversionFactoryException;

	OWLObjectPropertyReference getOWLObjectProperty(String propertyURI) throws OWLConversionFactoryException;

	OWLDataPropertyReference getOWLDataProperty(String propertyURI) throws OWLConversionFactoryException;

	Set<OWLNamedIndividualReference> getAllOWLIndividualsOfClass(String classURI) throws OWLConversionFactoryException;

	Set<SWRLRuleReference> getSWRLRules() throws OWLConversionFactoryException, SQWRLException, BuiltInException;

	boolean isOWLNamedIndividualOfClass(String individualURI, String classURI);

	boolean isSWRLBuiltIn(String builtInURI);

	boolean isOWLNamedClass(String classURI);

	boolean isValidURI(String uri);

	String uri2PrefixedName(String uri);

	String prefixedName2URI(String prefixedName);

	// Write methods
	void writeOWLClassDeclaration(OWLClassReference owlClass) throws OWLConversionFactoryException;

	void writeOWLIndividualDeclaration(OWLNamedIndividualReference owlIndividual) throws OWLConversionFactoryException;

	void writeOWLAxiom(OWLAxiomReference axiom) throws OWLConversionFactoryException;

	// Creation methods
	OWLClassReference createOWLClass();

	String createNewResourceURI(String prefix);

	SWRLRuleReference createSWRLRule(String ruleName, String ruleText) throws OWLConversionFactoryException, SWRLParseException;

	void deleteSWRLRule(String ruleURI) throws OWLConversionFactoryException;

	OWLModel getOWLModel(); // TODO: Protege-OWL dependency
}
