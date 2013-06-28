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

import edu.stanford.smi.protegex.owl.swrl.ddm.*;

import java.util.*;

public class TableImpl implements Table
{
  private Database database;
  private String schemaName, tableName;
  private Set<Column> columns;
  private PrimaryKey primaryKey;
  private Set<ForeignKey> foreignKeys;

  public TableImpl(Database database, String schemaName, String tableName)
  {
    this.database = database;
    this.schemaName = schemaName;
    this.tableName = tableName;
    this.columns = new HashSet<Column>();
    primaryKey = null;
    foreignKeys = new HashSet<ForeignKey>();
  } // TableImpl

  public TableImpl(Database database, String schemaName, String tableName, Set<Column> columns)
  {
    this.database = database;
    this.schemaName = schemaName;
    this.tableName = tableName;
    this.columns = columns;
    primaryKey = null;
    foreignKeys = new HashSet<ForeignKey>();
  } // TableImpl

  public TableImpl(Database database, String schemaName, String tableName, Set<Column> columns, 
                   PrimaryKey primaryKey, Set<ForeignKey> foreignKeys)
  {
    this.database = database;
    this.tableName = tableName;
    this.schemaName = schemaName;
    this.columns = columns;
    this.primaryKey = primaryKey;
    this.foreignKeys = foreignKeys;
  } // TableImpl

  public boolean hasPrimaryKey() { return primaryKey != null; }
  public boolean hasForeignKeys() { return !foreignKeys.isEmpty(); }
  
  public PrimaryKey getPrimaryKey() { return primaryKey; }
  public Set<ForeignKey> getForeignKeys() { return foreignKeys; }
  public Database getDatabase() { return database; }
  public String getTableName() { return tableName; }
  public String getSchemaName() { return schemaName; }
  public Set<Column> getColumns() { return columns; }

  public void setPrimaryKey(PrimaryKey primaryKey) { this.primaryKey = primaryKey; }
  public void setForeignKeys(Set<ForeignKey> foreignKeys) { this.foreignKeys = foreignKeys; }
  public void addForeignKey(ForeignKey foreignKey) { foreignKeys.add(foreignKey); }
  public void addColumns(Set<Column> columns) { this.columns = columns; }
} // TableImpl
