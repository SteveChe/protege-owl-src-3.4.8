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


package edu.stanford.smi.protegex.owl.swrl.ddm.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import edu.stanford.smi.protegex.owl.swrl.bridge.Mapper;
import edu.stanford.smi.protegex.owl.swrl.bridge.OWLDataValue;
import edu.stanford.smi.protegex.owl.swrl.bridge.OWLDataValueFactory;
import edu.stanford.smi.protegex.owl.swrl.bridge.exceptions.MapperException;
import edu.stanford.smi.protegex.owl.swrl.ddm.Column;
import edu.stanford.smi.protegex.owl.swrl.ddm.DDMFactory;
import edu.stanford.smi.protegex.owl.swrl.ddm.Database;
import edu.stanford.smi.protegex.owl.swrl.ddm.DatabaseConnection;
import edu.stanford.smi.protegex.owl.swrl.ddm.ForeignKey;
import edu.stanford.smi.protegex.owl.swrl.ddm.MapperGenerator;
import edu.stanford.smi.protegex.owl.swrl.ddm.OWLClassMap;
import edu.stanford.smi.protegex.owl.swrl.ddm.OWLDatatypePropertyMap;
import edu.stanford.smi.protegex.owl.swrl.ddm.OWLObjectPropertyMap;
import edu.stanford.smi.protegex.owl.swrl.ddm.PrimaryKey;
import edu.stanford.smi.protegex.owl.swrl.ddm.PrimaryKeyColumn;
import edu.stanford.smi.protegex.owl.swrl.ddm.Table;
import edu.stanford.smi.protegex.owl.swrl.ddm.exceptions.JDBCException;
import edu.stanford.smi.protegex.owl.swrl.portability.OWLClassReference;
import edu.stanford.smi.protegex.owl.swrl.portability.OWLDataFactory;
import edu.stanford.smi.protegex.owl.swrl.portability.OWLDataPropertyReference;
import edu.stanford.smi.protegex.owl.swrl.portability.OWLDataPropertyAssertionAxiomReference;
import edu.stanford.smi.protegex.owl.swrl.portability.OWLNamedIndividualReference;
import edu.stanford.smi.protegex.owl.swrl.portability.OWLObjectPropertyAssertionAxiomReference;
import edu.stanford.smi.protegex.owl.swrl.portability.OWLPropertyReference;
import edu.stanford.smi.protegex.owl.swrl.portability.p3.P3OWLDataFactory;
import edu.stanford.smi.protegex.owl.swrl.sqwrl.SQWRLQueryEngine;
import edu.stanford.smi.protegex.owl.swrl.sqwrl.SQWRLResult;
import edu.stanford.smi.protegex.owl.swrl.sqwrl.exceptions.InvalidQueryNameException;
import edu.stanford.smi.protegex.owl.swrl.sqwrl.exceptions.SQWRLException;

public class RelationalMapper implements Mapper, MapperGenerator
{
  private Map<String, OWLClassMap> classMaps;
  private Map<String, OWLObjectPropertyMap> objectPropertyMaps;
  private Map<String, OWLDatatypePropertyMap> datatypePropertyMaps;
  private Set<Database> databases;
  private Map<Database, DatabaseConnection> databaseConnections;
  private OWLDataFactory owlFactory;
  private OWLDataValueFactory owlDataValueFactory;

  public RelationalMapper(SQWRLQueryEngine queryEngine) throws MapperException
  {
    classMaps = new HashMap<String, OWLClassMap>();
    objectPropertyMaps = new HashMap<String, OWLObjectPropertyMap>();
    datatypePropertyMaps = new HashMap<String, OWLDatatypePropertyMap>();
    databases = new HashSet<Database>();
    databaseConnections = new HashMap<Database, DatabaseConnection>();

    readMaps(queryEngine);

    owlFactory = new P3OWLDataFactory();
    owlDataValueFactory = OWLDataValueFactory.create();
  } // RelationalMapper

