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


// TODO: lot of repetition in methods. Clean up.
// TODO :has prefix

package edu.stanford.smi.protegex.owl.swrl.bridge.builtins.abox;

import java.util.List;

import edu.stanford.smi.protegex.owl.model.OWLModel;
import edu.stanford.smi.protegex.owl.model.OWLNamedClass;
import edu.stanford.smi.protegex.owl.model.RDFResource;
import edu.stanford.smi.protegex.owl.swrl.bridge.BuiltInArgument;
import edu.stanford.smi.protegex.owl.swrl.bridge.MultiArgument;
import edu.stanford.smi.protegex.owl.swrl.bridge.OWLDataValue;
import edu.stanford.smi.protegex.owl.swrl.bridge.builtins.AbstractSWRLBuiltInLibrary;
import edu.stanford.smi.protegex.owl.swrl.bridge.exceptions.BuiltInException;
import edu.stanford.smi.protegex.owl.swrl.bridge.exceptions.OWLConversionFactoryException;
import edu.stanford.smi.protegex.owl.swrl.bridge.exceptions.SWRLBuiltInLibraryException;
import edu.stanford.smi.protegex.owl.swrl.portability.OWLClassAssertionAxiomReference;
import edu.stanford.smi.protegex.owl.swrl.portability.OWLClassReference;
import edu.stanford.smi.protegex.owl.swrl.portability.OWLNamedIndividualReference;
import edu.stanford.smi.protegex.owl.swrl.portability.OWLPropertyReference;
import edu.stanford.smi.protegex.owl.swrl.portability.OWLPropertyAssertionAxiomReference;
import edu.stanford.smi.protegex.owl.swrl.portability.SWRLIndividualArgumentReference;
import edu.stanford.smi.protegex.owl.swrl.portability.p3.P3OWLOntology;
import edu.stanford.smi.protegex.owl.swrl.sqwrl.DataValue;
import edu.stanford.smi.protegex.owl.swrl.util.SWRLOWLUtil;

/**
 * Implementations library for SWRL ABox built-in methods. See <a
 * href="http://protege.cim3.net/cgi-bin/wiki.pl?SWRLABoxBuiltIns">here</a> for documentation on this library.
 *
 * See <a href="http://protege.cim3.net/cgi-bin/wiki.pl?SWRLBuiltInBridge">here</a> for documentation on defining SWRL built-in libraries.
 */
public class SWRLBuiltInLibraryImpl extends AbstractSWRLBuiltInLibrary
{
  private static String SWRLABoxLibraryName = "SWRLABoxBuiltIns";

  public SWRLBuiltInLibraryImpl() 
  { 
    super(SWRLABoxLibraryName); 
  } 

  public void reset() {}

  /**
   * Determine if a single argument is an OWL individual. If the argument is unbound, bind it to all OWL individuals in an ontology.
   */
  public boolean isIndividual(List<BuiltInArgument> arguments) throws BuiltInException
  {
    checkNumberOfArgumentsEqualTo(1, arguments.size());
    boolean isUnboundArgument = isUnboundArgument(0, arguments);   
    boolean result = false;

    if (isUnboundArgument) {
      MultiArgument multiArgument = createMultiArgument();
      for (OWLNamedIndividualReference individual : getBuiltInBridge().getOWLIndividuals()) {
      	SWRLIndividualArgumentReference argument = createIndividualArgument(individual.getURI());
      	multiArgument.addArgument(argument);
      } // for
      arguments.get(0).setBuiltInResult(multiArgument);
      result = !multiArgument.hasNoArguments();
    } else {
      String individualURI = getArgumentAsAnIndividualURI(0, arguments);
      result = getBuiltInBridge().isOWLIndividual(individualURI);
    } // if

    return result;
  }

