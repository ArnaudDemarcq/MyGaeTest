/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.krohm.milleborne.actions.internalactions;

import java.util.Deque;
import java.util.List;
import org.krohm.milleborne.MilleBorneCard;
import org.krohm.milleborne.actions.IInternalAction;
import org.krohm.milleborne.data.Zones;
import org.krohm.milleborne.engineimpl.MilleBorneGame;
import org.krohm.milleborne.util.ZoneContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author arnaud
 */
public class DrawCardAction extends AbstractInternalAction {

    private static final Logger logger = LoggerFactory.getLogger(DrawCardAction.class);
    private long playerId;
    private int cardNumber;

    @Override
    public void execute(MilleBorneGame currentGame, Deque<IInternalAction> internalActionQueue) {

        logger.error("Player :<" + playerId + "> about to draw <" + cardNumber + "> cards.");
        // Hack to handle fact current action still not removed from Queue.
        IInternalAction currentAction = internalActionQueue.pollFirst();
        List<MilleBorneCard> deck = ZoneContent.getZoneContent(currentGame, Zones.ZONE_DECK);
        for (int i = 0; i < cardNumber; i++) {
            // Move card from top of library to hand
            if (i < deck.size()) {
                MoveCardAction newAction = new MoveCardAction();
                newAction.setTargetZone(Zones.PLAYER_ZONES.ZONE_HAND);
                newAction.setTargetPlayerId(playerId);
                newAction.setCardTimerId(deck.get(i).getTimerId());
                internalActionQueue.offerFirst(newAction);
            } else
            {
                logger.warn("There is not enouth cards to draw");
            }
        }
        internalActionQueue.offerFirst(currentAction);
        logger.error("New Size is : <" + internalActionQueue.size() + ">");
    }

    public long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(long playerId) {
        this.playerId = playerId;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }
}