  public void open() throws MapperException
  {
    try {
      for (DatabaseConnection connection : databaseConnections.values()) 
        if (!connection.isOpen()) connection.open();
    } catch (JDBCException e) {
      throw new MapperException("JDBC error opening database connections: " + e.getMessage());
    } catch (SQLException e) {
      throw new MapperException("SQL error opening database connections: " + e.getMessage());
    } // try
  } // open

  public void close() throws MapperException
  {
    try {
      for (DatabaseConnection connection : databaseConnections.values()) 
        if (connection.isOpen()) connection.close();
    } catch (JDBCException e) {
      throw new MapperException("JDBC error closing database connections: " + e.getMessage());
    } catch (SQLException e) {
      throw new MapperException("SQL error closing database connections: " + e.getMessage());
    } // try
  } // close

  public boolean isMapped(OWLClassReference owlClass) { return classMaps.containsKey(owlClass.getURI()); }
  public boolean isMapped(OWLPropertyReference owlProperty) 
  { 
    return objectPropertyMaps.containsKey(owlProperty.getURI()) || datatypePropertyMaps.containsKey(owlProperty.getURI());
  } //isMapped

  public void addMap(OWLClassMap classMap)
  {
    String className = classMap.getOWLClass().getURI();

    if (classMaps.containsKey(className)) classMaps.remove(className); // Remove old map, if any

    classMaps.put(className, classMap);
  } // addMap

  public void addMap(OWLObjectPropertyMap objectPropertyMap)
  {
    String propertyName = objectPropertyMap.getProperty().getURI();

    if (objectPropertyMaps.containsKey(propertyName)) objectPropertyMaps.remove(propertyName); // Remove old map, if any

    objectPropertyMaps.put(propertyName, objectPropertyMap);
  } // addMap

  public void addMap(OWLDatatypePropertyMap datatypePropertyMap)
  {
    String propertyName = datatypePropertyMap.getProperty().getURI();

    if (datatypePropertyMaps.containsKey(propertyName)) datatypePropertyMaps.remove(propertyName); // Remove old map, if any

    datatypePropertyMaps.put(propertyName, datatypePropertyMap);
  } // addMap

  public Set<OWLNamedIndividualReference> mapOWLClass(OWLClassReference owlClass) throws MapperException
  {
    String className = owlClass.getURI();
    OWLClassMap classMap = getOWLClassMap(className);
    PrimaryKey primaryKey = classMap.getPrimaryKey();
    String primaryKeyColumnName = primaryKey.getPrimaryKeyColumns().iterator().next().getColumnName(); // Will have checked for non composite key
    Database database = primaryKey.getBaseTable().getDatabase();
    String tableName = primaryKey.getBaseTable().getTableName();
    //String columnName = primaryKey.getPrimaryKeyColumns().iterator().next().getColumnName();
    Set<OWLNamedIndividualReference> result = new HashSet<OWLNamedIndividualReference>();
    DatabaseConnection databaseConnection;
    ResultSet rs;

    databaseConnection = getDatabaseConnection(database);

    try { 
      rs = databaseConnection.executeQuery("SELECT " + primaryKeyColumnName + " FROM " + tableName);
      
      while (rs.next()) {
        String individualName = rs.getString(primaryKeyColumnName);
        result.add(owlFactory.getOWLIndividual(individualName));
      } // while
    } catch (JDBCException e) {
      throw new MapperException("JDBC error mapping class '" + className + "': " + e.getMessage());
    } catch (SQLException e) {
      throw new MapperException("SQL error mapping class '" + className + "': " + e.getMessage());
    } // try

    return result;
  } // mapOWLClass

  public Set<OWLNamedIndividualReference> mapOWLClass(OWLClassReference owlClass, OWLNamedIndividualReference owlIndividual) throws MapperException
  {
    throw new MapperException("not implemented");
  } // mapOWLClass

