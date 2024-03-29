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


package edu.stanford.smi.protegex.owl.swrl.ui.table;

import java.util.Set;

import org.jdesktop.swingx.treetable.DefaultMutableTreeTableNode;
import org.jdesktop.swingx.treetable.DefaultTreeTableModel;

import edu.stanford.smi.protegex.owl.swrl.bridge.exceptions.OWLFactoryException;
import edu.stanford.smi.protegex.owl.swrl.portability.OWLDataFactory;
import edu.stanford.smi.protegex.owl.swrl.portability.SWRLRuleReference;

public class SWRLRuleGroupTreeTableModel extends DefaultTreeTableModel
{
	public static final int RuleGroupColumn = 0;
	public static final int IsEnabledColumn = 1;
	public static final int RuleTextColumn = 2;
	private static final int NumberOfColumns = 3;
	private DefaultMutableTreeTableNode rootNode;
	private Set<SWRLRuleReference> rules;

	public SWRLRuleGroupTreeTableModel(OWLDataFactory owlFactory) throws OWLFactoryException
	{
		rootNode = new DefaultMutableTreeTableNode(new SWRLRuleGroup()); // Not visible; dummy rule group
		setRoot(rootNode);
		rules = owlFactory.getSWRLRules();
		addRules(rules);
	}

	public void addRule(SWRLRuleReference rule)
	{
		boolean existingGroupFound = false;

		for (int i = 0; i < rootNode.getChildCount() && !existingGroupFound; i++) { // Find existing group and add this rule
			if (getChild(rootNode, i) instanceof DefaultMutableTreeTableNode) {
				DefaultMutableTreeTableNode defNode = (DefaultMutableTreeTableNode)getChild(rootNode, i);
				if (defNode.getUserObject() instanceof SWRLRuleGroup) {
					SWRLRuleGroup ruleGroup = (SWRLRuleGroup)defNode.getUserObject();
					if (rule.getRuleGroupName().equals(ruleGroup.getGroupName())) {
						defNode.add(new DefaultMutableTreeTableNode(rule));
						existingGroupFound = true;
					}
				}
			}
		}

		if (!existingGroupFound) {
			DefaultMutableTreeTableNode groupNode = new DefaultMutableTreeTableNode(new SWRLRuleGroup(rule.getRuleGroupName(), true));
			groupNode.add(new DefaultMutableTreeTableNode(rule));
			rootNode.add(groupNode);
		}
	}

	public void addRules(Set<SWRLRuleReference> rules)
	{
		for (SWRLRuleReference rule : rules)
			addRule(rule);
	}

	public int getColumnCount()
	{
		return NumberOfColumns;
	}

	public String getColumnName(int column)
	{
		String result = "";

		switch (column) {
		case RuleGroupColumn:
			result = "Group";
			break;
		case IsEnabledColumn:
			result = "Enabled";
			break;
		case RuleTextColumn:
			result = "Expression";
			break;
		} 

		return result;
	}

	public Object getValueAt(Object node, int column)
	{
		Object result = null;

		if (node instanceof DefaultMutableTreeTableNode) {
			DefaultMutableTreeTableNode defNode = (DefaultMutableTreeTableNode)node;
			if (defNode.getUserObject() instanceof SWRLRuleReference) {
				SWRLRuleReference rule = (SWRLRuleReference)defNode.getUserObject();
				switch (column) {
				case IsEnabledColumn:
					result = rule.isEnabled();
					break;
				case RuleTextColumn:
					result = rule.getRuleText();
					break;
				} // switch
			} else if (defNode.getUserObject() instanceof SWRLRuleGroup) {
				SWRLRuleGroup ruleGroup = (SWRLRuleGroup)defNode.getUserObject();
				switch (column) {
				case RuleGroupColumn:
					result = ruleGroup.getGroupName();
					break;
				case IsEnabledColumn:
					result = ruleGroup.getIsEnabled();
					break;
				} // switch
			}
		}
		return result;
	}

	public boolean isCellEditable(Object node, int column)
	{
		boolean result = false;

		if (node instanceof DefaultMutableTreeTableNode) {
			DefaultMutableTreeTableNode defNode = (DefaultMutableTreeTableNode)node;
			if (defNode.getUserObject() instanceof SWRLRuleReference) {
				// SWRLRule rule = (SWRLRule)defNode.getUserObject();
				switch (column) {
				case IsEnabledColumn:
				case RuleTextColumn:
					result = true;
					break;
				} // switch
			} else if (defNode.getUserObject() instanceof SWRLRuleGroup) {
				// SWRLRuleGroup ruleGroup = (SWRLRuleGroup)defNode.getUserObject();
				switch (column) {
				case RuleGroupColumn:
					result = true;
					break;
				case IsEnabledColumn:
					result = true;
					break;
				} 
			}
		}
		return result;
	}

	public void setValueAt(Object value, Object node, int column)
	{
		if (node instanceof DefaultMutableTreeTableNode) {
			DefaultMutableTreeTableNode defNode = (DefaultMutableTreeTableNode)node;
			if (defNode.getUserObject() instanceof SWRLRuleReference) {
				SWRLRuleReference rule = (SWRLRuleReference)defNode.getUserObject();
				switch (column) {
				case IsEnabledColumn:
					System.err.println("rule enable toggled");
					rule.setEnabled((Boolean)value);
					defNode.setUserObject(rule);
					break;
				case RuleTextColumn:
					System.err.println("rule text changed: " + value);
					rule.setRuleText(value.toString());
					defNode.setUserObject(rule);
					break;
				} // switch
			} else if (defNode.getUserObject() instanceof SWRLRuleGroup) {
				SWRLRuleGroup ruleGroup = (SWRLRuleGroup)defNode.getUserObject();
				switch (column) {
				case RuleGroupColumn:
					System.err.println("rule group name changed: " + value);
					ruleGroup.setGroupName(value.toString());
					defNode.setUserObject(ruleGroup);
					break;
				case IsEnabledColumn:
					System.err.println("rule group enable toggled");
					ruleGroup.setIsEnabled((Boolean)value);
					defNode.setUserObject(ruleGroup);
					break;
				} 
			}
		}
	}
}
