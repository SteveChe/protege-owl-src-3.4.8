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


package edu.stanford.smi.protegex.owl.swrl.bridge;

import java.util.Set;

import edu.stanford.smi.protegex.owl.swrl.bridge.exceptions.MapperException;
import edu.stanford.smi.protegex.owl.swrl.portability.OWLClassReference;
import edu.stanford.smi.protegex.owl.swrl.portability.OWLDataPropertyAssertionAxiomReference;
import edu.stanford.smi.protegex.owl.swrl.portability.OWLNamedIndividualReference;
import edu.stanford.smi.protegex.owl.swrl.portability.OWLObjectPropertyAssertionAxiomReference;
import edu.stanford.smi.protegex.owl.swrl.portability.OWLPropertyReference;

public interface Mapper
{
  boolean isMapped(OWLClassReference owlClass);
  boolean isMapped(OWLPropertyReference owlProperty);

  void open() throws MapperException;
  void close() throws MapperException;

  Set<OWLNamedIndividualReference> mapOWLClass(OWLClassReference owlClass) throws MapperException;
  Set<OWLNamedIndividualReference> mapOWLClass(OWLClassReference owlClass, OWLNamedIndividualReference owlIndividual) throws MapperException;

  Set<OWLDataPropertyAssertionAxiomReference> mapOWLDataProperty(OWLPropertyReference owlProperty) throws MapperException;
  Set<OWLDataPropertyAssertionAxiomReference> mapOWLDataProperty(OWLPropertyReference owlProperty, OWLNamedIndividualReference subject) throws MapperException;
  Set<OWLDataPropertyAssertionAxiomReference> mapOWLDataProperty(OWLPropertyReference owlProperty, OWLDataValue value) throws MapperException;
  Set<OWLDataPropertyAssertionAxiomReference> mapOWLDataProperty(OWLPropertyReference owlProperty, OWLNamedIndividualReference subject, OWLDataValue value) 
    throws MapperException;

  Set<OWLObjectPropertyAssertionAxiomReference> mapOWLObjectProperty(OWLPropertyReference owlProperty) throws MapperException;
  Set<OWLObjectPropertyAssertionAxiomReference> mapOWLObjectProperty(OWLPropertyReference owlProperty, OWLNamedIndividualReference subject) throws MapperException;
  Set<OWLObjectPropertyAssertionAxiomReference> mapOWLObjectProperty(OWLPropertyReference owlProperty, OWLNamedIndividualReference subject, OWLNamedIndividualReference object) 
    throws MapperException;
} // Mapper
