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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import edu.stanford.smi.protegex.owl.swrl.bridge.BuiltInArgument;
import edu.stanford.smi.protegex.owl.swrl.bridge.exceptions.BuiltInException;
import edu.stanford.smi.protegex.owl.swrl.portability.SWRLBuiltInAtomReference;
import edu.stanford.smi.protegex.owl.swrl.portability.SWRLLiteralArgumentReference;
import edu.stanford.smi.protegex.owl.swrl.sqwrl.DataValue;
import edu.stanford.smi.protegex.owl.swrl.sqwrl.SQWRLNames;

/**
 * Class representing a SWRL built-in atom
 */
public class P3SWRLBuiltInAtomReference extends P3SWRLAtomReference implements SWRLBuiltInAtomReference
{
	private String builtInURI, builtInPrefixedName;
	private List<BuiltInArgument> arguments;
	private int builtInIndex = -1; // Index of this built-in atom in rule body; left-to-right, first built-in index is 0, second in 1, and so on
	private boolean sqwrlCollectionResultsUsed = false;
	private Set<String> pathVariableNames = new HashSet<String>();

	public P3SWRLBuiltInAtomReference(String builtInURI, String builtInPrefixedName, List<BuiltInArgument> arguments)
	{
		this.builtInURI = builtInURI;
		this.builtInPrefixedName = builtInPrefixedName;
		this.arguments = arguments;
	}

	public P3SWRLBuiltInAtomReference(String builtInURI, String builtInPrefixedName)
	{
		this.builtInURI = builtInURI;
		this.builtInPrefixedName = builtInPrefixedName;
		this.arguments = new ArrayList<BuiltInArgument>();
	}

	public void setBuiltInArguments(List<BuiltInArgument> arguments)
	{
		this.arguments = arguments;
	}

	public String getPredicate()
	{
		return builtInURI;
	}

	public String getBuiltInPrefixedName()
	{
		return builtInPrefixedName;
	}

	public List<BuiltInArgument> getArguments()
	{
		return arguments;
	}

	public int getNumberOfArguments()
	{
		return arguments.size();
	}

	public int getBuiltInIndex()
	{
		return builtInIndex;
	}

	public void setBuiltInIndex(int builtInIndex)
	{
		this.builtInIndex = builtInIndex;
	}

	public Set<String> getPathVariableNames()
	{
		return pathVariableNames;
	}

	public boolean usesSQWRLCollectionResults()
	{
		return sqwrlCollectionResultsUsed;
	}

	public boolean isSQWRLBuiltIn()
	{
		return SQWRLNames.isSQWRLBuiltIn(builtInURI);
	}

	public boolean isSQWRLMakeCollection()
	{
		return SQWRLNames.isSQWRLCollectionMakeBuiltIn(builtInURI);
	}

	public boolean isSQWRLGroupCollection()
	{
		return SQWRLNames.isSQWRLCollectionGroupByBuiltIn(builtInURI);
	}

	public boolean isSQWRLCollectionOperation()
	{
		return SQWRLNames.isSQWRLCollectionOperationBuiltIn(builtInURI);
	}

	public boolean isSQWRLCollectionCreateOperation()
	{
		return SQWRLNames.isSQWRLCollectionCreateOperationBuiltIn(builtInURI);
	}

	public void setUsesSQWRLCollectionResults()
	{
		sqwrlCollectionResultsUsed = true;
	}

	public boolean usesAtLeastOneVariableOf(Set<String> variableNames) throws BuiltInException
	{
		Set<String> s = new HashSet<String>(variableNames);

		s.retainAll(getArgumentsVariableNames());

		return !s.isEmpty();
	}

	public boolean isArgumentAVariable(int argumentNumber) throws BuiltInException
	{
		checkArgumentNumber(argumentNumber);

		return arguments.get(argumentNumber).isVariable();
	}

	public boolean isArgumentUnbound(int argumentNumber) throws BuiltInException
	{
		checkArgumentNumber(argumentNumber);

		return arguments.get(argumentNumber).isUnbound();
	}

	public boolean hasUnboundArguments()
	{
		for (BuiltInArgument argument : arguments)
			if (argument.isUnbound())
				return true;
		return false;
	}

	public Set<String> getUnboundArgumentVariableNames() throws BuiltInException
	{
		Set<String> result = new HashSet<String>();

		for (BuiltInArgument argument : arguments)
			if (argument.isUnbound())
				result.add(argument.getVariableName());

		return result;
	}

	public String getArgumentVariableName(int argumentNumber) throws BuiltInException
	{
		checkArgumentNumber(argumentNumber);

		if (!arguments.get(argumentNumber).isVariable())
			throw new BuiltInException("expecting a variable for (0-offset) argument #" + argumentNumber);

		return arguments.get(argumentNumber).getVariableName();
	}

	public List<String> getArgumentsVariableNames() throws BuiltInException
	{
		List<String> result = new ArrayList<String>();

		for (BuiltInArgument argument : arguments)
			if (argument.isVariable())
				result.add(argument.getVariableName());

		return result;
	}

	public List<String> getArgumentsVariableNamesExceptFirst() throws BuiltInException
	{
		List<String> result = new ArrayList<String>();
		int argumentCount = 0;

		for (BuiltInArgument argument : arguments)
			if (argument.isVariable() && argumentCount++ != 0)
				result.add(argument.getVariableName());

		return result;
	}

	public void addArguments(List<BuiltInArgument> additionalArguments)
	{
		arguments.addAll(additionalArguments);
	}

	public void setPathVariableNames(Set<String> variableNames)
	{
		pathVariableNames = variableNames;
	}

	private void checkArgumentNumber(int argumentNumber) throws BuiltInException
	{
		if (argumentNumber < 0 || argumentNumber > arguments.size())
			throw new BuiltInException("invalid (0-offset) argument #" + argumentNumber);
	}

	public String toString()
	{
		String result = builtInPrefixedName + "(";
		boolean isFirst = true;

		for (BuiltInArgument argument : getArguments()) {
			if (!isFirst)
				result += ", ";
			if (argument instanceof SWRLLiteralArgumentReference) {
				SWRLLiteralArgumentReference dataValueArgument = (SWRLLiteralArgumentReference)argument;
				DataValue dataValue = dataValueArgument.getLiteral();
				if (dataValue.isString())
					result += "\"" + dataValue + "\"";
				else
					result += "" + dataValue;
			} else
				result += "" + argument;
			isFirst = false;
		} // for

		result += ")";

		return result;
	}
}
