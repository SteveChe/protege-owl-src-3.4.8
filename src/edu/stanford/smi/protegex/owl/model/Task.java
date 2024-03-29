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

package edu.stanford.smi.protegex.owl.model;

/**
 * User: matthewhorridge<br>
 * The University Of Manchester<br>
 * Medical Informatics Group<br>
 * Date: Sep 11, 2005<br><br>
 * <p/>
 * matthew.horridge@cs.man.ac.uk<br>
 * www.cs.man.ac.uk/~horridgm<br><br>
 * <p/>
 * Represents a task that could potentially take
 * a significant amount of time to complete and
 * should therefore have some kind of progress
 * display if this is the case.
 */
public interface Task {

    /**
     * Requests that the task be cancelled. This
     * method will only be called if the task
     * can be cancelled.
     */
    public void cancelTask();


    /**
     * Gets the title for this task.
     */
    public String getTitle();


    /**
     * Gets the minimum progress value for
     * this task.
     */
    public int getProgressMin();


    /**
     * Gets the maximum progress value for
     * this task.
     */
    public int getProgressMax();


    /**
     * Checks whether this Task has been cancelled.
     * Unless either method is overloaded, this will return true after cancelTask
     * has been called (e.g., via the cancel button).
     *
     * @return true  if this has been cancelled
     */
    public boolean isCancelled();


    /**
     * Determines if the task can be cancelled
     *
     * @return <code>true</code> if the task can
     *         be cancelled, or <code>false</code> if the
     *         task cannot be cancelled.
     */
    public boolean isPossibleToCancel();


    public void runTask() throws Exception;
}
