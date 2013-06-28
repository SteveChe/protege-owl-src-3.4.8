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


package edu.stanford.smi.protegex.owl.swrl.bridge.impl;

import java.util.ArrayList;
import java.util.List;

import edu.stanford.smi.protegex.owl.swrl.bridge.BuiltInArgument;
import edu.stanford.smi.protegex.owl.swrl.bridge.MultiArgument;

/**
 * A class used to bind multiple arguments to a built-in argument
 */
public class MultiArgumentImpl extends BuiltInArgumentImpl implements MultiArgument
{
  private List<BuiltInArgument> arguments;

  public MultiArgumentImpl()
  {
    super();
    arguments = new ArrayList<BuiltInArgument>();
  }

  public MultiArgumentImpl(List<BuiltInArgument> arguments)
  {
    super();
    this.arguments = arguments;
  }

  public void addArgument(BuiltInArgument argument) { arguments.add(argument); }
  public void setArguments(List<BuiltInArgument> arguments) { this.arguments = arguments; }
  public List<BuiltInArgument> getArguments() { return arguments; }
  public int getNumberOfArguments() { return arguments.size(); }
  public boolean hasNoArguments() { return arguments.size() == 0; }
  
  public void setVariableName(String variableName)
  {
  	for (BuiltInArgument argument : arguments) argument.setVariableName(variableName); 
  }
  
  public int compareTo(BuiltInArgument argument)
  {
  	return getVariableName().compareTo(argument.getVariableName());
  }     
}
