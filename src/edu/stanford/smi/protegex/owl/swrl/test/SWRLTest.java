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
import edu.stanford.smi.protegex.owl.swrl.model.SWRLFactory;
import edu.stanford.smi.protegex.owl.swrl.model.SWRLImp;
import edu.stanford.smi.protegex.owl.swrl.util.SWRLOWLUtil;

public class SWRLTest
{
   public static void main(String args[]) 
  {
	  JenaOWLModel modelA = null, modelB = null;
    SWRLFactory factoryA = null, factoryB = null;
    String fileName1 = "", fileName2 = "", fileName3 = "", fileName4 = "";

    if (args.length == 4) {
      fileName1 = args[0];
      fileName2 = args[1];
      fileName3 = args[2];
      fileName4 = args[3];
    } else Usage();
   
//    String uriA = "file://dionisbg.di.funpic.de/familyA.owl";
    try {
      modelA=SWRLOWLUtil.createJenaOWLModel(fileName1);
      System.out.println("ModelA loaded ...");
    } catch (java.lang.Exception e) {
      e.printStackTrace();
    }
    factoryA = new SWRLFactory(modelA);
    System.out.println("SWRL FactoryA created ...");
    
    // -- OntologyB --
    try {
      modelB=SWRLOWLUtil.createJenaOWLModel(fileName2);
      System.out.println("ModelB loaded ...");
    }
    catch (java.lang.Exception e) {
      e.printStackTrace();
    }
    factoryB = new SWRLFactory(modelB);
    System.out.println("SWRL FactoryB created ...");
        
    // -- Copying rules between two models --
    try {
      System.out.print("Copying " + factoryA.getImps().size() + " rules to target ontology...");
         for (Object o : factoryA.getImps()) {
    	  if (o instanceof SWRLImp) {
    		  SWRLImp imp = (SWRLImp)o;
    		  System.err.println(imp.getBrowserText());
    		  factoryB.createImp(imp.getBrowserText());
    	  }
      }
      factoryA.deleteImps();
      
      System.out.print("Saving stripped ontology to " + fileName3 + "...");
      SWRLOWLUtil.writeJenaOWLModel2File(modelA, fileName3);
      System.out.println("...written.");
      
      System.out.print("Saving target ontology with rules to " + fileName4 + "...");
      SWRLOWLUtil.writeJenaOWLModel2File(modelB, fileName4);
      System.out.println("...written.");
      
    } catch (Throwable e) {
      e.printStackTrace();
    }
  } // main

  private static void Usage()
  {
    System.err.println("Usage: SWRLTest <fileName(with rules)> <fileName>(base)<fileName (stripped of rules)> <fileName>(only rules)");
    System.exit(1);
  } // Usage

} // SWRLTest
