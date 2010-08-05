/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.krohm.milleborne.actions.internalactions;

import java.util.Deque;
import org.krohm.milleborne.actions.IInternalAction;
import org.krohm.milleborne.data.Phases;
import org.krohm.milleborne.engineimpl.MilleBorneGame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author arnaud
 */
public class IntenalInitAction extends AbstractInternalAction {

    private static final Logger logger = LoggerFactory.getLogger(IntenalInitAction.class);
    private static final int initDrawNumber = 7;

    public void execute(MilleBorneGame currentGame, Deque<IInternalAction> internalActionQueue) {
        // Hack to handle fact current action still not removed from Queue.
        IInternalAction currentAction = internalActionQueue.pollFirst();
        // Reset
        ResetDeckAction resetAction = new ResetDeckAction();
        internalActionQueue.offer(resetAction);
        //Shuffle
        ShuffleDeckAction shuffleAction = new ShuffleDeckAction();
        internalActionQueue.offer(shuffleAction);
        // User Dependant Actions
        Long firstPlayerId = null;
        for (Long currentPlayerId : currentGame.getGamePlayers().keySet()) {
            if (firstPlayerId == null) {
                firstPlayerId = currentPlayerId;
            }
            // Card Drawing
            DrawCardAction currentDrawCardAction = new DrawCardAction();
            currentDrawCardAction.setPlayerId(currentPlayerId);
            currentDrawCardAction.setCardNumber(initDrawNumber);
            internalActionQueue.offer(currentDrawCardAction);
        }
        // Active Player
        SetActivePlayerAction setActivePlayer = new SetActivePlayerAction();
        setActivePlayer.setPlayerId(firstPlayerId);
        internalActionQueue.offer(setActivePlayer);
        // Turn Owner
        SetTurnOwnerAction setTurnOwner = new SetTurnOwnerAction();
        setTurnOwner.setPlayerId(firstPlayerId);
        internalActionQueue.offer(setTurnOwner);
        // Current Action
        SetPhaseAction setPhaseAction = new SetPhaseAction();
        setPhaseAction.setPhaseId(Phases.PHASE_MAIN);
        internalActionQueue.offer(setPhaseAction);

        // The return of the dirty hack
        internalActionQueue.offerFirst(currentAction);
        logger.error("New Size is : <" + internalActionQueue.size() + ">");
    }
}
