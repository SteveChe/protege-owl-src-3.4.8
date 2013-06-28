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

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractAction;
import javax.swing.JPopupMenu;
import javax.swing.tree.TreePath;

import org.jdesktop.swingx.JXTreeTable;
import org.jdesktop.swingx.treetable.DefaultMutableTreeTableNode;

import edu.stanford.smi.protegex.owl.swrl.portability.SWRLRuleReference;

public class SWRLRuleGroupTreeTable extends JXTreeTable
{
	SWRLRuleGroupTreeTableModel model;
	
	public SWRLRuleGroupTreeTable(SWRLRuleGroupTreeTableModel model)
	{
		super(model);
		this.model = model;
		setTreeCellRenderer(new SWRLRuleGroupTreeCellRenderer());
		setEditable(true);
		//treeTable.setLeaf, setClosed, setOpen icons
		createPopupMenu();

		getColumnModel().getColumn(SWRLRuleGroupTreeTableModel.RuleGroupColumn).setPreferredWidth(100);
		getColumnModel().getColumn(SWRLRuleGroupTreeTableModel.IsEnabledColumn).setMaxWidth(60);
    getColumnModel().getColumn(SWRLRuleGroupTreeTableModel.RuleTextColumn).setPreferredWidth(700);
   } 

	private void createPopupMenu() 
	{
		JPopupMenu popup = new JPopupMenu();
		popup.add(new EnableAllRulesAction());      
		addMouseListener(new PopupListener(popup));
	} 

	private class PopupListener extends MouseAdapter 
	{
		JPopupMenu popup;
		
		PopupListener(JPopupMenu popupMenu) { popup = popupMenu; }

		public void mousePressed(MouseEvent e) { maybeShowPopup(e); }
		public void mouseReleased(MouseEvent e) { maybeShowPopup(e);  }
		private void maybeShowPopup(MouseEvent e) { if (e.isPopupTrigger()) popup.show(e.getComponent(), e.getX(), e.getY()); }
  } 
    
	private class EnableAllRulesAction extends AbstractAction
	{
		public EnableAllRulesAction() { super("Enable all rules"); }
		
		public void actionPerformed(ActionEvent e)
		{
			System.err.println("Selected row?");
			System.err.println("selected: " + getSelectedRow());
			
			TreePath path = getPathForRow(getSelectedRow());
			Object component = path.getLastPathComponent();
			
			System.err.println("path: " + path);
			
			if (component instanceof DefaultMutableTreeTableNode) {
				DefaultMutableTreeTableNode defNode = (DefaultMutableTreeTableNode)component;
				if (defNode.getUserObject() instanceof SWRLRuleGroup) {
					SWRLRuleGroup ruleGroup = (SWRLRuleGroup)defNode.getUserObject();
					System.err.println("RuleGroup.name: " + ruleGroup.getGroupName());
				} else if (defNode.getUserObject() instanceof SWRLRuleReference) {
					SWRLRuleReference rule = (SWRLRuleReference)defNode.getUserObject();
					System.err.println("Rule.name: " + rule.getURI());
				} // if
			} // if         
		} 
	} 
} 
