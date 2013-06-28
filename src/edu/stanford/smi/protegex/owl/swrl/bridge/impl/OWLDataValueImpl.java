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

import edu.stanford.smi.protegex.owl.swrl.bridge.OWLDataValue;
import edu.stanford.smi.protegex.owl.swrl.bridge.xsd.XSDType;
import edu.stanford.smi.protegex.owl.swrl.sqwrl.DataValue;
import edu.stanford.smi.protegex.owl.swrl.sqwrl.exceptions.DataValueConversionException;
import edu.stanford.smi.protegex.owl.swrl.sqwrl.impl.DataValueImpl;

public class OWLDataValueImpl extends DataValueImpl implements OWLDataValue
{  
	public OWLDataValueImpl(String s) { super(s); }
  public OWLDataValueImpl(boolean b) { super(b); }
  public OWLDataValueImpl(Boolean b) { super(b); }
  public OWLDataValueImpl(int i) { super(i); }
  public OWLDataValueImpl(long l) { super(l); }
  public OWLDataValueImpl(float f) { super(f); }
  public OWLDataValueImpl(double d) { super(d); }
  public OWLDataValueImpl(short s) { super(s); }
  public OWLDataValueImpl(Byte b) { super(b); }
  public OWLDataValueImpl(XSDType xsd) { super(xsd); }

  public OWLDataValueImpl(DataValue dataValue) { super(dataValue); }
  public OWLDataValueImpl(Object o) throws DataValueConversionException { super(o); }
  
  public boolean isOWLStringLiteral() { return isString(); }
}