  public Set<OWLObjectPropertyAssertionAxiomReference> mapOWLObjectProperty(OWLPropertyReference owlProperty) throws MapperException
  {
    String propertyName = owlProperty.getURI();
    OWLObjectPropertyMap propertyMap = getOWLObjectPropertyMap(propertyName);
    ForeignKey foreignKey = propertyMap.getForeignKey();
    String subjectTableName = foreignKey.getBaseTable().getTableName();
    String subjectPrimaryKeyColumnName = foreignKey.getBaseTable().getPrimaryKey().getPrimaryKeyColumns().iterator().next().getColumnName();
    String subjectForeignKeyColumnName = foreignKey.getForeignKeyColumns().iterator().next().getColumnName();
    String objectTableName = foreignKey.getReferencedTable().getTableName();
    String objectPrimaryKeyColumnName = foreignKey.getReferencedTable().getPrimaryKey().getPrimaryKeyColumns().iterator().next().getColumnName();
    Database database = foreignKey.getBaseTable().getDatabase();
    Set<OWLObjectPropertyAssertionAxiomReference> result = new HashSet<OWLObjectPropertyAssertionAxiomReference>();
    DatabaseConnection databaseConnection;
    String query;
    ResultSet rs;

    databaseConnection = getDatabaseConnection(database);

    query = "SELECT S." + subjectPrimaryKeyColumnName + ", S." + subjectForeignKeyColumnName + ", O." + objectPrimaryKeyColumnName + " " +
            "FROM " + subjectTableName + " AS S, " + objectTableName + " AS O " +
            "WHERE " + subjectForeignKeyColumnName + " = " + objectPrimaryKeyColumnName;

    try {
      rs = databaseConnection.executeQuery(query);
      
      while (rs.next()) {
        OWLNamedIndividualReference subject = owlFactory.getOWLIndividual(rs.getString(subjectPrimaryKeyColumnName));
        OWLNamedIndividualReference object = owlFactory.getOWLIndividual(rs.getString(objectPrimaryKeyColumnName));
        OWLObjectPropertyAssertionAxiomReference axiom = owlFactory.getOWLObjectPropertyAssertionAxiom(subject, owlProperty, object);
        result.add(axiom);
      } // while
    } catch (JDBCException e) {
      throw new MapperException("JDBC error mapping object property '" + propertyName + "': " + e.getMessage());
    } catch (SQLException e) {
      throw new MapperException("SQL error mapping object property '" + propertyName + "': " + e.getMessage());
    } // try

    return result;
  } // mapOWLObjectProperty

  public Set<OWLObjectPropertyAssertionAxiomReference> mapOWLObjectProperty(OWLPropertyReference owlProperty, OWLNamedIndividualReference subject) throws MapperException
  {
    throw new MapperException("not implemented");
  } // mapOWLObjectProperty

  public Set<OWLObjectPropertyAssertionAxiomReference> mapOWLObjectProperty(OWLPropertyReference owlProperty, OWLNamedIndividualReference subject, OWLNamedIndividualReference object) 
    throws MapperException
  {
    throw new MapperException("not implemented");
  } // mapOWLbjectProperty

