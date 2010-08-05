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
import org.krohm.milleborne.actions.IReturnAction;
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

    @Override
    public IReturnAction executeAction(long gameId, IUserAction userAction) {
        InternalActionExecutionContext executionData = new InternalActionExecutionContext();
        executionData.setGameData(games.get(gameId));
        executionData.setUserAction(userAction);
        DroolsExecutionSolver solver = new DroolsExecutionSolver();
        //solver.performExecution(executionData);
        return solver.performExecution(userAction, games.get(gameId));
    }

    public int getPhase(long gameId) {
        return games.get(gameId).getCurrentPhase();
    }

    public MilleBornePlayer getActivePlayer(long gameId) {
        MilleBorneGame currentGame = games.get(gameId);
        return currentGame.getGamePlayers().get(currentGame.getActivePlayer());
    }

    public MilleBornePlayer getTurnOwnerPlayer(long gameId) {
        MilleBorneGame currentGame = games.get(gameId);
        return currentGame.getGamePlayers().get(currentGame.getTurnOwner());
    }
}
