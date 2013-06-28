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

import edu.stanford.smi.protegex.owl.swrl.bridge.Argument;

/**
 * Class representing argument to SWRL built-ins and SWRL atoms
 */
public abstract class ArgumentImpl implements Argument
{
	// There is an equals methods defined for this class.
	private String variableName;
	private boolean isAVariable;
	private boolean isArgumentUnbound;

	public ArgumentImpl()
	{
		variableName = "";
		isAVariable = false;
		isArgumentUnbound = false;
	}

	public ArgumentImpl(String variableName)
	{
		this.variableName = variableName;
		isAVariable = true;
		isArgumentUnbound = false;
	}

	public boolean isVariable()
	{
		return isAVariable;
	}

	public void setVariableName(String variableName)
	{
		this.variableName = variableName;
		isAVariable = true;
	}

	public String getVariableName()
	{
		return variableName;
	}

	public void setUnbound()
	{
		isArgumentUnbound = true;
	}

	public void setBound()
	{
		isArgumentUnbound = false;
	}

	public boolean isUnbound()
	{
		return isArgumentUnbound;
	}

	public boolean isBound()
	{
		return !isArgumentUnbound;
	}

	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if ((obj == null) || (obj.getClass() != this.getClass()))
			return false;
		ArgumentImpl impl = (ArgumentImpl)obj;
		return (((variableName == impl.variableName || variableName != null && variableName.equals(impl.variableName))) && (isAVariable == impl.isAVariable) && (isArgumentUnbound == impl.isArgumentUnbound));
	}

	public int hashCode()
	{
		int hash = 78;
		hash = hash + (null == variableName ? 0 : variableName.hashCode());
		hash = hash + (isAVariable ? 0 : 1);
		hash = hash + (isArgumentUnbound ? 0 : 1);
		return hash;
	}
}
