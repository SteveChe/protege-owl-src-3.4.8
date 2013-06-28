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

import edu.stanford.smi.protegex.owl.swrl.portability.OWLPropertyReference;
import edu.stanford.smi.protegex.owl.swrl.portability.SWRLArgumentReference;
import edu.stanford.smi.protegex.owl.swrl.portability.SWRLBinaryAtomReference;

public class P3SWRLBinaryAtomReference extends P3SWRLAtomReference implements SWRLBinaryAtomReference
{
	private OWLPropertyReference property;
	private SWRLArgumentReference argument1, argument2;

	public P3SWRLBinaryAtomReference(OWLPropertyReference property)
	{
		this.property = property;
		this.argument1 = null;
		this.argument2 = null;
	}

	public P3SWRLBinaryAtomReference()
	{
		this.property = null;
		this.argument1 = null;
		this.argument2 = null;
	}

	public int getNumberOfArguments()
	{
		return 2;
	}

	public OWLPropertyReference getProperty()
	{
		return property;
	}

	public void setArgument1(SWRLArgumentReference argument1)
	{
		this.argument1 = argument1;
	}

	public void setArgument2(SWRLArgumentReference argument2)
	{
		this.argument2 = argument2;
	}

	public SWRLArgumentReference getFirstArgument()
	{
		return argument1;
	}

	public SWRLArgumentReference getSecondArgument()
	{
		return argument2;
	}

	protected P3SWRLBinaryAtomReference(SWRLArgumentReference argument1, SWRLArgumentReference argument2)
	{
		this.argument1 = argument1;
		this.argument2 = argument2;
	}
}
