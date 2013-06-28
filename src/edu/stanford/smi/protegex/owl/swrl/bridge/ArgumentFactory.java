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

import java.util.List;

import edu.stanford.smi.protegex.owl.swrl.bridge.impl.ArgumentFactoryImpl;
import edu.stanford.smi.protegex.owl.swrl.bridge.xsd.XSDType;
import edu.stanford.smi.protegex.owl.swrl.portability.SWRLIndividualArgumentReference;
import edu.stanford.smi.protegex.owl.swrl.portability.SWRLLiteralArgumentReference;
import edu.stanford.smi.protegex.owl.swrl.portability.SWRLVariableReference;
import edu.stanford.smi.protegex.owl.swrl.sqwrl.DataValue;
import edu.stanford.smi.protegex.owl.swrl.sqwrl.exceptions.DataValueConversionException;

public abstract class ArgumentFactory
{
	public static ArgumentFactory getFactory()
	{
		return new ArgumentFactoryImpl();
	}

	public abstract SWRLVariableReference createVariableArgument(String variableName);

	public abstract ClassArgument createClassArgument(String classURI);
	public abstract SWRLIndividualArgumentReference createIndividualArgument(String individualURI);
	public abstract ObjectPropertyArgument createObjectPropertyArgument(String propertyURI);
	public abstract DataPropertyArgument createDataPropertyArgument(String propertyURI);

	public abstract SWRLLiteralArgumentReference createDataValueArgument(DataValue dataValue);

	public abstract SWRLLiteralArgumentReference createDataValueArgument(String s);
	public abstract SWRLLiteralArgumentReference createDataValueArgument(boolean b);
	public abstract SWRLLiteralArgumentReference createDataValueArgument(Boolean b);
	public abstract SWRLLiteralArgumentReference createDataValueArgument(int i);
	public abstract SWRLLiteralArgumentReference createDataValueArgument(long l);
	public abstract SWRLLiteralArgumentReference createDataValueArgument(float f);
	public abstract SWRLLiteralArgumentReference createDataValueArgument(double d);
	public abstract SWRLLiteralArgumentReference createDataValueArgument(short s);
	public abstract SWRLLiteralArgumentReference createDataValueArgument(Byte b);
	public abstract SWRLLiteralArgumentReference createDataValueArgument(XSDType xsd);
	public abstract SWRLLiteralArgumentReference createDataValueArgument(Object o) throws DataValueConversionException;

	public abstract MultiArgument createMultiArgument();
	public abstract MultiArgument createMultiArgument(List<BuiltInArgument> arguments);

	public abstract CollectionArgument createCollectionArgument(String collectionName, String collectionID);
}
