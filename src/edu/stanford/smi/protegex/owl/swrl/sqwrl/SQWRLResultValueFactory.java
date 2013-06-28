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

import edu.stanford.smi.protegex.owl.swrl.bridge.xsd.XSDType;
import edu.stanford.smi.protegex.owl.swrl.sqwrl.exceptions.DataValueConversionException;
import edu.stanford.smi.protegex.owl.swrl.sqwrl.impl.ClassValueImpl;
import edu.stanford.smi.protegex.owl.swrl.sqwrl.impl.DataPropertyValueImpl;
import edu.stanford.smi.protegex.owl.swrl.sqwrl.impl.DataValueImpl;
import edu.stanford.smi.protegex.owl.swrl.sqwrl.impl.IndividualValueImpl;
import edu.stanford.smi.protegex.owl.swrl.sqwrl.impl.ObjectPropertyValueImpl;

public class SQWRLResultValueFactory 
{
	public ClassValue createClassValue(String classURI) { return new ClassValueImpl(classURI); }
  public IndividualValue createIndividualValue(String individualURI) { return new IndividualValueImpl(individualURI); }
  public ObjectPropertyValue createObjectPropertyValue(String propertyURI) { return new ObjectPropertyValueImpl(propertyURI); }
  public DataPropertyValue createDataPropertyValue(String propertyURI) { return new DataPropertyValueImpl(propertyURI); }
  
  public DataValue createDataValue(String s) { return new DataValueImpl(s); }
  public DataValue createDataValue(boolean b) { return new DataValueImpl(b); }
  public DataValue createDataValue(Boolean b) { return new DataValueImpl(b); }
  public DataValue createDataValue(int i) { return new DataValueImpl(i); }
  public DataValue createDataValue(long l) { return new DataValueImpl(l); }
  public DataValue createDataValue(float f) { return new DataValueImpl(f); }
  public DataValue createDataValue(double d) { return new DataValueImpl(d); }
  public DataValue createDataValue(short s) { return new DataValueImpl(s); }
  public DataValue createDataValue(XSDType xsd) { return new DataValueImpl(xsd); }
  public DataValue createDataValue(Object o) throws DataValueConversionException { return new DataValueImpl(o); }
}
