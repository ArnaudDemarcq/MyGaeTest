/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.krohm.milleborne.actions.internalactions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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
public class ShuffleDeckAction extends AbstractInternalAction {

    private static final Logger logger = LoggerFactory.getLogger(ShuffleDeckAction.class);

    public void execute(MilleBorneGame currentGame) {

        List<MilleBorneCard> deck = new ArrayList<MilleBorneCard>();
        Map<Long, MilleBorneCard> cards = currentGame.getGameItems();
        for (Long key : cards.keySet()) {
            MilleBorneCard currentCard = cards.get(key);
            if (currentCard.getZoneId() == Zones.ZONE_DECK) {
                deck.add(currentCard);
            }
        }
        Collections.shuffle(deck);
        for (MilleBorneCard currentCard : deck) {
            Long previousKey = currentCard.getTimerId();
            cards.remove(previousKey);
            currentCard.setTimerId(UniqueId.getUniqueId());
            cards.put(currentCard.getTimerId(), currentCard);
        }
        logger.error("Shuffle Done");
    }
}
