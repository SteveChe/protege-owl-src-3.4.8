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

import edu.stanford.smi.protegex.owl.swrl.ddm.Database;

public class DatabaseImpl implements Database
{
  private String jdbcDriverName, serverName, databaseName;
  private int portNumber;

  public DatabaseImpl(String jdbcDriverName, String serverName, String databaseName, int portNumber)
  {
    this.jdbcDriverName = jdbcDriverName;
    this.serverName = serverName;
    this.databaseName = databaseName;
    this.portNumber = portNumber;
  } // DatabaseImpl

  public String getJDBCDriverName() { return jdbcDriverName; }
  public String getServerName() { return serverName; }
  public String getDatabaseName() { return databaseName; }
  public int getPortNumber() { return portNumber; }

  public boolean equals(Object obj)
  {
    if (this == obj) return true;
    if ((obj == null) || (obj.getClass() != this.getClass())) return false;
    DatabaseImpl impl = (DatabaseImpl)obj;
    return ((jdbcDriverName != null && impl.jdbcDriverName != null && jdbcDriverName.equals(impl.jdbcDriverName)) &&
            (serverName != null && impl.serverName != null && serverName.equals(impl.serverName)) &&
            (databaseName != null && impl.databaseName != null && databaseName.equals(impl.databaseName)) &&
            (portNumber == impl.portNumber));
  } // equals

  public int hashCode()
  {
    int hash = 345;
    hash = hash + (null == jdbcDriverName ? 0 : jdbcDriverName.hashCode());
    hash = hash + (null == serverName ? 0 : serverName.hashCode());
    hash = hash + (null == databaseName ? 0 : databaseName.hashCode());
    hash = hash + portNumber;
    return hash;
  } // hashCode

  public String toString() 
  {
    return "jdbcDriverName = " + jdbcDriverName + "; serverName = " + serverName + "; databaseName = " + databaseName + "; portNumber = " + portNumber;
  } // toString

} // Database
