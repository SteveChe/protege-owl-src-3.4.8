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

package edu.stanford.smi.protegex.owl.swrl.ui.table;

public class SWRLRuleGroup implements Comparable<SWRLRuleGroup>
{
  private String groupName = "";
  private Boolean isEnabled = false;
  
  public SWRLRuleGroup() {};
  
  public SWRLRuleGroup(String groupName, Boolean isEnabled)
  {
	  super();
	  this.groupName = groupName;
	  this.isEnabled = isEnabled;
  } 
		    
  public String getGroupName() { return groupName; }
  public void setGroupName(String groupName) { this.groupName = groupName; }
  public Boolean getIsEnabled() { return isEnabled; }
  public void setIsEnabled(Boolean isEnabled) { this.isEnabled = isEnabled; }
  
  public int hashCode() 
  { 
	  final int PRIME = 31;
	  int result = 1;
	  result = PRIME * result + ((groupName == null) ? 0 : groupName.hashCode());
	  result = PRIME * result + ((isEnabled == null) ? 0 : isEnabled.hashCode());
	  return result;
  }
  
  public boolean equals(Object obj) {
	  if (this == obj) return true;
	  if (obj == null) return false;
	  if (getClass() != obj.getClass()) return false;
	  final SWRLRuleGroup other = (SWRLRuleGroup)obj;
	  if (groupName == null) { if (other.groupName != null)return false;
	  } else if (!groupName.equals(other.groupName)) return false;
	  if (isEnabled == null) { if (other.isEnabled != null) return false;
	  } else if (!isEnabled.equals(other.isEnabled)) return false;
	  return true;
  }
  
  public int compareTo(SWRLRuleGroup otherObject) {
	  int res = 0;
	  res = otherObject.getGroupName().compareTo(getGroupName());
	  return res;
  } 
  
  public String toString() { return groupName; }
}
