/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.krohm.milleborne.engineimpl;

import java.util.LinkedList;
import java.util.Queue;
import org.krohm.milleborne.actions.IInternalAction;
import org.krohm.milleborne.actions.IReturnAction;
import org.krohm.milleborne.actions.IUserAction;
import org.krohm.milleborne.data.Steps;

/**
 *
 * @author arnaud
 */
public class InternalActionExecutionContext {

    // some public are fields for Drools to be able to detect modifications
    private MilleBorneGame gameData;
    private IUserAction userAction;
    private final Queue<IInternalAction> todoList = new LinkedList<IInternalAction>();
    private IReturnAction returnAction;
    private int currentStep = Steps.STEP_START;

    public MilleBorneGame getGameData() {
        return gameData;
    }

    public void setGameData(MilleBorneGame gameData) {
        this.gameData = gameData;
    }

    public Queue<IInternalAction> getTodoList() {
        return todoList;
    }

    public IUserAction getUserAction() {
        return userAction;
    }

    public void setUserAction(IUserAction userAction) {
        this.userAction = userAction;
    }

    public IReturnAction getReturnAction() {
        return returnAction;
    }

    public void setReturnAction(IReturnAction returnAction) {
        this.returnAction = returnAction;
    }

    public int getCurrentStep() {
        return currentStep;
    }

    public void setCurrentStep(int currentStep) {
        this.currentStep = currentStep;
    }

}
