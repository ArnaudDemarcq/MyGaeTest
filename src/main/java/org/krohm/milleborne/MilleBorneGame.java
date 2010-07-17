/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.krohm.milleborne;

import java.util.LinkedHashMap;
import java.util.Map;
import org.krohm.milleborne.util.UniqueId;

/**
 *
 * @author arnaud
 */
public class MilleBorneGame {

    private long uniqueId = UniqueId.getUniqueId();
    private long timerId = UniqueId.getUniqueId();
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

    public long getTimerId() {
        return timerId;
    }

    public void setTimerId(long timerId) {
        this.timerId = timerId;
    }

    public long getUniqueId() {
        return uniqueId;
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
