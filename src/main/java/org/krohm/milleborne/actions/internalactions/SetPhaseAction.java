/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.krohm.milleborne.actions.internalactions;

import java.util.Deque;
import org.krohm.milleborne.actions.IInternalAction;
import org.krohm.milleborne.engineimpl.MilleBorneGame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author arnaud
 */
public class SetPhaseAction extends AbstractInternalAction {

    private static final Logger logger = LoggerFactory.getLogger(SetPhaseAction.class);
    private int phaseId;

    @Override
    public void execute(MilleBorneGame currentGame, Deque<IInternalAction> internalActionQueue) {
        logger.error("Setting Phase to :<" + phaseId + ">");
        currentGame.setCurrentPhase(phaseId);
    }

    public int getPhaseId() {
        return phaseId;
    }

    public void setPhaseId(int phaseId) {
        this.phaseId = phaseId;
    }
}
