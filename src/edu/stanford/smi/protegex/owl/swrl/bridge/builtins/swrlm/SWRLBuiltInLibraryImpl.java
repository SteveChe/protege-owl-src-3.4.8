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


package edu.stanford.smi.protegex.owl.swrl.bridge.builtins.swrlm;

import java.util.List;

import org.nfunk.jep.JEP;

import edu.stanford.smi.protegex.owl.swrl.bridge.BuiltInArgument;
import edu.stanford.smi.protegex.owl.swrl.bridge.builtins.AbstractSWRLBuiltInLibrary;
import edu.stanford.smi.protegex.owl.swrl.bridge.exceptions.BuiltInException;

/**
 ** Implementations library for SWRL mathematical built-in methods. See <a
 ** href="http://protege.cim3.net/cgi-bin/wiki.pl?SWRLTabMathematicalBuiltIns">here</a> for documentation on this library.
 **
 ** See <a href="http://protege.cim3.net/cgi-bin/wiki.pl?SWRLBuiltInBridge">here</a> for documentation on defining SWRL built-in libraries.
 */
public class SWRLBuiltInLibraryImpl extends AbstractSWRLBuiltInLibrary
{
  private static String SWRLMLibraryName = "SWRLTabMathematicalBuiltIns";

  private JEP jep = null;

  public SWRLBuiltInLibraryImpl() 
  { 
    super(SWRLMLibraryName); 
  } // SWRLMLibraryImpl

  public void reset() 
  {
    jep = null;
  } // reset

  /**
   ** Returns true if the first argument is equal to the square root of the second argument. If the first argument is unbound, bind it to
   ** the square root of the second argument.
   */
  public boolean sqrt(List<BuiltInArgument> arguments) throws BuiltInException
  {
    boolean result;
    double argument1, argument2;

    checkNumberOfArgumentsAtLeast(2, arguments.size());

    argument2 = getArgumentAsADouble(1, arguments);

    if (isUnboundArgument(0, arguments)) {
      arguments.get(0).setBuiltInResult(createDataValueArgument(java.lang.Math.sqrt(argument2)));
      result = true;
    } else {
      argument1 = getArgumentAsADouble(0, arguments);
      result = argument1 == java.lang.Math.sqrt(argument2);
    } // if
    
    return result;
  } // sqrt

  /**
   ** Returns true if the first argument is equal to the natural logarithm (base e) of the second argument. If the first argument is
   ** unbound, bind it to the natural logarithm of the second argument.
   */
  public boolean log(List<BuiltInArgument> arguments) throws BuiltInException
  {
    boolean result;
    double argument1, argument2;

    checkNumberOfArgumentsAtLeast(2, arguments.size());

    argument2 = getArgumentAsADouble(1, arguments);

    if (isUnboundArgument(0, arguments)) {
      arguments.get(0).setBuiltInResult(createDataValueArgument(java.lang.Math.log(argument2)));
      result = true;
    } else {
      argument1 = getArgumentAsADouble(0, arguments);
      result = argument1 == java.lang.Math.log(argument2);
    } // if
    
    return result;
  } // log

  /*
  ** Returns true if the first argument is equals to the mathematical expression specified in the second argument, which may use the values
  ** specified by the variables in the optional subsequent arguments. If the first argument is unbound, bind it to the result of the
  ** expression.
  */ 
  public boolean eval(List<BuiltInArgument> arguments) throws BuiltInException
  {
    double value;
    String expression;
    boolean result;

    checkNumberOfArgumentsAtLeast(2, arguments.size());

    expression = getArgumentAsAString(1, arguments);

    if (arguments.size() > 2) {
      List<BuiltInArgument> variableArguments = arguments.subList(2, arguments.size());

      checkForUnboundArguments(variableArguments, "unexpected unbound expression argument");
      checkForNonVariableArguments(variableArguments, "unexpected non variable argument");

      for (BuiltInArgument argument : variableArguments) { 
        String variableName = argument.getVariableName(); // We will have already checked that they are all variables
        double variableValue = getArgumentAsADouble(argument);
        getJEP().addVariable(variableName, variableValue);
      } // for
    } // if

    getJEP().parseExpression(expression);
    if (getJEP().hasError()) throw new BuiltInException("exception parsing expression '" + expression + "': " + getJEP().getErrorInfo());
    value = getJEP().getValue();
    if (getJEP().hasError()) throw new BuiltInException("exception parsing expression '" + expression + "': " + getJEP().getErrorInfo());

    if (isUnboundArgument(0, arguments)) {
      arguments.get(0).setBuiltInResult(createDataValueArgument(value));
      result = true;
    } else {
      result = value == getArgumentAsADouble(0, arguments);
    } // if
    
    return result;
  } // eval

  // cf. http://www.singularsys.com/jep/doc/javadoc/org/nfunk/jep/JEP.html for JEP API
  private JEP getJEP()
  {
    if (jep == null) {
      jep = new JEP();

      jep.addStandardFunctions();
      jep.addStandardConstants();
      jep.setImplicitMul(true);
    } // if

    return jep;
  } // getJEP

} // SWRLBuiltInLibraryImpl
