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

import edu.stanford.smi.protegex.owl.swrl.portability.OWLLiteralReference;
import edu.stanford.smi.protegex.owl.swrl.portability.OWLPropertyValueReference;
import edu.stanford.smi.protegex.owl.swrl.portability.OWLTypedLiteralReference;
import edu.stanford.smi.protegex.owl.swrl.sqwrl.exceptions.DataValueConversionException;

/**
 * Convenience wrapper around OWLAPI classes OWLLiteral and OWLDataType
 */
public interface OWLDataValue extends OWLPropertyValueReference, OWLLiteralReference, OWLTypedLiteralReference 
{
  // Java types
  boolean isString();
  boolean isBoolean();
  boolean isNumeric();
  boolean isInteger();
  boolean isLong();
  boolean isFloat();
  boolean isDouble();
  boolean isShort();
  boolean isByte();
  
  boolean isXSDType();
  boolean isXSDTime();
  boolean isXSDDate();
  boolean isXSDDateTime();
  boolean isXSDDuration();
  boolean isXSDAnyURI();
  
  boolean isComparable();

  String getString() throws DataValueConversionException;
  boolean getBoolean() throws DataValueConversionException;
  int getInt() throws DataValueConversionException;
  long getLong() throws DataValueConversionException;
  float getFloat() throws DataValueConversionException;
  double getDouble() throws DataValueConversionException;
  short getShort() throws DataValueConversionException;
  byte getByte() throws DataValueConversionException;
  Number getNumber() throws DataValueConversionException;

  String toString();
  String toQuotedString();
}
