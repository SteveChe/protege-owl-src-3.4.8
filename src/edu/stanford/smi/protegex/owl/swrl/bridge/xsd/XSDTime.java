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


package edu.stanford.smi.protegex.owl.swrl.bridge.xsd;

import edu.stanford.smi.protegex.owl.model.XSDNames;
import edu.stanford.smi.protegex.owl.swrl.sqwrl.exceptions.DataValueConversionException;

public class XSDTime extends XSDType
{
  public XSDTime(String content) throws DataValueConversionException 
  { 
    super(content); 

    setURI(XSDNames.TIME);
  } // XSDTime

  public XSDTime(java.util.Date date) throws DataValueConversionException 
  { 
    super(XSDTimeUtil.date2XSDTimeString(date)); 

    setURI(XSDNames.TIME);
  } // XSDTime

  protected void validate() throws DataValueConversionException
  {
    if (getContent() == null) throw new DataValueConversionException("null content for xsd:Time");

    if (!XSDTimeUtil.isValidXSDTime(getContent())) throw new DataValueConversionException("invalid xsd:Time '" + getContent() + "'");
  } // validate

} // XSDTime

