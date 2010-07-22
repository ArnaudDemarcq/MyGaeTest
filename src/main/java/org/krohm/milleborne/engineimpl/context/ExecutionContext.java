/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.krohm.milleborne.engineimpl.context;

import org.krohm.milleborne.actions.IReturnAction;
import org.krohm.milleborne.data.Steps;

/**
 *
 * @author arnaud
 */
public class ExecutionContext {

    private IReturnAction returnAction = null;
    private int currentStep = Steps.STEP_START;

    public int getCurrentStep() {
        return currentStep;
    }

    public void setCurrentStep(int currentStep) {
        this.currentStep = currentStep;
    }

    public IReturnAction getReturnAction() {
        return returnAction;
    }

    public void setReturnAction(IReturnAction returnAction) {
        this.returnAction = returnAction;
    }
}
