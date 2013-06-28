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

import edu.stanford.smi.protegex.owl.swrl.bridge.BuiltInArgument;
import edu.stanford.smi.protegex.owl.swrl.bridge.impl.BuiltInArgumentImpl;
import edu.stanford.smi.protegex.owl.swrl.portability.SWRLIndividualArgumentReference;

public class P3SWRLIndividualArgumentReference extends BuiltInArgumentImpl implements SWRLIndividualArgumentReference
{
	private String individualURI;

	public P3SWRLIndividualArgumentReference(String individualURI)
	{
		this.individualURI = individualURI;
	}

	public String getURI()
	{
		return individualURI;
	}

	public String toString()
	{
		return getURI();
	}

	public int compareTo(BuiltInArgument o)
	{
		return individualURI.compareTo(((SWRLIndividualArgumentReference)o).getURI());
	}

	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if ((obj == null) || (obj.getClass() != this.getClass()))
			return false;
		P3SWRLIndividualArgumentReference impl = (P3SWRLIndividualArgumentReference)obj;
		return (getURI() == impl.getURI() || (getURI() != null && getURI().equals(impl.getURI())));
	}

	public int hashCode()
	{
		int hash = 12;
		hash = hash + (null == getURI() ? 0 : getURI().hashCode());
		return hash;
	}
}