  public Set<OWLDataPropertyAssertionAxiomReference> mapOWLDataProperty(OWLPropertyReference owlProperty, OWLNamedIndividualReference subjectOWLIndividual,
                                                                       OWLDataValue objectOWLDatatypeValue) 
    throws MapperException
  {
    String propertyName = owlProperty.getURI();
    OWLDatatypePropertyMap propertyMap = getOWLDatatypePropertyMap(propertyName);
    PrimaryKey primaryKey = propertyMap.getPrimaryKey();
    String valueColumnName = propertyMap.getValueColumn().getColumnName();
    String subjectPrimaryKeyColumnName = primaryKey.getPrimaryKeyColumns().iterator().next().getColumnName();
    String subjectTableName = primaryKey.getBaseTable().getTableName();
    Database database = primaryKey.getBaseTable().getDatabase();
    Set<OWLDataPropertyAssertionAxiomReference> result = new HashSet<OWLDataPropertyAssertionAxiomReference>();
    boolean hasSubject = (subjectOWLIndividual != null);
    boolean hasObject = (objectOWLDatatypeValue != null);
    DatabaseConnection databaseConnection;
    String query;
    ResultSet rs;

    databaseConnection = getDatabaseConnection(database);

    query = "SELECT " + subjectPrimaryKeyColumnName + ", " + valueColumnName + " FROM " + subjectTableName;
    if (hasSubject || hasObject) {
      query += " WHERE ";
      if (hasSubject) query +=  subjectPrimaryKeyColumnName + " = " + subjectOWLIndividual.getURI();
      if (hasObject) {
        if (hasSubject) query += " AND ";
        query += valueColumnName + " = ";
        if (objectOWLDatatypeValue.isString()) query += "\"" + objectOWLDatatypeValue.toString() + "\"";
        else query += objectOWLDatatypeValue.toString();
      } // if
    } // if

    try {
      rs = databaseConnection.executeQuery(query);
      
      while (rs.next()) {
        OWLNamedIndividualReference subject = owlFactory.getOWLIndividual(rs.getString(subjectPrimaryKeyColumnName));
        OWLDataValue value = owlDataValueFactory.getOWLDataValue(rs.getFloat(valueColumnName)); // TODO: float only
        OWLDataPropertyAssertionAxiomReference axiom = owlFactory.getOWLDataPropertyAssertionAxiom(subject, owlProperty, value);
        result.add(axiom);
      } // while
      rs.close();
    } catch (JDBCException e) {
      throw new MapperException("JDBC error mapping datatype property '" + propertyName + "': " + e.getMessage());
    } catch (SQLException e) {
      throw new MapperException("SQL error mapping datatype property '" + propertyName + "': " + e.getMessage());
    } // try

    return result;
  } // mapOWLDatatypeProperty

  public Set<OWLDataPropertyAssertionAxiomReference> mapOWLDataProperty(OWLPropertyReference owlProperty,
                                                                       OWLNamedIndividualReference subjectOWLIndividual)

    throws MapperException
  {
    return mapOWLDataProperty(owlProperty, subjectOWLIndividual, null);
  } // mapOWLDatatypeProperty

  public Set<OWLDataPropertyAssertionAxiomReference> mapOWLDataProperty(OWLPropertyReference owlProperty,
                                                                       OWLDataValue objectOWLDatatypeValue)

    throws MapperException
  {
    return mapOWLDataProperty(owlProperty, null, objectOWLDatatypeValue);
  } // mapOWLDatatypeProperty

  public Set<OWLDataPropertyAssertionAxiomReference> mapOWLDataProperty(OWLPropertyReference owlProperty) throws MapperException
  {
    return mapOWLDataProperty(owlProperty, null, null);
  } // mapOWLDatatypeProperty

  private OWLClassMap getOWLClassMap(String className) throws MapperException
  {
    if (!classMaps.containsKey(className)) throw new MapperException("attempt to map unmapped class '" + className + "'");

    return classMaps.get(className);
  } // getOWLClassMap

  private OWLObjectPropertyMap getOWLObjectPropertyMap(String propertyName) throws MapperException
  {
    if (!objectPropertyMaps.containsKey(propertyName)) throw new MapperException("attempt to map unmapped object property '" + propertyName + "'");

    return objectPropertyMaps.get(propertyName);
  } // getOWLObjectPropertyMap

  private OWLDatatypePropertyMap getOWLDatatypePropertyMap(String propertyName) throws MapperException
  {
    if (!datatypePropertyMaps.containsKey(propertyName)) throw new MapperException("attempt to map unmapped datatype property '" + propertyName + "'");

    return datatypePropertyMaps.get(propertyName);
  } // getOWLDatatypePropertyMap

  private void readMaps(SQWRLQueryEngine queryEngine) throws MapperException
  {
    try {
      queryEngine.runSQWRLQueries();
      
      readOWLClassMaps(queryEngine);
      readOWLObjectPropertyMaps(queryEngine);
      readOWLDatatypePropertyMaps(queryEngine);
    } catch (JDBCException e) {
      throw new MapperException("JDBC error reading mapping information: " + e.getMessage());
    } catch (SQLException e) {
      throw new MapperException("SQL error reading mapping information: " + e.getMessage());
    } catch (InvalidQueryNameException e) {
      // We have not imported http://swrl.stanford.edu/ontologies/built-ins/3.4/ddm.owl so there is nothing to map
    } catch (SQWRLException e) {
      throw new MapperException("SQWRL error reading mapping information: " + e.getMessage());
    } // try
  } // readMaps

