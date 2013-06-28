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

import edu.stanford.smi.protegex.owl.swrl.bridge.exceptions.TargetSWRLRuleEngineException;
import edu.stanford.smi.protegex.owl.swrl.portability.OWLAxiomReference;
import edu.stanford.smi.protegex.owl.swrl.portability.OWLClassAssertionAxiomReference;
import edu.stanford.smi.protegex.owl.swrl.portability.OWLClassPropertyAssertionAxiomReference;
import edu.stanford.smi.protegex.owl.swrl.portability.OWLDataPropertyAssertionAxiomReference;
import edu.stanford.smi.protegex.owl.swrl.portability.OWLDeclarationAxiomReference;
import edu.stanford.smi.protegex.owl.swrl.portability.OWLDifferentIndividualsAxiomReference;
import edu.stanford.smi.protegex.owl.swrl.portability.OWLObjectPropertyAssertionAxiomReference;
import edu.stanford.smi.protegex.owl.swrl.portability.OWLSameIndividualAxiomReference;
import edu.stanford.smi.protegex.owl.swrl.portability.OWLSubClassAxiomReference;

/**
 * Interface that defines method that must be implemented by a target rule engine to natively representing OWL axioms.
 * 
 * A default implementation that does most of the heavy lifting can be found in the DefaultOWLAxiomConvertor class.
 */
public interface OWLAxiomConvertor
{
	void generateOWLAxiom(OWLAxiomReference axiom) throws TargetSWRLRuleEngineException;
	
	void generateOWLDeclarationAxiom(OWLDeclarationAxiomReference axiom) throws TargetSWRLRuleEngineException; 
  void generateOWLDataPropertyAssertionAxiom(OWLDataPropertyAssertionAxiomReference axiom) throws TargetSWRLRuleEngineException;
  void generateOWLObjectPropertyAssertionAxiom(OWLObjectPropertyAssertionAxiomReference axiom) throws TargetSWRLRuleEngineException;
  void generateOWLSameIndividualAxiom(OWLSameIndividualAxiomReference axiom)	throws TargetSWRLRuleEngineException;
  void generateOWLDifferentIndividualsAxiom(OWLDifferentIndividualsAxiomReference axiom)	throws TargetSWRLRuleEngineException;
  void generateOWLClassAssertionAxiom(OWLClassAssertionAxiomReference axiom) throws TargetSWRLRuleEngineException;
  void generateOWLSubclassAxiom(OWLSubClassAxiomReference axiom) throws TargetSWRLRuleEngineException;
  
  void generateOWLClassPropertyAssertionAxiom(OWLClassPropertyAssertionAxiomReference axiom)	throws TargetSWRLRuleEngineException;
  void generateOWLPropertyPropertyAssertionAxiom(OWLPropertyPropertyAssertionAxiomReference axiom) throws TargetSWRLRuleEngineException;
} 
