/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.krohm.milleborne.actions.internalactions;

import java.util.Deque;
import org.krohm.milleborne.MilleBorneCard;
import org.krohm.milleborne.actions.IInternalAction;
import org.krohm.milleborne.engineimpl.MilleBorneGame;
import org.krohm.milleborne.util.UniqueId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author arnaud
 */
public class MoveCardAction extends AbstractInternalAction {

    private int targetZone;
    private long targetPlayerId;
    private long cardTimerId;
    private static final Logger logger = LoggerFactory.getLogger(MoveCardAction.class);

    public void execute(MilleBorneGame currentGame, Deque<IInternalAction> internalActionQueue) {

        logger.error("About to move card !");
        MilleBorneCard targetCard = currentGame.getGameItems().get(cardTimerId);
        if (targetCard != null)
        {
            targetCard.setTimerId(UniqueId.getUniqueId());
            targetCard.setZoneId(targetZone);
            targetCard.setControlerId(targetPlayerId);
        } else {
            logger.error("Card Already Gone");
        }

        
    }

    public long getTargetPlayerId() {
        return targetPlayerId;
    }

    public void setTargetPlayerId(long targetPlayerId) {
        this.targetPlayerId = targetPlayerId;
    }

    public int getTargetZone() {
        return targetZone;
    }

    public void setTargetZone(int targetZone) {
        this.targetZone = targetZone;
    }

    public long getCardTimerId() {
        return cardTimerId;
    }

    public void setCardTimerId(long cardTimerId) {
        this.cardTimerId = cardTimerId;
    }
}
