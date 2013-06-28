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

/* CVS $Id: AbsoluteLayoutNames.java,v 1.2 2005/12/31 14:08:16 matthewhorridge Exp $ */
package edu.stanford.smi.protegex.owl.ui.forms; 
import com.hp.hpl.jena.ontology.DatatypeProperty;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;
/**
 * Vocabulary definitions from file:/C:/protege-owl/owl/schemagen-temp.owl 
 * @author Auto-generated by schemagen on 27 Oct 2005 13:07 
 */
public class AbsoluteLayoutNames {
    /** <p>The ontology model that holds the vocabulary terms</p> */
    private static OntModel m_model = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM, null );
    
    /** <p>The namespace of the vocabulary as a string</p> */
    public static final String NS = "http://www.owl-ontologies.com/forms/absolute.owl#";
    
    /** <p>The namespace of the vocabulary as a string</p>
     *  @see #NS */
    public static String getURI() {return NS;}
    
    /** <p>The namespace of the vocabulary as a resource</p> */
    public static final Resource NAMESPACE = m_model.createResource( NS );
    
    public static final DatatypeProperty x = m_model.createDatatypeProperty( "http://www.owl-ontologies.com/forms/absolute.owl#x" );
    
    public static final DatatypeProperty y = m_model.createDatatypeProperty( "http://www.owl-ontologies.com/forms/absolute.owl#y" );
    
    public static final DatatypeProperty height = m_model.createDatatypeProperty( "http://www.owl-ontologies.com/forms/absolute.owl#height" );
    
    public static final DatatypeProperty width = m_model.createDatatypeProperty( "http://www.owl-ontologies.com/forms/absolute.owl#width" );
    
    public static final OntClass AbsoluteLayoutData = m_model.createClass( "http://www.owl-ontologies.com/forms/absolute.owl#AbsoluteLayoutData" );
    
}
