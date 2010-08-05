/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.krohm.milleborne.engineimpl;

import org.krohm.milleborne.*;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author arnaud
 */
public class MilleBorneGame extends MilleBorneObject {

    private long activePlayer;
    private int currentPhase;
    private long turnOwner;
    private final Map<Long, MilleBorneCard> gameItems = new LinkedHashMap<Long, MilleBorneCard>();
    private final Map<Long, MilleBornePlayer> gamePlayers = new LinkedHashMap<Long, MilleBornePlayer>();

    public Map<Long, MilleBorneCard> getGameItems() {
        return gameItems;
    }

    public Map<Long, MilleBornePlayer> getGamePlayers() {
        return gamePlayers;
    }

    public long getActivePlayer() {
        return activePlayer;
    }

    public void setActivePlayer(long activePlayer) {
        this.activePlayer = activePlayer;
    }

    public long getTurnOwner() {
        return turnOwner;
    }

    public void setTurnOwner(long turnOwner) {
        this.turnOwner = turnOwner;
    }

    public int getCurrentPhase() {
        return currentPhase;
    }

    public void setCurrentPhase(int currentPhase) {
        this.currentPhase = currentPhase;
    }
}
