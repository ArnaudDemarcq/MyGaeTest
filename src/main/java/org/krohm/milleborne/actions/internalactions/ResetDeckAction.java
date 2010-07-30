/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.krohm.milleborne.actions.internalactions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.krohm.milleborne.MilleBorneCard;
import org.krohm.milleborne.actions.IInternalAction;
import org.krohm.milleborne.data.Zones;
import org.krohm.milleborne.engineimpl.MilleBorneGame;
import org.krohm.milleborne.util.UniqueId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author arnaud
 */
public class ResetDeckAction extends AbstractInternalAction {

    private static final Logger logger = LoggerFactory.getLogger(ResetDeckAction.class);

    @Override
    public void execute(MilleBorneGame currentGame, Deque<IInternalAction> internalActionQueue) {

        Map<Long, MilleBorneCard> cards = currentGame.getGameItems();
        Map<Long, MilleBorneCard> tmpCards = new HashMap<Long, MilleBorneCard>();
        for (Long key : cards.keySet()) {

            MilleBorneCard currentCard = cards.get(key);
            currentCard.setZoneId(Zones.ZONE_DECK);
            currentCard.setTimerId(UniqueId.getUniqueId());

            tmpCards.put(currentCard.getTimerId(), currentCard);
        }
        cards.clear();
        cards.putAll(tmpCards);
        logger.error("Reset Done");
    }
}
