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
import edu.stanford.smi.protegex.owl.swrl.bridge.OWLDataValue;
import edu.stanford.smi.protegex.owl.swrl.bridge.OWLPropertyPropertyAssertionAxiomReference;
import edu.stanford.smi.protegex.owl.swrl.portability.OWLAxiomReference;
import edu.stanford.smi.protegex.owl.swrl.portability.OWLClassPropertyAssertionAxiomReference;
import edu.stanford.smi.protegex.owl.swrl.portability.OWLDataPropertyAssertionAxiomReference;
import edu.stanford.smi.protegex.owl.swrl.portability.OWLObjectPropertyAssertionAxiomReference;
import edu.stanford.smi.protegex.owl.swrl.portability.OWLPropertyAssertionAxiomReference;

public class InferredAxiomsPanel extends JPanel 
{
  private SWRLRuleEngine ruleEngine;
  private InferredAxiomsModel inferredAxiomsModel;
  private JTable table;

  public InferredAxiomsPanel(SWRLRuleEngine ruleEngine) 
  {
    this.ruleEngine = ruleEngine;

    inferredAxiomsModel = new InferredAxiomsModel();
    table = new JTable(inferredAxiomsModel);

    setLayout(new BorderLayout());

    JScrollPane scrollPane = new JScrollPane(table);
    JViewport viewPort = scrollPane.getViewport();
    viewPort.setBackground(table.getBackground());

    add(BorderLayout.CENTER, scrollPane);
  } 

  public void validate() { inferredAxiomsModel.fireTableDataChanged(); super.validate(); }
  
  private class InferredAxiomsModel extends AbstractTableModel
  {
    public int getRowCount() { return ruleEngine.getNumberOfInferredOWLAxioms(); }
    public int getColumnCount() { return 1; }
    public String getColumnName(int column) { return "Inferred Axioms"; }

    public Object getValueAt(int row, int column) 
    { 
      String result = "";

      if (row < 0 || row >= getRowCount()) result = new String("OUT OF BOUNDS");
      else {
        OWLAxiomReference axiom = (OWLAxiomReference)ruleEngine.getInferredOWLAxioms().toArray()[row];

        if (axiom instanceof OWLPropertyAssertionAxiomReference) {
          OWLPropertyAssertionAxiomReference propertyAssertionAxiom = (OWLPropertyAssertionAxiomReference)axiom;
          result = propertyAssertionAxiom.getProperty().getURI() + "(" + propertyAssertionAxiom.getSubject().getURI() + ", ";
          
          if (axiom instanceof OWLObjectPropertyAssertionAxiomReference) {
            OWLObjectPropertyAssertionAxiomReference objectAxiom = (OWLObjectPropertyAssertionAxiomReference)axiom;
            result += ruleEngine.uri2PrefixedName(objectAxiom.getObject().getURI());
          } else if (axiom instanceof OWLDataPropertyAssertionAxiomReference) {
            OWLDataPropertyAssertionAxiomReference dataAxiom = (OWLDataPropertyAssertionAxiomReference)axiom;
            OWLDataValue dataValue = ruleEngine.getOWLDataValueFactory().getOWLDataValue(dataAxiom.getObject());
            if (dataValue.isString() || dataValue.isXSDType()) result += "\"" + dataAxiom.getObject() + "\"";
            else result += dataAxiom.getObject();
          } else if (axiom instanceof OWLClassPropertyAssertionAxiomReference) {
            OWLClassPropertyAssertionAxiomReference classAxiom = (OWLClassPropertyAssertionAxiomReference)axiom;
            result += ruleEngine.uri2PrefixedName(classAxiom.getObject().getURI());
          } else if (axiom instanceof OWLPropertyPropertyAssertionAxiomReference) {
            OWLPropertyPropertyAssertionAxiomReference propertyAxiom = (OWLPropertyPropertyAssertionAxiomReference)axiom;
            result += ruleEngine.uri2PrefixedName(propertyAxiom.getObject().getURI());
          } // if
          result += ")";
        } else result = axiom.toString();
      } // if

      return result;
    } 
  } 
} 
