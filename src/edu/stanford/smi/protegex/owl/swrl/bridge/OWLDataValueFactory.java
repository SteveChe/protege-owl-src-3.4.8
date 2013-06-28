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

import edu.stanford.smi.protegex.owl.swrl.bridge.impl.OWLDataValueFactoryImpl;
import edu.stanford.smi.protegex.owl.swrl.bridge.xsd.XSDType;
import edu.stanford.smi.protegex.owl.swrl.portability.OWLLiteralReference;
import edu.stanford.smi.protegex.owl.swrl.sqwrl.DataValue;
import edu.stanford.smi.protegex.owl.swrl.sqwrl.exceptions.DataValueConversionException;

/**
 * The SWRLTab deals with literals using its local OWLDataValue class. This class wraps the less convenient OWLLiteral and OWLDataType classes.
 */
public abstract class OWLDataValueFactory 
{
	public static OWLDataValueFactory create() { return new OWLDataValueFactoryImpl(); }
	
	public abstract OWLDataValue getOWLDataValue(OWLLiteralReference literal);
	public abstract OWLDataValue getOWLDataValue(DataValue dataValue);
	public abstract OWLDataValue getOWLDataValue(String s);
	public abstract OWLDataValue getOWLDataValue(boolean b);
	public abstract OWLDataValue getOWLDataValue(Boolean b);
	public abstract OWLDataValue getOWLDataValue(int i);
	public abstract OWLDataValue getOWLDataValue(long l);
	public abstract OWLDataValue getOWLDataValue(float f);
	public abstract OWLDataValue getOWLDataValue(double d);
	public abstract OWLDataValue getOWLDataValue(short s);
	public abstract OWLDataValue getOWLDataValue(Byte b);
	public abstract OWLDataValue getOWLDataValue(XSDType xsd);
	public abstract OWLDataValue getOWLDataValue(Object o) throws DataValueConversionException; // TODO: get rid of this
}
