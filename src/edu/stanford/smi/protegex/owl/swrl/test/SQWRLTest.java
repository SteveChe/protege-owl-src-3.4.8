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


package edu.stanford.smi.protegex.owl.swrl.test;

import edu.stanford.smi.protegex.owl.jena.JenaOWLModel;
import edu.stanford.smi.protegex.owl.swrl.SWRLRuleEngine;
import edu.stanford.smi.protegex.owl.swrl.bridge.SWRLRuleEngineFactory;
import edu.stanford.smi.protegex.owl.swrl.exceptions.SWRLOWLUtilException;
import edu.stanford.smi.protegex.owl.swrl.exceptions.SWRLRuleEngineException;
import edu.stanford.smi.protegex.owl.swrl.sqwrl.IndividualValue;
import edu.stanford.smi.protegex.owl.swrl.sqwrl.SQWRLQueryEngine;
import edu.stanford.smi.protegex.owl.swrl.sqwrl.SQWRLQueryEngineFactory;
import edu.stanford.smi.protegex.owl.swrl.sqwrl.SQWRLResult;
import edu.stanford.smi.protegex.owl.swrl.util.SWRLOWLUtil;

public class SQWRLTest
{

	public static void main(String args[])
	{
		String owlFileName = "";

		if (args.length == 1) {
			owlFileName = args[0];
		} else
			Usage();

		try {
			JenaOWLModel owlModel = SWRLOWLUtil.createJenaOWLModel(owlFileName);
			SWRLRuleEngine ruleEngine = SWRLRuleEngineFactory.create(owlModel);
			SQWRLQueryEngine queryEngine = SQWRLQueryEngineFactory.create(owlModel);
			SQWRLResult result;

			result = queryEngine.runSQWRLQuery("Query1");

			while (result.hasNext()) {
				IndividualValue x = result.getObjectValue("?x");
				System.err.println("value: ?x=" + x);
				result.next();
			} // while

			ruleEngine.infer();

		} catch (SWRLRuleEngineException e) {
			System.err.println("SWRL rule engine exception: " + e.getMessage());
			e.printStackTrace();
		} catch (SWRLOWLUtilException e) {
			System.err.println("Exception: " + e.getMessage());
			e.printStackTrace();
		} // try
	}

	/*
	 * } catch (SWRLParseException e) { System.err.println("Parse exception: " + e.getMessage()); e.printStackTrace(); } catch (SWRLFactoryException e) {
	 * System.err.println("Factory exception: " + e.getMessage()); e.printStackTrace();
	 */
	private static void Usage()
	{
		System.err.println("Usage: SQWRLTest <owlFileName>");
		System.exit(1);
	}
}