  // TODO: this needs serious cleanup.
  /**
   * Returns true if the individual named by the first argument has a property specified by the second argument with the value specified by
   * the third argument. If the third argument in unbound, bind it to all the values for this property for the specified individual.
   */
  public boolean hasValue(List<BuiltInArgument> arguments) throws BuiltInException
  {
    String individualURI, propertyURI = null;
    Object propertyValue = null;
    boolean propertyValueSupplied;
    boolean result = false, isObjectProperty;

    checkNumberOfArgumentsEqualTo(3, arguments.size());
    individualURI = getArgumentAsAnIndividualURI(0, arguments);
    propertyURI = getArgumentAsAPropertyURI(1, arguments);

    propertyValueSupplied = !isUnboundArgument(2, arguments);

    try {
      isObjectProperty = getBuiltInBridge().isOWLObjectProperty(propertyURI);

      if (getIsInConsequent()) {
        OWLNamedIndividualReference subject = getBuiltInBridge().getOWLDataFactory().getOWLIndividual(individualURI);
        OWLPropertyReference property;
        OWLPropertyAssertionAxiomReference axiom;

        if (isObjectProperty) {
          SWRLIndividualArgumentReference argument = getArgumentAsAnIndividual(2, arguments);
          OWLNamedIndividualReference value = getBuiltInBridge().getOWLDataFactory().getOWLIndividual(argument.getURI());
          property = getBuiltInBridge().getOWLDataFactory().getOWLObjectProperty(propertyURI);
          axiom = getBuiltInBridge().getOWLDataFactory().getOWLObjectPropertyAssertionAxiom(subject, property, value);
        } else {
          DataValue dataValue = getArgumentAsADataValue(2, arguments);
          OWLDataValue owlDataValue = getBuiltInBridge().getOWLDataValueFactory().getOWLDataValue(dataValue);  
          property = getBuiltInBridge().getOWLDataFactory().getOWLDataProperty(propertyURI);
          axiom = getBuiltInBridge().getOWLDataFactory().getOWLDataPropertyAssertionAxiom(subject, property, owlDataValue);
        } // if
        getBuiltInBridge().injectOWLAxiom(axiom);
      } else { // In antecedent
        if (propertyValueSupplied) {
          propertyValue = getArgumentAsAPropertyValue(2, arguments);
          if (isObjectProperty) {
            RDFResource resource = SWRLOWLUtil.getObjectPropertyValue(getOWLModel(), individualURI, propertyURI);
            result = propertyValue.equals(resource.getURI());
          } else 
            result = SWRLOWLUtil.getDatavaluedPropertyValues(getOWLModel(), individualURI, propertyURI).contains(propertyValue);
        } else { // Property value unbound
          MultiArgument multiArgument = createMultiArgument();
          if (isObjectProperty) {
            for (RDFResource value : SWRLOWLUtil.getObjectPropertyValues(getOWLModel(), individualURI, propertyURI)) {
              if (value instanceof edu.stanford.smi.protegex.owl.model.OWLIndividual) {
                edu.stanford.smi.protegex.owl.model.OWLIndividual individual = (edu.stanford.smi.protegex.owl.model.OWLIndividual)value;
                multiArgument.addArgument(createIndividualArgument(individual.getURI()));
              } else if (value instanceof edu.stanford.smi.protegex.owl.model.OWLNamedClass) {
                edu.stanford.smi.protegex.owl.model.OWLNamedClass cls = (edu.stanford.smi.protegex.owl.model.OWLNamedClass)value;
                multiArgument.addArgument(createClassArgument(cls.getURI()));
              } else if (value instanceof edu.stanford.smi.protegex.owl.model.OWLDatatypeProperty) {
                edu.stanford.smi.protegex.owl.model.OWLDatatypeProperty owlDatatypeProperty = (edu.stanford.smi.protegex.owl.model.OWLDatatypeProperty)value;
                multiArgument.addArgument(createDataPropertyArgument(owlDatatypeProperty.getURI()));
              } else if (value instanceof edu.stanford.smi.protegex.owl.model.OWLObjectProperty) {
                edu.stanford.smi.protegex.owl.model.OWLObjectProperty owlObjectProperty = (edu.stanford.smi.protegex.owl.model.OWLObjectProperty)value;
                multiArgument.addArgument(createObjectPropertyArgument(owlObjectProperty.getURI()));
              } // if
            } // for
          } else { // Data property
            for (Object value : SWRLOWLUtil.getDatavaluedPropertyValues(getOWLModel(), individualURI, propertyURI)) {
              if (value instanceof edu.stanford.smi.protegex.owl.model.RDFSLiteral) {
                edu.stanford.smi.protegex.owl.model.RDFSLiteral literal = (edu.stanford.smi.protegex.owl.model.RDFSLiteral)value;
                multiArgument.addArgument(P3OWLOntology.convertRDFSLiteral2DataValueArgument(getOWLModel(), literal));
              } else {
                multiArgument.addArgument(createDataValueArgument(value));
              } // if
            } // for
          } // if
          arguments.get(2).setBuiltInResult(multiArgument);
          result = !multiArgument.hasNoArguments();
        } // if
      } // if
    } catch (OWLConversionFactoryException e) {
      throw new BuiltInException(e.getMessage());
    } // try

    return result;
  } // hasValue

