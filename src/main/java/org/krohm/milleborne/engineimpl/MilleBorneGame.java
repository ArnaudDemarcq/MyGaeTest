/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.krohm.milleborne.engineimpl;

import org.krohm.milleborne.*;
import java.util.LinkedHashMap;
import java.util.Map;
import org.krohm.milleborne.util.UniqueId;

/**
 *
 * @author arnaud
 */
public class MilleBorneGame extends MilleBorneObject {

    private long activePlayer;
    private long currentPhase;
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

    public long getCurrentPhase() {
        return currentPhase;
    }

    public void setCurrentPhase(long currentPhase) {
        this.currentPhase = currentPhase;
    }
}
