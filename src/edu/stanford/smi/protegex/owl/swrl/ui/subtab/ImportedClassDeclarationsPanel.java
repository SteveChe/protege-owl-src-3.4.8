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

package edu.stanford.smi.protegex.owl.swrl.ui.subtab;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.table.AbstractTableModel;

import edu.stanford.smi.protegex.owl.swrl.SWRLRuleEngine;
import edu.stanford.smi.protegex.owl.swrl.portability.OWLClassReference;

public class ImportedClassDeclarationsPanel extends JPanel 
{
  private SWRLRuleEngine ruleEngine;
  private ImportedClasseDeclarationsModel importedClassesModel;
  private JTable table;

  public ImportedClassDeclarationsPanel(SWRLRuleEngine ruleEngine) 
  {
    this.ruleEngine = ruleEngine;

    importedClassesModel = new ImportedClasseDeclarationsModel();
    table = new JTable(importedClassesModel);

    setLayout(new BorderLayout());

    JScrollPane scrollPane = new JScrollPane(table);
    JViewport viewPort = scrollPane.getViewport();
    viewPort.setBackground(table.getBackground());

    add(BorderLayout.CENTER, scrollPane);
  } 

  public void validate() { importedClassesModel.fireTableDataChanged(); super.validate(); }

  private class ImportedClasseDeclarationsModel extends AbstractTableModel
  {
    public int getRowCount() { return ruleEngine.getNumberOfImportedOWLClasses(); }
    public int getColumnCount() { return 1; }
    public String getColumnName(int column) { return "Imported OWL Classes"; }

    public Object getValueAt(int row, int column) 
    { 
      Object result = null;

      if (row < 0 || row >= getRowCount()) result = new String("OUT OF BOUNDS");
      else {
    	  String classURI =  ruleEngine.getImportedOWLClasses().toArray(new OWLClassReference[0])[row].getURI();
    	  result =  ruleEngine.uri2PrefixedName(classURI);
      } // if

      return result;
    } 
  }
}