  /**
   * Returns true if the individual named by the first argument has a property specified by the second argument. If the second argument in
   * unbound, bind it to all the properties that currently have a value for this individual.
   */
  public boolean hasProperty(List<BuiltInArgument> arguments) throws BuiltInException
  {
    String individualURI, propertyURI = null;
    boolean hasUnboundPropertyArgument = isUnboundArgument(1, arguments);
    boolean result = false;

    checkNumberOfArgumentsInRange(2, 3, arguments.size());
    individualURI = getArgumentAsAnIndividualURI(0, arguments);
    propertyURI = getArgumentAsAPropertyURI(1, arguments);

    try {
      if (hasUnboundPropertyArgument) {
        MultiArgument multiArgument = createMultiArgument();
        for (edu.stanford.smi.protegex.owl.model.OWLProperty property : SWRLOWLUtil.getOWLPropertiesOfIndividual(getOWLModel(), individualURI)) {
          if (property.isObjectProperty()) multiArgument.addArgument(createObjectPropertyArgument(property.getURI()));
          else multiArgument.addArgument(createDataPropertyArgument(property.getURI()));
        } // for
        arguments.get(1).setBuiltInResult(multiArgument);
        result = !multiArgument.hasNoArguments();
      } else
        result = SWRLOWLUtil.getNumberOfPropertyValues(getOWLModel(), individualURI, propertyURI, true) != 0;
    } catch (OWLConversionFactoryException e) {
      throw new BuiltInException(e.getMessage());
    } // try

    return result;
  } 

  /**
   * Returns true if the class named by the first argument has an individual identified by the second argument. If the second argument is
   * unbound, bind it to all individuals of the class.
   */
  public boolean hasIndividual(List<BuiltInArgument> arguments) throws BuiltInException
  {
    boolean isUnboundArgument = isUnboundArgument(1, arguments);   
    String classURI;
    boolean result = false;

    checkNumberOfArgumentsEqualTo(2, arguments.size());
    checkThatArgumentIsAClass(0, arguments);

    classURI = getArgumentAsAClassURI(0, arguments);

    try {
      if (isUnboundArgument) {
        MultiArgument multiArgument = createMultiArgument();
        for (edu.stanford.smi.protegex.owl.model.OWLIndividual individual: SWRLOWLUtil.getOWLIndividualsOfClass(getOWLModel(), classURI)) 
          multiArgument.addArgument(createIndividualArgument(individual.getURI()));
        arguments.get(1).setBuiltInResult(multiArgument);
        result = !multiArgument.hasNoArguments();
      } else { // Bound argument
        String individualURI = getArgumentAsAnIndividualURI(1, arguments);
        result = getBuiltInBridge().isOWLIndividualOfClass(individualURI, classURI);
      } // if
    } catch (OWLConversionFactoryException e) {
      throw new BuiltInException(e.getMessage());
    } // try
    return result;
  } 

  /**
   * Returns true if the OWL class, property, or individual named by the first argument has a URI identified by the second
   * argument. If the second argument is unbound, bind it to URI of the resource.
   */
  public boolean hasURI(List<BuiltInArgument> arguments) throws BuiltInException
  {
    boolean isUnboundArgument = isUnboundArgument(1, arguments);   
    String resourceURI, uri;
    boolean result = false;

    checkNumberOfArgumentsEqualTo(2, arguments.size());
    checkThatArgumentIsAClassPropertyOrIndividual(0, arguments);

    resourceURI = getArgumentAsAURI(0, arguments);

    try {
      if (isUnboundArgument) {
        uri = SWRLOWLUtil.getURI(getOWLModel(), resourceURI);
        arguments.get(1).setBuiltInResult(createDataValueArgument(uri));
        result = true;
      } else { // Bound argument
        uri = getArgumentAsAString(1, arguments);
        result = uri.equals(SWRLOWLUtil.getURI(getOWLModel(), resourceURI));
      } // if
    } catch (OWLConversionFactoryException e) {
      throw new BuiltInException(e.getMessage());
    } // try
    return result;
  } 

