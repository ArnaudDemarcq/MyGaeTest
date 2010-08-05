/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.krohm.milleborne;

import java.util.List;
import org.krohm.milleborne.actions.IReturnAction;
import org.krohm.milleborne.actions.IUserAction;

/**
 *
 * @author arnaud
 */
public interface IMilleBorneEngine {

    // TODO : Create interfaces for MilleBorneCard and MilleBornePlayer (getters only)
    // in order to prevent
    // Modification by front end
    public long newGame();

    public void deleteGame(long gameId);

    public List<Long> getGameList();

    /**
     *
     * Game status
     *
     **/
    public List<MilleBorneCard> getZoneContent(long gameId, long zoneId);

    public List<MilleBorneCard> getZoneContent(long gameId, long zoneId, long playerId);

    public List<MilleBornePlayer> getPlayers(long gameId);

    public int getPhase(long gameId);

    public MilleBornePlayer getActivePlayer(long gameId);

    public MilleBornePlayer getTurnOwnerPlayer(long gameId);

    /**
     *      Execution
     **/
    public IReturnAction executeAction(long gameId, IUserAction userAction);
}
