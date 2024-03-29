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


package edu.stanford.smi.protegex.owl.swrl.bridge.builtins.swrlx;

import java.util.HashMap;
import java.util.List;

import edu.stanford.smi.protegex.owl.swrl.bridge.BuiltInArgument;
import edu.stanford.smi.protegex.owl.swrl.bridge.builtins.AbstractSWRLBuiltInLibrary;
import edu.stanford.smi.protegex.owl.swrl.bridge.exceptions.BuiltInException;
import edu.stanford.smi.protegex.owl.swrl.bridge.exceptions.SWRLRuleEngineBridgeException;
import edu.stanford.smi.protegex.owl.swrl.portability.OWLClassReference;
import edu.stanford.smi.protegex.owl.swrl.portability.OWLNamedIndividualReference;

/**
 * Implementations library for SWRL Extensions built-in methods. See <a
 * href="http://protege.cim3.net/cgi-bin/wiki.pl?SWRLExtensionsBuiltIns">here</a> for documentation on this library.
 *
 * See <a href="http://protege.cim3.net/cgi-bin/wiki.pl?SWRLBuiltInBridge">here</a> for documentation on defining SWRL built-in libraries.
 */
public class SWRLBuiltInLibraryImpl extends AbstractSWRLBuiltInLibrary
{
  private static String SWRLXLibraryName = "SWRLExtensionsBuiltIns";
  private HashMap<String, OWLClassReference> classInvocationMap;
  private HashMap<String, OWLNamedIndividualReference> individualInvocationMap;

  public SWRLBuiltInLibraryImpl() 
  { 
    super(SWRLXLibraryName); 
  } // SWRLBuiltInLibraryImpl

  public void reset() 
  {
    classInvocationMap = new HashMap<String, OWLClassReference>();
    individualInvocationMap = new HashMap<String, OWLNamedIndividualReference>();
  } // reset

  /**
   ** For every pattern of second and subsequent arguments, create an OWL anonymous class and bind it to the first argument. If
   ** the first argument is already bound when the built-in is called, this method returns true.
   */
  public boolean makeOWLClass(List<BuiltInArgument> arguments) throws BuiltInException
  {
    checkNumberOfArgumentsAtLeast(2, arguments.size());

    if (isUnboundArgument(0, arguments)) {
      OWLClassReference owlClass = null;
      String createInvocationPattern 
        = createInvocationPattern(getBuiltInBridge(), getInvokingRuleName(), getInvokingBuiltInIndex(), getIsInConsequent(),
                                  arguments.subList(1, arguments.size()));

      if (classInvocationMap.containsKey(createInvocationPattern)) owlClass = classInvocationMap.get(createInvocationPattern);
      else {
        owlClass = getBuiltInBridge().injectOWLClassDeclaration();
        classInvocationMap.put(createInvocationPattern, owlClass);
      } // if
      arguments.get(0).setBuiltInResult(createClassArgument(owlClass.getURI())); // Bind the result to the first parameter      
    } // if
    
    return true;
  } // makeOWLClass

  /**
   ** For every pattern of second and subsequent arguments, create an OWL individual of type OWL:Thing and bind it to the first argument. If
   ** the first argument is already bound when the built-in is called, this method returns true.
   */
  public boolean makeOWLIndividual(List<BuiltInArgument> arguments) throws BuiltInException
  {
    checkNumberOfArgumentsAtLeast(2, arguments.size());

    if (isUnboundArgument(0, arguments)) {
      OWLNamedIndividualReference owlIndividual = null;
      String createInvocationPattern 
        = createInvocationPattern(getBuiltInBridge(), getInvokingRuleName(), getInvokingBuiltInIndex(), getIsInConsequent(),
                                  arguments.subList(1, arguments.size()));

      if (individualInvocationMap.containsKey(createInvocationPattern)) owlIndividual = individualInvocationMap.get(createInvocationPattern);
      else {
        owlIndividual = getBuiltInBridge().injectOWLIndividualDeclaration();
        individualInvocationMap.put(createInvocationPattern, owlIndividual);
      } // if
      arguments.get(0).setBuiltInResult(createIndividualArgument(owlIndividual.getURI())); // Bind the result to the first parameter      
    } // if
    
    return true;
  } // makeOWLIndividual

  // For backwards compatability
  public boolean makeOWLThing(List<BuiltInArgument> arguments) throws BuiltInException
  {
    return makeOWLIndividual(arguments);
  } // makeOWLThing

  // For backwards compatability
  public boolean createOWLThing(List<BuiltInArgument> arguments) throws BuiltInException
  {
    return makeOWLIndividual(arguments);
  } // createOWLThing

  // TODO: check for invocations to swrlx built-ins, which will cause blocking
  public boolean invokeSWRLBuiltIn(List<BuiltInArgument> arguments) throws BuiltInException
  {
    checkNumberOfArgumentsAtLeast(2, arguments.size());
    
    String builtInName = getArgumentAsAnIndividualURI(0, arguments);
    boolean result = false;

    try {
      result = getBuiltInBridge().invokeSWRLBuiltIn(getInvokingRuleName(), builtInName, getInvokingBuiltInIndex(), getIsInConsequent(),
                                                     arguments.subList(1, arguments.size()));
    } catch (SWRLRuleEngineBridgeException e) {
      throw new BuiltInException("error invoking built-in '" + builtInName + "' from built-in: " + e.getMessage());
    } // try

    return result;
  } 


} // SWRLBuiltInLibraryImpl
