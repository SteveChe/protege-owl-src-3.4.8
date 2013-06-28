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

package edu.stanford.smi.protegex.owlx.examples.javaDemo.model;

import edu.stanford.smi.protegex.owl.model.OWLIndividual;
import edu.stanford.smi.protegex.owl.model.RDFProperty;
import edu.stanford.smi.protegex.owl.model.RDFSLiteral;

/**
 * Generated by Protege-OWL  (http://protege.stanford.edu/plugins/owl).
 * Source OWL Class: http://www.owl-ontologies.com/javaDemo.owl#Purchase
 *
 * @version generated on Mon Feb 21 10:53:08 EST 2005
 */
public interface Purchase extends OWLIndividual {

    // Property http://www.owl-ontologies.com/javaDemo.owl#customer


    Customer getCustomer();


    RDFProperty getCustomerProperty();


    boolean hasCustomer();


    void setCustomer(Customer newCustomer);

    // Property http://www.owl-ontologies.com/javaDemo.owl#date


    RDFSLiteral getDate();


    RDFProperty getDateProperty();


    boolean hasDate();


    void setDate(RDFSLiteral newDate);

    // Property http://www.owl-ontologies.com/javaDemo.owl#product


    Product getProduct();


    RDFProperty getProductProperty();


    boolean hasProduct();


    void setProduct(Product newProduct);
}
