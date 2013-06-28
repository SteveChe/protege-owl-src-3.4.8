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


package edu.stanford.smi.protegex.owl.swrl.bridge.builtins.query;

import edu.stanford.smi.protegex.owl.swrl.bridge.BuiltInArgument;
import edu.stanford.smi.protegex.owl.swrl.bridge.exceptions.BuiltInException;
import edu.stanford.smi.protegex.owl.swrl.bridge.builtins.AbstractSWRLBuiltInLibrary;

import java.util.*;

/**
 * This built-in library has been superseded by the SQWRL built-in library. See See <a
 * href="http://protege.cim3.net/cgi-bin/wiki.pl?SQWRL">here</a> for documentation on this new library.
 */
public class SWRLBuiltInLibraryImpl extends AbstractSWRLBuiltInLibrary 
{
  private static String QueryLibraryName = "SWRLQueryBuiltIns";

  public SWRLBuiltInLibraryImpl() { super(QueryLibraryName); }

  public void reset() {}

  public boolean select(List<BuiltInArgument> arguments) throws BuiltInException { return throwSupersededException(); }
  public boolean selectDistinct(List<BuiltInArgument> arguments) throws BuiltInException { return throwSupersededException(); }
  public boolean count(List<BuiltInArgument> arguments) throws BuiltInException { return throwSupersededException(); }
  public boolean min(List<BuiltInArgument> arguments) throws BuiltInException { return throwSupersededException(); }
  public boolean max(List<BuiltInArgument> arguments) throws BuiltInException { return throwSupersededException(); }
  public boolean sum(List<BuiltInArgument> arguments) throws BuiltInException { return throwSupersededException(); }
  public boolean avg(List<BuiltInArgument> arguments) throws BuiltInException { return throwSupersededException(); }
  public boolean columnNames(List<BuiltInArgument> arguments) throws BuiltInException { return throwSupersededException(); }
  public boolean orderBy(List<BuiltInArgument> arguments) throws BuiltInException { return throwSupersededException(); }
  public boolean orderByDescending(List<BuiltInArgument> arguments) throws BuiltInException { return throwSupersededException(); }
  
  private boolean throwSupersededException() throws BuiltInException
  {
    throw new BuiltInException("\nThe query library has been superseded by the SQWRL library; to upgrade, import the SQWRL ontology\n" +
                               "(http://sqwrl.stanford.edu/ontologies/built-ins/3.4/sqwrl.owl) from the Protege-OWL repository, give it\n" +
                               "the prefix 'sqwrl', and replace 'query' with this prefix for all existing query built-ins"); 
  } // throwSupersededException

} // SWRLBuiltInLibraryImpl
