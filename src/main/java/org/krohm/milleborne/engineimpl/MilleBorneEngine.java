/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.krohm.milleborne.engineimpl;

import org.krohm.milleborne.engineimpl.context.InternalActionExecutionContext;
import org.krohm.milleborne.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.krohm.milleborne.actions.IInternalAction;
import org.krohm.milleborne.actions.IUserAction;
import org.krohm.milleborne.engineimpl.rules.DroolsExecutionSolver;

/**
 *
 * @author arnaud
 */
public class MilleBorneEngine implements IMilleBorneEngine {

    private static final long noPlayerDefault = -1;
    private static final ConcurrentHashMap<Long, MilleBorneGame> games =
            new ConcurrentHashMap<Long, MilleBorneGame>();

    public long newGame() {
        MilleBorneGame newGame = MilleBorneGameFactory.newGame();
        games.putIfAbsent(newGame.getUniqueId(), newGame);
        return newGame.getUniqueId();
    }

    public void deleteGame(long gameId) {
        games.remove(gameId);
    }

    public List<Long> getGameList() {
        return new ArrayList<Long>(games.keySet());
    }

    public List<MilleBorneCard> getZoneContent(long gameId, long zoneId) {
        return getZoneContent(gameId, zoneId, noPlayerDefault);
    }

    public List<MilleBorneCard> getZoneContent(long gameId, long zoneId, long playerId) {
        List<MilleBorneCard> matchingCards = new ArrayList<MilleBorneCard>();

        Map<Long, MilleBorneCard> initialMap = games.get(gameId).getGameItems();

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

    public List<MilleBornePlayer> getPlayers(long gameId) {
        return new ArrayList(games.get(gameId).getGamePlayers().values());
    }
    // OLD

    public void executeAction(long gameId, IUserAction userAction) {
        InternalActionExecutionContext executionData = new InternalActionExecutionContext();
        executionData.setGameData(games.get(gameId));
        executionData.setUserAction(userAction);
        DroolsExecutionSolver solver = new DroolsExecutionSolver();
        //solver.performExecution(executionData);
        solver.performExecution(userAction, games.get(gameId));
    }
}
