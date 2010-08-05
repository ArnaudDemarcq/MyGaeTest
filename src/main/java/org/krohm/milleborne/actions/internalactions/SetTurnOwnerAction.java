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
public class SetTurnOwnerAction extends AbstractInternalAction {

    private static final Logger logger = LoggerFactory.getLogger(SetTurnOwnerAction.class);
    private long playerId;

    @Override
    public void execute(MilleBorneGame currentGame, Deque<IInternalAction> internalActionQueue) {
        currentGame.setTurnOwner(playerId);
    }

    public long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(long playerId) {
        this.playerId = playerId;
    }
}
