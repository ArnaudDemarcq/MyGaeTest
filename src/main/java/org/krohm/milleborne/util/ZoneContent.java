/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.krohm.milleborne.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.krohm.milleborne.MilleBorneCard;
import org.krohm.milleborne.engineimpl.MilleBorneGame;

/**
 *
 * @author arnaud
 */
public class ZoneContent {

    private static final long noPlayerDefault = -1;

    public static final List<MilleBorneCard> getZoneContent(MilleBorneGame game, long zoneId, long playerId) {
        List<MilleBorneCard> matchingCards = new ArrayList<MilleBorneCard>();

        Map<Long, MilleBorneCard> initialMap = game.getGameItems();

        for (long currentCardId : initialMap.keySet()) {
            MilleBorneCard currentCard = initialMap.get(currentCardId);
            if (currentCard.getZoneId() == zoneId) {
                if (playerId == noPlayerDefault || playerId == currentCard.getControlerId()) {
                    matchingCards.add(currentCard);
                }
            }
        }
        return matchingCards;
    }

    public static final List<MilleBorneCard> getZoneContent(MilleBorneGame game, long zoneId) {
        return getZoneContent(game, zoneId, noPlayerDefault);
    }
}
