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


package edu.stanford.smi.protegex.owl.swrl.ddm;

import edu.stanford.smi.protegex.owl.swrl.ddm.impl.*;
import edu.stanford.smi.protegex.owl.swrl.portability.OWLClassReference;
import edu.stanford.smi.protegex.owl.swrl.portability.OWLPropertyReference;


import java.util.*;
import java.sql.*;

public class DDMFactory
{
  public static DatabaseConnection createDatabaseConnection(Database database, String userID, String password) throws SQLException
  {
    return new DatabaseConnectionImpl(database, userID, password);
  } // createDatabaseConnection

  public static Database createDatabase(String jdbcDriverName, String serverName, String databaseName, int portNumber) 
  {
    return new DatabaseImpl(jdbcDriverName, serverName, databaseName, portNumber);
  } // createDatabase

  public static Table createTable(Database database, String schemaName, String tableName)
  {
    return new TableImpl(database, schemaName, tableName);
  } // createTable

  public static Table createTable(Database database, String schemaName, String tableName, Set<Column> columns)
  {
    return new TableImpl(database, schemaName, tableName, columns);
  } // createTable

  public static Table createTable(Database database, String schemaName, String tableName, Set<Column> columns, 
                                  PrimaryKey primaryKey, Set<ForeignKey> foreignKeys)
  {
    return new TableImpl(database, schemaName, tableName, columns, primaryKey, foreignKeys);
  } // createTable

  public static Column createColumn(String columnName, int columnType) { return new ColumnImpl(columnName, columnType); }

  public static KeyColumn createKeyColumn(String columnName, int columnType) { return new KeyColumnImpl(columnName, columnType); }

  public static PrimaryKeyColumn createPrimaryKeyColumn(String columnName, int columnType) { return new PrimaryKeyColumnImpl(columnName, columnType); }

  public static ForeignKeyColumn createForeignKeyColumn(String columnName, int columnType, String referencedColumnName) 
  { 
    return new ForeignKeyColumnImpl(columnName, columnType, referencedColumnName); 
  } // createForeignKeyColumn

  public static PrimaryKey createPrimaryKey(Table table, Set<PrimaryKeyColumn> keyColumns) { return new PrimaryKeyImpl(table, keyColumns); }

  public static ForeignKey createForeignKey(Table baseTable, Set<ForeignKeyColumn> keyColumns, Table referencedTable) 
  { 
    return new ForeignKeyImpl(baseTable, keyColumns, referencedTable); 
  } // createForeignKey

  public static OWLClassMap createOWLClassMap(OWLClassReference owlClass, PrimaryKey primaryKey) { return new OWLClassMapImpl(owlClass, primaryKey); }

  public static OWLObjectPropertyMap createOWLObjectPropertyMap(OWLPropertyReference owlProperty, PrimaryKey primaryKey, ForeignKey foreignKey)
  {
    return new OWLObjectPropertyMapImpl(owlProperty, primaryKey, foreignKey);
  } // OWLObjectPropertyMap
      
  public static OWLDatatypePropertyMap createOWLDatatypePropertyMap(OWLPropertyReference owlProperty, PrimaryKey primaryKey, Column valueColumn)
  {
    return new OWLDatatypePropertyMapImpl(owlProperty, primaryKey, valueColumn);
  } // createOWLDatatypePropertyMap

} // DDMFactory
