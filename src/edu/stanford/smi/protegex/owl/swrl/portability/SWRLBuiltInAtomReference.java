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


package edu.stanford.smi.protegex.owl.swrl.portability;

import java.util.List;
import java.util.Set;

import edu.stanford.smi.protegex.owl.swrl.bridge.BuiltInArgument;
import edu.stanford.smi.protegex.owl.swrl.bridge.exceptions.BuiltInException;

public interface SWRLBuiltInAtomReference extends SWRLAtomReference
{
	String getPredicate();

	List<BuiltInArgument> getArguments();

	// boolean isCoreBuiltin();

	String getBuiltInPrefixedName();

	int getBuiltInIndex();

	void setBuiltInIndex(int builtInIndex);

	boolean usesAtLeastOneVariableOf(Set<String> variableNames) throws BuiltInException;

	boolean isArgumentAVariable(int argumentNumber) throws BuiltInException;

	boolean isArgumentUnbound(int argumentNumber) throws BuiltInException;

	boolean hasUnboundArguments();

	Set<String> getUnboundArgumentVariableNames() throws BuiltInException;

	String getArgumentVariableName(int argumentNumber) throws BuiltInException;

	List<String> getArgumentsVariableNames() throws BuiltInException;

	List<String> getArgumentsVariableNamesExceptFirst() throws BuiltInException;

	Set<String> getPathVariableNames(); // Indicates variables that this built-in depends on (directly or indirectly)

	void addArguments(List<BuiltInArgument> additionalArguments);

	boolean usesSQWRLCollectionResults();

	boolean isSQWRLBuiltIn();

	boolean isSQWRLMakeCollection();

	boolean isSQWRLGroupCollection();

	boolean isSQWRLCollectionOperation();

	boolean isSQWRLCollectionCreateOperation();

	void setUsesSQWRLCollectionResults();

	void setPathVariableNames(Set<String> variableNames);
}
