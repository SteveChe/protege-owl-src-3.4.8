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

import edu.stanford.smi.protegex.owl.swrl.bridge.exceptions.SWRLRuleEngineBridgeException;
import edu.stanford.smi.protegex.owl.swrl.portability.OWLAxiomReference;
import edu.stanford.smi.protegex.owl.swrl.portability.OWLDataFactory;
import edu.stanford.smi.protegex.owl.swrl.portability.PrefixManager;
import edu.stanford.smi.protegex.owl.swrl.portability.SWRLAtomReference;
import edu.stanford.smi.protegex.owl.swrl.portability.SWRLBuiltInAtomReference;
import edu.stanford.smi.protegex.owl.swrl.portability.SWRLRuleReference;

/**
 * The SWRL Rule Engine Bridge defines the interface seen by a target implementation of a SWRL rule engine. The implementation uses this
 * interface primarily to infer axioms and to invoke built-ins. 
 *
 * Detailed documentation for this mechanism can be found <a href="http://protege.cim3.net/cgi-bin/wiki.pl?SWRLRuleEngineBridgeFAQ">here</a>.
 */
public interface SWRLRuleEngineBridge
{
	void setTargetRuleEngine(TargetSWRLRuleEngine targetRuleEngine);
  
	/**
   * The infer method can be used by a target rule engines to assert axioms that they infer into the bridge.
   */
  void inferOWLAxiom(OWLAxiomReference axiom) throws SWRLRuleEngineBridgeException;
  
  /**
   * This method can be used by a target rule engines to invoke built-ins. If the invoked built-in generates an argument binding, the bridge will call the 
   * defineBuiltInArgumentBinding method in the target rule engine for each unique binding pattern.
   */
  boolean invokeSWRLBuiltIn(String ruleURI, String builtInURI, int builtInIndex, boolean isInConsequent, List<BuiltInArgument> arguments) 
    throws SWRLRuleEngineBridgeException;

  boolean isOWLClass(String uri);
  boolean isOWLObjectProperty(String uri);
  boolean isOWLDataProperty(String uri);
  boolean isOWLIndividual(String uri);

  OWLDataFactory getOWLDataFactory();
  OWLDataValueFactory getOWLDataValueFactory();
  
  // SQWRL-related functionality
  boolean isSQWRLQuery(SWRLRuleReference query);
  boolean usesSQWRLCollections(SWRLRuleReference query);
  List<SWRLAtomReference> getSQWRLPhase1BodyAtoms(SWRLRuleReference query);
  List<SWRLAtomReference> getSQWRLPhase2BodyAtoms(SWRLRuleReference query);
  List<SWRLBuiltInAtomReference> getBuiltInAtomsFromHead(SWRLRuleReference query, Set<String> builtInNames);
  List<SWRLBuiltInAtomReference> getBuiltInAtomsFromBody(SWRLRuleReference query, Set<String> builtInNames);
  
  // TODO: temporary
  String uri2PrefixedName(String uri);
  String name2URI(String prefixedName);
  PrefixManager getPrefixManager();
}
