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


package edu.stanford.smi.protegex.owl.swrl.portability.p3;

import edu.stanford.smi.protegex.owl.swrl.portability.OWLDataPropertyReference;
import edu.stanford.smi.protegex.owl.swrl.portability.SWRLArgumentReference;
import edu.stanford.smi.protegex.owl.swrl.portability.SWRLDataPropertyAtomReference;
import edu.stanford.smi.protegex.owl.swrl.portability.SWRLLiteralArgumentReference;
import edu.stanford.smi.protegex.owl.swrl.sqwrl.DataValue;

/**
 * Class representing a SWRL data valued property atom
 */
public class P3SWRLDataPropertyAtomReference extends P3SWRLBinaryAtomReference implements SWRLDataPropertyAtomReference
{
	private OWLDataPropertyReference property;

	public P3SWRLDataPropertyAtomReference(OWLDataPropertyReference property, SWRLArgumentReference argument1, SWRLArgumentReference argument2)
	{
		super(argument1, argument2);
		this.property = property;
	}

	public P3SWRLDataPropertyAtomReference(OWLDataPropertyReference property)
	{
		this.property = property;
	}

	public OWLDataPropertyReference getProperty()
	{
		return property;
	}

	public String toString()
	{
		String result = "" + getProperty() + "(" + getFirstArgument() + ", ";

		if (getSecondArgument() instanceof SWRLLiteralArgumentReference) {
			SWRLLiteralArgumentReference dataValueArgument = (SWRLLiteralArgumentReference)getSecondArgument();
			DataValue dataValue = dataValueArgument.getLiteral();
			if (dataValue.isString())
				result += "\"" + dataValue + "\"";
			else
				result += "" + dataValue;
		} else
			result += "" + getSecondArgument();

		result += ")";

		return result;
	}
}
