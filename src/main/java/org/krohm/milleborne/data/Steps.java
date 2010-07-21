/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.krohm.milleborne.data;

/**
 *
 * @author arnaud
 */
public class Steps {

    public static final int STEP_ERROR = -1; // ERROR !
    public static final int STEP_START = 0; // Contains nothing : just a START
    public static final int STEP_INIT = 1; //
    public static final int STEP_USER_ACTION = 2; // Converts UserAction to Internal Action
    public static final int STEP_BEFORE_EXEC = 3; // Action Validation
    public static final int STEP_EXEC = 4; // Executes the Action
    public static final int STEP_AFTER_EXEC = 5; // Things to do after Exec (link change phase)
    public static final int STEP_END = 6;// Contains nothing : just a END

    public static class InternalSteps {

        public static final int INTERNAL_STEP_USER_ACTION = 201; // ERROR
    }
}

