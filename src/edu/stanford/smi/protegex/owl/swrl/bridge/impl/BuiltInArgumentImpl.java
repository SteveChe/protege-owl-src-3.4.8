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

import edu.stanford.smi.protegex.owl.swrl.bridge.BuiltInArgument;
import edu.stanford.smi.protegex.owl.swrl.bridge.MultiArgument;
import edu.stanford.smi.protegex.owl.swrl.bridge.exceptions.BuiltInException;

/**
 * Class representing argument to built-ins
 */
public abstract class BuiltInArgumentImpl extends ArgumentImpl implements BuiltInArgument
{
  // There is an equals methods defined for this class.
  private BuiltInArgument builtInResult; // Used to store result of binding for unbound arguments

  public BuiltInArgumentImpl()
  {
  	super();
    builtInResult = null; 
  }

  public BuiltInArgumentImpl(String variableName) 
  {
  	super(variableName);
    builtInResult = null; 
  }

  public void setBuiltInResult(BuiltInArgument builtInResult) throws BuiltInException
  { 
    if (!isUnbound()) throw new BuiltInException("attempt to bind value to bound argument " + this.toString());
    
    setBound();

    this.builtInResult = builtInResult;
    this.builtInResult.setVariableName(getVariableName());
  } 

  public BuiltInArgument getBuiltInResult()
  { 
    //if (!isUnbound()) throw new BuiltInException("attempt to retrieve binding from a non bound argument " + this.toString());

    return builtInResult; 
  }

  public boolean hasBuiltInResult() { return builtInResult != null; }
  public boolean hasBuiltInMultiArgumentResult() { return hasBuiltInResult() && builtInResult instanceof MultiArgument; }
  
  public MultiArgument getBuiltInMultiArgumentResult() throws BuiltInException
  {
	  if (!hasBuiltInMultiArgumentResult()) throw new BuiltInException("argument is not a multi-argument");
	  
	  return (MultiArgument)builtInResult;
  }  

  public String toString()
  {
    if (builtInResult != null) return builtInResult.toString();
    else return "?" + getVariableName();
  }

  public boolean equals(Object obj)
  {
    if(this == obj) return true;
    if((obj == null) || (obj.getClass() != this.getClass())) return false;
    BuiltInArgumentImpl impl = (BuiltInArgumentImpl)obj;
    return super.equals((ArgumentImpl)impl) && 
           ((builtInResult == impl.builtInResult) || (builtInResult != null && builtInResult.equals(impl.builtInResult)));
  } 
  
  public int hashCode()
  {
    int hash = 78;
    hash = hash + super.hashCode();
    hash = hash + (null == builtInResult ? 0 : builtInResult.hashCode());
    return hash;
  }
}
