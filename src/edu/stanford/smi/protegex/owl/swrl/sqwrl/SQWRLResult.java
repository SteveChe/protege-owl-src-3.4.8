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

import edu.stanford.smi.protegex.owl.swrl.sqwrl.exceptions.SQWRLException;

import java.util.List;

/**
 * Interface that defines methods to process results from a SQWRL query. See <a
 * href="http://protege.cim3.net/cgi-bin/wiki.pl?SQWRLQueryAPI">here</a> for documentation.
 */
public interface SQWRLResult
{
  List<String> getColumnNames() throws SQWRLException;
  int getNumberOfColumns() throws SQWRLException;
  String getColumnName(int columnIndex) throws SQWRLException;

  boolean isEmpty() throws SQWRLException;
  int getNumberOfRows() throws SQWRLException;
  void reset() throws SQWRLException;
  void next() throws SQWRLException;
  boolean hasNext() throws SQWRLException;
  
  boolean hasObjectValue(String columnName) throws SQWRLException;
  boolean hasObjectValue(int columnIndex) throws SQWRLException;
  boolean hasDataValue(String columnName) throws SQWRLException;
  boolean hasDataValue(int columnIndex) throws SQWRLException;
  boolean hasClassValue(String columnName) throws SQWRLException;
  boolean hasClassValue(int columnIndex) throws SQWRLException;
  boolean hasPropertyValue(String columnName) throws SQWRLException;
  boolean hasPropertyValue(int columnIndex) throws SQWRLException;

  List<SQWRLResultValue> getRow() throws SQWRLException;
  SQWRLResultValue getValue(String columnName) throws SQWRLException;
  SQWRLResultValue getValue(int columnIndex) throws SQWRLException;
  SQWRLResultValue getValue(int columnIndex, int rowIndex) throws SQWRLException;
  IndividualValue getObjectValue(String columnName) throws SQWRLException;
  IndividualValue getObjectValue(int columnIndex) throws SQWRLException;
  DataValue getDataValue(String columnName) throws SQWRLException;
  DataValue getDataValue(int columnIndex) throws SQWRLException;
  ClassValue getClassValue(String columnName) throws SQWRLException;
  ClassValue getClassValue(int columnIndex) throws SQWRLException;
  PropertyValue getPropertyValue(String columnName) throws SQWRLException;
  PropertyValue getPropertyValue(int columnIndex) throws SQWRLException;

  List<SQWRLResultValue> getColumn(String columnName) throws SQWRLException;
  List<SQWRLResultValue> getColumn(int columnIndex) throws SQWRLException;
}
