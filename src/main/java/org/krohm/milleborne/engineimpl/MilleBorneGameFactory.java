/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.krohm.milleborne.engineimpl;

import org.krohm.milleborne.*;
import java.util.LinkedHashMap;
import java.util.Map;
import org.krohm.milleborne.cards.*;
import org.krohm.milleborne.data.Zones;
import org.krohm.milleborne.util.UniqueId;

/**
 *
 * @author arnaud
 */
public class MilleBorneGameFactory {

    public static final MilleBorneGame newGame() {
        MilleBorneGame currentGame = new MilleBorneGame();
        // cards
        currentGame.getGameItems().putAll(getInitialDeck());

        // players
        MilleBornePlayer player1 = new MilleBornePlayer();
        player1.setName("Player1");
        currentGame.getGamePlayers().put(player1.getUniqueId(), player1);
        MilleBornePlayer player2 = new MilleBornePlayer();
        player2.setName("Player2");
        currentGame.getGamePlayers().put(player2.getUniqueId(), player2);
        return currentGame;
    }

    private static final Map<Long, MilleBorneCard> getInitialDeck() {


        Map<Long, MilleBorneCard> currentDeck = new LinkedHashMap<Long, MilleBorneCard>();

        // Distance Cards
        for (int i = 0; i < 10; i++) {
            addNewCardToDeck(new Dist25(), currentDeck);
        }
        for (int i = 0; i < 10; i++) {
            addNewCardToDeck(new Dist75(), currentDeck);
        }
        for (int i = 0; i < 10; i++) {
            addNewCardToDeck(new Dist50(), currentDeck);
        }
        for (int i = 0; i < 12; i++) {
            addNewCardToDeck(new Dist100(), currentDeck);
        }
        for (int i = 0; i < 4; i++) {
            addNewCardToDeck(new Dist200(), currentDeck);
        }
        // Obstacle Cards
        for (int i = 0; i < 3; i++) {
            addNewCardToDeck(new ObstacleGas(), currentDeck);
        }
        for (int i = 0; i < 3; i++) {
            addNewCardToDeck(new ObstacleTire(), currentDeck);
        }

        for (int i = 0; i < 3; i++) {
            addNewCardToDeck(new ObstacleAccident(), currentDeck);
        }
        for (int i = 0; i < 4; i++) {
            addNewCardToDeck(new ObstacleSpeedLimit(), currentDeck);
        }
        for (int i = 0; i < 5; i++) {
            addNewCardToDeck(new ObstacleRedLight(), currentDeck);
        }
        // Parades
        for (int i = 0; i < 6; i++) {
            addNewCardToDeck(new ParadeGas(), currentDeck);
        }
        for (int i = 0; i < 6; i++) {
            addNewCardToDeck(new ParadeTire(), currentDeck);
        }
        for (int i = 0; i < 6; i++) {
            addNewCardToDeck(new ParadeAccident(), currentDeck);
        }
        for (int i = 0; i < 6; i++) {
            addNewCardToDeck(new ParadeLimitEnd(), currentDeck);
        }
        for (int i = 0; i < 14; i++) {
            addNewCardToDeck(new ParadeDrive(), currentDeck);
        }
        addNewCardToDeck(new BotteGas(), currentDeck);
        addNewCardToDeck(new BottePrioritary(), currentDeck);
        addNewCardToDeck(new BotteTire(), currentDeck);
        addNewCardToDeck(new BotteAccident(), currentDeck);
        return currentDeck;

    }

    private static final void addNewCardToDeck(MilleBorneCard currentCard,
            Map<Long, MilleBorneCard> currentMap) {
        currentCard.setZoneId(Zones.ZONE_DECK);
        // reset gives a new Timer Id as the object is changing zone
        currentCard.setTimerId(UniqueId.getUniqueId());
        currentMap.put(currentCard.getTimerId(), currentCard);
    }
}
