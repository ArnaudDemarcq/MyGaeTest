/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.krohm.milleborne;

import java.util.List;
import java.util.Map;
import org.krohm.milleborne.actions.IUserAction;

/**
 *
 * @author arnaud
 */
public interface IMilleBorneEngine {

    public long newGame();

    public void deleteGame(long gameId);

    public List<Long> getGameList();

    public List<MilleBorneCard> getZoneContent(long gameId, long zoneId);

    public List<MilleBorneCard> getZoneContent(long gameId, long zoneId, long playerId);

        public List<MilleBornePlayer> getPlayers(long gameId);
    /**
     *      OLD
     **/
    public void executeAction(long gameId,
            IUserAction userAction);
}
