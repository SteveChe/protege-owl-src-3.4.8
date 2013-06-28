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


package edu.stanford.smi.protegex.owl.swrl.bridge.builtins.swrlxml;

import java.net.URI;
import java.net.URISyntaxException;

import org.jdom.Document;

import edu.stanford.smi.protege.exception.OntologyLoadException;
import edu.stanford.smi.protegex.owl.jena.JenaOWLModel;
import edu.stanford.smi.protegex.owl.model.OWLModel;
import edu.stanford.smi.protegex.owl.model.util.ImportHelper;
import edu.stanford.smi.protegex.owl.swrl.model.SWRLNames;
import edu.stanford.smi.protegex.owl.swrl.util.SWRLOWLUtil;

public class MapXML2OWL
{
  public static void main(String args[])
  {
    String xmlURI = "", owlFilename = "";
    Document doc;
    JenaOWLModel owlModel;
    edu.stanford.smi.protegex.owl.swrl.bridge.builtins.swrlxml.XMLMapper  mapper;
    XMLProcessor processor = new XMLProcessor();

    if (args.length == 2) {
      xmlURI = args[0];
      owlFilename = args[1];
    } else Usage();

    try {
      doc = processor.processXMLStream(xmlURI);
      owlModel = SWRLOWLUtil.createJenaOWLModel();
      owlModel.setGenerateEventsEnabled(false);
      addSWRLXMLImport(owlModel);
      mapper = new XMLMapper(owlModel);
      mapper.document2OWLDocument(doc);
      SWRLOWLUtil.writeJenaOWLModel2File(owlModel, owlFilename);
    } catch (Exception e) {
      System.err.println("error mapping XML document with URI '" + xmlURI + "': " + e.getMessage());
      e.printStackTrace();
    } 
  } 

  private static void Usage()
  {
    System.err.println("Usage: MapXML2OWL <xmlURI> <owlFilename>");
    System.exit(1);
  } 

  private static void addSWRLXMLImport(OWLModel owlModel) 
  {
    ImportHelper importHelper = new ImportHelper(owlModel);

    try {
      owlModel.getNamespaceManager().setPrefix(new URI(SWRLNames.SWRLXML_NAMESPACE), SWRLNames.SWRLXML_PREFIX);
      
      if  (owlModel.getTripleStoreModel().getTripleStore(SWRLNames.SWRLXML_IMPORT) == null) 
        importHelper.addImport(new URI(SWRLNames.SWRLXML_IMPORT));

      importHelper.importOntologies(false);
    } catch (URISyntaxException e) {
      System.err.println("error importing SWRLXML ontology: " + e.getMessage());
      System.exit(-1);
    } catch (OntologyLoadException e) {
      System.err.println("error loading SWRLXML ontology: " + e.getMessage());
      System.exit(-1);
    }
  } 
} 
