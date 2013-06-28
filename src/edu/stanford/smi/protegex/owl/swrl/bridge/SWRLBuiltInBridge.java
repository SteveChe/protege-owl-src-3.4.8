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


package edu.stanford.smi.protegex.owl.swrl.bridge;

import java.util.List;
import java.util.Set;

import edu.stanford.smi.protegex.owl.swrl.bridge.exceptions.SWRLBuiltInBridgeException;
import edu.stanford.smi.protegex.owl.swrl.bridge.exceptions.SWRLRuleEngineBridgeException;
import edu.stanford.smi.protegex.owl.swrl.portability.OWLAxiomReference;
import edu.stanford.smi.protegex.owl.swrl.portability.OWLClassReference;
import edu.stanford.smi.protegex.owl.swrl.portability.OWLDataFactory;
import edu.stanford.smi.protegex.owl.swrl.portability.OWLNamedIndividualReference;
import edu.stanford.smi.protegex.owl.swrl.portability.OWLOntology;
import edu.stanford.smi.protegex.owl.swrl.portability.OWLPropertyAssertionAxiomReference;
import edu.stanford.smi.protegex.owl.swrl.sqwrl.exceptions.SQWRLException;
import edu.stanford.smi.protegex.owl.swrl.sqwrl.impl.SQWRLResultImpl;

/**
 * The SWRL Built-in Bridge defines the methods seen by built-in implementations at run time. Ideally, built-in implementations should only
 * use this interface to operate on the active ontology. However, some specialized libraries (e.g., abox and tbox) require direct access to
 * the active ontology so will use the provided getOWLModel method. It will be removed shortly.
 *
 * Detailed documentation for the SWRL rule engine bridge mechanism can be found <a
 * href="http://protege.cim3.net/cgi-bin/wiki.pl?SWRLRuleEngineBridgeFAQ">here</a>.
 */
public interface SWRLBuiltInBridge
{
  OWLOntology getActiveOntology();

  // The inject methods can be used by built-ins to inject new axioms into a bridge, which will also reflect them in the underlying
  // engine. Eventually collapse all inject methods into injectOWLAxiom.
  void injectOWLAxiom(OWLAxiomReference axiom) throws SWRLBuiltInBridgeException;

  // TODO: the following methods should be subsumed by injectOWLAxiom
  OWLClassReference injectOWLClassDeclaration() throws SWRLBuiltInBridgeException;
  void injectOWLClassDeclaration(String className) throws SWRLBuiltInBridgeException;
  OWLNamedIndividualReference injectOWLIndividualDeclaration() throws SWRLBuiltInBridgeException;
  void injectOWLIndividualDeclaration(OWLNamedIndividualReference owlIndividual) throws SWRLBuiltInBridgeException;
  OWLNamedIndividualReference injectOWLIndividualDeclaration(OWLClassReference owlClass) throws SWRLBuiltInBridgeException;

  boolean isOWLClass(String classURI);
  boolean isOWLObjectProperty(String propertyURI);
  boolean isOWLDataProperty(String propertyURI);
  boolean isOWLIndividual(String individualURI);
  boolean isOWLIndividualOfClass(String individualURI, String classURI);
  
  Set<OWLNamedIndividualReference> getOWLIndividuals();

  Set<OWLPropertyAssertionAxiomReference> getOWLPropertyAssertionAxioms(String individualURI, String propertyURI) throws SWRLBuiltInBridgeException;

  SQWRLResultImpl getSQWRLUnpreparedResult(String queryURI) throws SQWRLException;

  OWLDataFactory getOWLDataFactory();
  OWLDataValueFactory getOWLDataValueFactory();
  
  // TODO: temporary 
  boolean invokeSWRLBuiltIn(String ruleName, String builtInName, int builtInIndex, boolean isInConsequent, List<BuiltInArgument> arguments) 
    throws SWRLRuleEngineBridgeException;
} 
