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


package edu.stanford.smi.protegex.owl.swrl.sqwrl;

import java.util.Set;

import edu.stanford.smi.protegex.owl.swrl.parser.SWRLParseException;
import edu.stanford.smi.protegex.owl.swrl.portability.SWRLRuleReference;
import edu.stanford.smi.protegex.owl.swrl.sqwrl.exceptions.SQWRLException;

/**
 * This interface defines the methods that must be provided by a SQWRL query engine.
 */
public interface SQWRLQueryEngine
{
  /**
   * Run a named SQWRL query. SWRL rules will also be executed and any inferences produced by them will be available in the query.
   */
  SQWRLResult runSQWRLQuery(String queryName) throws SQWRLException;

  /**
   * Run a named SQWRL query without executing any SWRL rules in ontology.
   */
  SQWRLResult runStandaloneSQWRLQuery(String queryName) throws SQWRLException;

  /**
   * Run all SQWRL enabled 	queries.
   */
  void runSQWRLQueries() throws SQWRLException;

  /**
   * Create and run a SQWRL query. Query will be created and added to ontology.
   */
  SQWRLResult runSQWRLQuery(String queryName, String queryText) throws SQWRLException, SWRLParseException;
  
  /**
   * Create a SQWRL query.
   */
  void createSQWRLQuery(String queryName, String queryText) throws SQWRLException, SWRLParseException;

  /**
   * Delete a SQWRL query.
   */
  void deleteSQWRLQuery(String queryName) throws SQWRLException;

  /**
   * Get the results from a previously executed SQWRL query. Null is returned if there is no result.
   */
  SQWRLResult getSQWRLResult(String queryName) throws SQWRLException;

  /**
   * Get all the enabled SQWRL queries in the ontology.
   */
  Set<SWRLRuleReference> getSQWRLQueries() throws SQWRLException;
  
  /**
   * Get the names of the enabled SQWRL queries in the ontology.
   */
  Set<String> getSQWRLQueryNames() throws SQWRLException;
  
  /**
   * Returns the name of the underlying targer rule engine.
   */
  String getTargetRuleEngineName();
  
  // TODO: temporary
  String uri2PrefixedName(String uri);
  String name2URI(String prefixedName);
}
