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


package edu.stanford.smi.protegex.owl.swrl;

import java.util.Set;

import edu.stanford.smi.protegex.owl.swrl.bridge.OWLDataValueFactory;
import edu.stanford.smi.protegex.owl.swrl.exceptions.SWRLRuleEngineException;
import edu.stanford.smi.protegex.owl.swrl.portability.OWLAxiomReference;
import edu.stanford.smi.protegex.owl.swrl.portability.OWLClassReference;
import edu.stanford.smi.protegex.owl.swrl.portability.OWLNamedIndividualReference;
import edu.stanford.smi.protegex.owl.swrl.portability.SWRLRuleReference;
import edu.stanford.smi.protegex.owl.swrl.sqwrl.SQWRLQueryEngine;

/**
 * This interface defines methods that must be provided by a SWRL rule engine.
 * 
 * Detailed documentation for this mechanism can be found <a href="http://protege.cim3.net/cgi-bin/wiki.pl?SWRLRuleEngineBridgeFAQ">here</a>.
 */
public interface SWRLRuleEngine extends SQWRLQueryEngine
{
  /**
   * Load rules and knowledge from OWL into bridge, send them to a rule engine, run the rule engine, and write any inferred knowledge back
   * to OWL.
   */
  void infer() throws SWRLRuleEngineException;

  /**
   * Load rules and relevant knowledge from OWL into bridge. All existing bridge rules and knowledge will first be cleared and the associated rule
   * engine will be reset.
   */
  void importSWRLRulesAndOWLKnowledge() throws SWRLRuleEngineException;

  /**
   * Load specific query, all enabled rules, and relevant knowledge from OWL into bridge. All existing bridge rules and knowledge will first be cleared and the associated rule
   * engine will be reset.
   */
  void importSQWRLQueryAndOWLKnowledge(String queryName) throws SWRLRuleEngineException;

  /**
   * Run the rule engine.
   */
  void run() throws SWRLRuleEngineException;

  /**
   * Write knowledge inferred by rule engine back to OWL.
   */
  void writeInferredKnowledge2OWL() throws SWRLRuleEngineException;

  /**
   *  Clear all inferred and injected knowledge from rule engine, deleted asserted knowledge from the bridge, and leave imported bridge
   *  knowledge intact.
   */
  void reset() throws SWRLRuleEngineException;

  // Convenience methods to display rule engine activity
  int getNumberOfImportedSWRLRules();
  int getNumberOfImportedOWLAxioms();
  int getNumberOfInferredOWLAxioms();
  int getNumberOfInjectedOWLAxioms();
  int getNumberOfImportedOWLClasses();
  int getNumberOfImportedOWLIndividuals();
  int getNumberOfInferredOWLIndividuals();
  int getNumberOfInjectedOWLClasses();
  int getNumberOfInjectedOWLIndividuals();

  Set<OWLAxiomReference> getImportedOWLAxioms();
  Set<OWLAxiomReference> getInferredOWLAxioms();
  Set<OWLAxiomReference> getInjectedOWLAxioms();
  Set<SWRLRuleReference> getImportedSWRLRules();
  Set<OWLClassReference> getImportedOWLClasses();
  Set<OWLClassReference> getInjectedOWLClasses();
  Set<OWLNamedIndividualReference> getImportedOWLIndividuals();
  Set<OWLNamedIndividualReference> getReclassifiedOWLIndividuals();
  Set<OWLNamedIndividualReference> getInjectedOWLIndividuals();

  String getTargetRuleEngineName();
  String getTargetRuleEngineVersion();

  // TODO: temporary
  String uri2PrefixedName(String uri);
  String name2URI(String prefixedName);
  OWLDataValueFactory getOWLDataValueFactory();
}
