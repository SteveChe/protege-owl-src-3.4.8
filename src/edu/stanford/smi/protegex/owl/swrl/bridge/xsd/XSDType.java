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

import java.net.URISyntaxException;

import edu.stanford.smi.protegex.owl.swrl.sqwrl.exceptions.DataValueConversionException;

public abstract class XSDType implements Comparable<XSDType>
{
  private String content;
  private java.net.URI uri = null;

  public XSDType(String content) throws DataValueConversionException
  {
    this.content = content;
    validate();
  } // XSDType

  public String getContent() { return content; }

  public String toString() { return content; }

  public java.net.URI getURI() { return uri; }

  public int compareTo(XSDType xsdType)
  {
    return content.compareTo(xsdType.getContent()); 
  } // compareTo

  protected abstract void validate() throws DataValueConversionException;

  protected void setURI(String uriString) throws DataValueConversionException
  {
    try {
      uri = new java.net.URI(uriString);
    } catch (URISyntaxException e) {
      throw new DataValueConversionException("invalid URI " + uri + " associated with value " + content + "");
    } // try
  } // setURI

} // XSDType
