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

import edu.stanford.smi.protegex.owl.model.OWLModel;
import edu.stanford.smi.protegex.owl.swrl.bridge.SWRLRuleEngineFactory;
import edu.stanford.smi.protegex.owl.swrl.exceptions.SWRLRuleEngineException;
import edu.stanford.smi.protegex.owl.swrl.sqwrl.exceptions.SQWRLException;

/**
 * Factory for creating a SQWRL query engine
 */
public class SQWRLQueryEngineFactory
{
  public static SQWRLQueryEngine create(OWLModel owlModel) throws SQWRLException
  {
    SQWRLQueryEngine queryEngine = null;

    try {
      queryEngine = SWRLRuleEngineFactory.create(owlModel);
    } catch (SWRLRuleEngineException e) {
      throw new SQWRLException("error creating SQWRL query engine: " + e.getMessage());
    } // try
    
    return queryEngine;
  }

  public static SQWRLQueryEngine create(String pluginName, OWLModel owlModel) throws SQWRLException
  {
    SQWRLQueryEngine queryEngine = null;

    try {
      queryEngine = SWRLRuleEngineFactory.create(pluginName, owlModel);
    } catch (SWRLRuleEngineException e) {
      throw new SQWRLException("error creating SQWRL query engine using '" + pluginName + "': " + e.getMessage());
    } // try
    
    return queryEngine;
  }
}
