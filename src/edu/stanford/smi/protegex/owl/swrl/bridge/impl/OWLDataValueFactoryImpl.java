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

import edu.stanford.smi.protegex.owl.swrl.bridge.OWLDataValueFactory;
import edu.stanford.smi.protegex.owl.swrl.bridge.OWLDataValue;
import edu.stanford.smi.protegex.owl.swrl.bridge.xsd.XSDType;
import edu.stanford.smi.protegex.owl.swrl.portability.OWLLiteralReference;
import edu.stanford.smi.protegex.owl.swrl.sqwrl.DataValue;
import edu.stanford.smi.protegex.owl.swrl.sqwrl.exceptions.DataValueConversionException;

public class OWLDataValueFactoryImpl extends OWLDataValueFactory 
{
	public OWLDataValue getOWLDataValue(OWLLiteralReference literal) { return (OWLDataValue)literal; } // TODO: reimplement for P4 
	public OWLDataValue getOWLDataValue(DataValue dataValue) { return new OWLDataValueImpl(dataValue); }
	public OWLDataValue getOWLDataValue(String s) { return new OWLDataValueImpl(s); }
  public OWLDataValue getOWLDataValue(boolean b){ return new OWLDataValueImpl(b); }
  public OWLDataValue getOWLDataValue(Boolean b){ return new OWLDataValueImpl(b); }
  public OWLDataValue getOWLDataValue(int i) { return new OWLDataValueImpl(i); }
  public OWLDataValue getOWLDataValue(long l) { return new OWLDataValueImpl(l); }
  public OWLDataValue getOWLDataValue(float f) { return new OWLDataValueImpl(f); }
  public OWLDataValue getOWLDataValue(double d){ return new OWLDataValueImpl(d); }
  public OWLDataValue getOWLDataValue(short s) { return new OWLDataValueImpl(s); }
  public OWLDataValue getOWLDataValue(Byte b) { return new OWLDataValueImpl(b); }
  public OWLDataValue getOWLDataValue(XSDType xsd) { return new OWLDataValueImpl(xsd); }
  public OWLDataValue getOWLDataValue(Object o) throws DataValueConversionException { return new OWLDataValueImpl(o); } 
}