  /**
   * Returns true if the individual named by the first argument is an instance of the class identified by the second argument. If the
   * second argument is unbound, bind it to all defining classes of the individual.
   */
  public boolean hasClass(List<BuiltInArgument> arguments) throws BuiltInException
  {
    boolean isUnboundArgument = isUnboundArgument(1, arguments);   
    String individualURI;
    boolean result = false;

    checkNumberOfArgumentsEqualTo(2, arguments.size());

    individualURI = getArgumentAsAnIndividualURI(0, arguments);

    try {
      if (getIsInConsequent()) {
        String classURI;
        OWLClassReference owlClass;
        OWLNamedIndividualReference owlIndividual;
        OWLClassAssertionAxiomReference axiom;

        if (isArgumentAString(1, arguments)) { 
          classURI = SWRLOWLUtil.getFullName(getOWLModel(), getArgumentAsAString(1, arguments));;
          if (!getBuiltInBridge().isOWLClass(classURI)) getBuiltInBridge().injectOWLClassDeclaration(classURI);
        } else classURI = getArgumentAsAClassURI(1, arguments);

        owlClass  = getBuiltInBridge().getOWLDataFactory().getOWLClass(classURI);
        owlIndividual = getBuiltInBridge().getOWLDataFactory().getOWLIndividual(individualURI);
        axiom = getBuiltInBridge().getOWLDataFactory().getOWLClassAssertionAxiom(owlIndividual, owlClass);

        getBuiltInBridge().injectOWLAxiom(axiom);
      } else {
        if (isUnboundArgument) {
          MultiArgument multiArgument = createMultiArgument();
          for (OWLNamedClass cls: SWRLOWLUtil.getOWLClassesOfIndividual(getOWLModel(), individualURI)) 
            multiArgument.addArgument(createClassArgument(cls.getURI()));
          arguments.get(1).setBuiltInResult(multiArgument);
          result = !multiArgument.hasNoArguments();
        } else { // Bound argument
          String classURI = getArgumentAsAClassURI(1, arguments);
          result = getBuiltInBridge().isOWLIndividualOfClass(individualURI, classURI);
        } // if
      } // if
    } catch (OWLConversionFactoryException e) {
      throw new BuiltInException(e.getMessage());
    } // try

    return result;
  }

  /**
   * Returns true if the individual named by the second argument has the number of values specified by the first argument of the property
   * specified by the third argument.
   */
  public boolean hasNumberOfPropertyValues(List<BuiltInArgument> arguments) throws BuiltInException
  {
    String individualURI, propertyURI = null;
    int numberOfPropertyValues;
    boolean hasUnboundNumberOfPropertyValuesArgument = isUnboundArgument(0, arguments);
    boolean result = false;

    checkNumberOfArgumentsEqualTo(3, arguments.size());
    individualURI = getArgumentAsAnIndividualURI(1, arguments);
    propertyURI = getArgumentAsAPropertyURI(2, arguments);

    try {
      if (hasUnboundNumberOfPropertyValuesArgument) {
        numberOfPropertyValues = SWRLOWLUtil.getNumberOfPropertyValues(getOWLModel(), individualURI, propertyURI, true);
        arguments.get(0).setBuiltInResult(createDataValueArgument(numberOfPropertyValues));
        result = true;
      } else {
        numberOfPropertyValues = getArgumentAsAnInteger(0, arguments);
        result = SWRLOWLUtil.getNumberOfPropertyValues(getOWLModel(), individualURI, propertyURI, true) == numberOfPropertyValues;
      } // if
    } catch (OWLConversionFactoryException e) {
      throw new BuiltInException(e.getMessage());
    } // try

    return result;
  }

  public boolean isLiteral(List<BuiltInArgument> arguments) throws BuiltInException
  {
    checkNumberOfArgumentsEqualTo(1, arguments.size());

    return isArgumentADataValue(0, arguments);
  }

  public boolean notLiteral(List<BuiltInArgument> arguments) throws BuiltInException
  {
    checkNumberOfArgumentsEqualTo(1, arguments.size());

    return !isArgumentADataValue(0, arguments);
  }

  public boolean isNumeric(List<BuiltInArgument> arguments) throws BuiltInException
  {
    checkNumberOfArgumentsEqualTo(1, arguments.size());

    return isArgumentNumeric(0, arguments);
  } 

  public boolean notNumeric(List<BuiltInArgument> arguments) throws BuiltInException
  {
    return !isNumeric(arguments);
  }
  
  private OWLModel getOWLModel() throws SWRLBuiltInLibraryException { return getBuiltInBridge().getActiveOntology().getOWLModel(); }
}
