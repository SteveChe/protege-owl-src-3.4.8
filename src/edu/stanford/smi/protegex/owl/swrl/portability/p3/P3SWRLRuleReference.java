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

import java.util.List;

import edu.stanford.smi.protegex.owl.swrl.portability.SWRLAtomReference;
import edu.stanford.smi.protegex.owl.swrl.portability.SWRLRuleReference;

public class P3SWRLRuleReference implements SWRLRuleReference
{
	private String rulePrefixedName;
	private List<SWRLAtomReference> bodyAtoms, headAtoms;

	public P3SWRLRuleReference(String rulePrefixedName, List<SWRLAtomReference> bodyAtoms, List<SWRLAtomReference> headAtoms)
	{
		this.rulePrefixedName = rulePrefixedName;
		this.bodyAtoms = bodyAtoms;
		this.headAtoms = headAtoms;
	}

	public String getURI()
	{
		return rulePrefixedName;
	}

	public List<SWRLAtomReference> getHeadAtoms()
	{
		return headAtoms;
	}

	public List<SWRLAtomReference> getBodyAtoms()
	{
		return bodyAtoms;
	}

	public void setURI(String ruleURI)
	{
		this.rulePrefixedName = ruleURI;
	}

	public void setRuleText(String text)
	{
	}

	public String getRuleGroupName()
	{
		return "";
	}

	public void appendAtomsToBody(List<SWRLAtomReference> atoms)
	{
		bodyAtoms.addAll(atoms);
	}

	public void setBodyAtoms(List<SWRLAtomReference> atoms)
	{
		bodyAtoms = atoms;
	}

	public String toString()
	{
		return rulePrefixedName;
	}

	public boolean isEnabled()
	{
		return true;
	} // TODO - used only in SWRLRuleGroupTreeTableModel

	public void setEnabled(boolean isEnabled)
	{
	} // TODO - used only in SWRLRuleGroupTreeTableModel

	public String getRuleText()
	{
		String result = "";
		boolean isFirst = true;

		for (SWRLAtomReference atom : getBodyAtoms()) {
			if (!isFirst)
				result += " ^ ";
			result += "" + atom;
			isFirst = false;
		} // for

		result += " -> ";

		isFirst = true;
		for (SWRLAtomReference atom : getHeadAtoms()) {
			if (!isFirst)
				result += " ^ ";
			result += "" + atom;
			isFirst = false;
		} // for

		return result;
	}
}
