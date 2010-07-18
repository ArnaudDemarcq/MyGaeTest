/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.krohm.milleborne.engineimpl;

import java.util.ArrayList;
import java.util.List;
import org.krohm.milleborne.actions.IInternalAction;
import org.krohm.milleborne.actions.IReturnAction;
import org.krohm.milleborne.actions.IUserAction;

/**
 *
 * @author arnaud
 */
public class InternalActionExecutionContext {

    private MilleBorneGame gameData;
    private IUserAction userAction;
    private final List<IInternalAction> todoList = new ArrayList<IInternalAction>();
    private IReturnAction returnAction;

    public MilleBorneGame getGameData() {
        return gameData;
    }

    public void setGameData(MilleBorneGame gameData) {
        this.gameData = gameData;
    }

    public List<IInternalAction> getTodoList() {
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
}
