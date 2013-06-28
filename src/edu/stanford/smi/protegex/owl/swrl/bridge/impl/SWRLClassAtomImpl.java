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

import edu.stanford.smi.protegex.owl.swrl.portability.SWRLArgumentReference;
import edu.stanford.smi.protegex.owl.swrl.portability.SWRLClassAtomReference;
import edu.stanford.smi.protegex.owl.swrl.portability.p3.P3SWRLAtomReference;

public class SWRLClassAtomImpl extends P3SWRLAtomReference implements SWRLClassAtomReference
{
	private SWRLArgumentReference argument1;
	private String classURI;

	public SWRLClassAtomImpl(String classURI, SWRLArgumentReference argument1)
	{
		this.classURI = classURI;
		this.argument1 = argument1;
	}

	public SWRLClassAtomImpl(String classURI)
	{
		this.classURI = classURI;
		this.argument1 = null;
	}

	public int getNumberOfArguments()
	{
		return 1;
	}

	public void setArgument1(SWRLArgumentReference argument1)
	{
		this.argument1 = argument1;
	}

	public String getClassURI()
	{
		return classURI;
	}

	public SWRLArgumentReference getArgument1()
	{
		return argument1;
	}

	public String toString()
	{
		return getClassURI() + "(" + getArgument1() + ")";
	}
}