  private void readOWLClassMaps(SQWRLQueryEngine queryEngine) throws MapperException, SQWRLException
  {    
  } // readOWLClassMaps

  private void readOWLObjectPropertyMaps(SQWRLQueryEngine queryEngine) throws MapperException, SQWRLException
  {
  } // readOWLObjectPropertyMaps

  private void readOWLDatatypePropertyMaps(SQWRLQueryEngine queryEngine) 
    throws MapperException, SQWRLException, SQLException, JDBCException
  {
    Table subjectTable;
    Column valueColumn;
    PrimaryKeyColumn keyColumn;
    Set<PrimaryKeyColumn> idColumns;
    Set<Column> allColumns;
    Database database;
    PrimaryKey primaryKey;
    OWLDatatypePropertyMap datatypePropertyMap;
    OWLDataPropertyReference owlDatatypeProperty;

    SQWRLResult result = queryEngine.getSQWRLResult("ddm:OWLDatatypePropertyMap-Query");
    if (result != null) {
      while (result.hasNext()) {
        String propertyName = result.getPropertyValue("?ddm:owlDatatypeProperty").getURI();
        String schemaName  = result.getDataValue("?ddm:schemaName").getString();
        String tableName  = result.getDataValue("?ddm:tableName").getString();
        String keyColumnName  = result.getDataValue("?ddm:keyColumnName").getString();
        String valueColumnName  = result.getDataValue("?ddm:valueColumnName").getString();
        String jdbcDriverName  = result.getDataValue("?ddm:jdbcDriverName").getString();
        String databaseName  = result.getDataValue("?ddm:databaseName").getString();
        String serverName  = result.getDataValue("?ddm:serverName").getString();
        int portNumber  = result.getDataValue("?ddm:portNumber").getInt();

        owlDatatypeProperty = owlFactory.getOWLDataProperty(propertyName);

        database = DDMFactory.createDatabase(jdbcDriverName, serverName, databaseName, portNumber);
        if (!databases.contains(database)) databases.add(database);

        allColumns = new HashSet<Column>();
        idColumns = new HashSet<PrimaryKeyColumn>();

        keyColumn = DDMFactory.createPrimaryKeyColumn(keyColumnName, 999);
        idColumns.add(keyColumn);
        allColumns.add(keyColumn);

        valueColumn = DDMFactory.createColumn(valueColumnName, 999);
        allColumns.add(keyColumn);

        subjectTable = DDMFactory.createTable(database, schemaName, tableName, allColumns);

        primaryKey = DDMFactory.createPrimaryKey(subjectTable, idColumns);

        datatypePropertyMap = DDMFactory.createOWLDatatypePropertyMap(owlDatatypeProperty, primaryKey, valueColumn);
        datatypePropertyMaps.put(propertyName, datatypePropertyMap);

        result.next();
      } // while
    } // if

  } // readOWLDatatypePropertyMaps

  private DatabaseConnection createDatabaseConnection(Database database) throws MapperException
  {
    DatabaseConnection connection = null;

    try {
      connection = new DatabaseConnectionImpl(database, "root", "w0rches");
    } catch (SQLException e) {
      throw new MapperException("error creating connection to database '" + database + "': " + e.getMessage());
    } // try
    
    return connection;
  } // createDatabaseConnection

  private DatabaseConnection getDatabaseConnection(Database database) throws MapperException
  {
    DatabaseConnection databaseConnection = null;

    if (!databaseConnections.containsKey(database)) {
      databaseConnection = createDatabaseConnection(database);
      databaseConnections.put(database, databaseConnection);
    } else databaseConnection = databaseConnections.get(database);

    try {
      if (!databaseConnection.isOpen()) databaseConnection.open();
    } catch (SQLException e) {
      throw new MapperException("error connecting to database '" + database + "': " + e.getMessage());
    } // try

    return databaseConnection;
  } // getDatabaseConnection

} // RelationalMapper


