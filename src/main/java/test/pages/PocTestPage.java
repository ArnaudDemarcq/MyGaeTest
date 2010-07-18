/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.pages;

import java.util.List;
import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.krohm.milleborne.IMilleBorneEngine;
import org.krohm.milleborne.engineimpl.MilleBorneEngine;

import org.apache.wicket.extensions.markup.html.tree.table.ColumnLocation;
import org.apache.wicket.extensions.markup.html.tree.table.IColumn;
import org.apache.wicket.extensions.markup.html.tree.table.PropertyRenderableColumn;
import org.apache.wicket.extensions.markup.html.tree.table.PropertyTreeColumn;
import org.apache.wicket.extensions.markup.html.tree.table.TreeTable;
import org.apache.wicket.extensions.markup.html.tree.table.ColumnLocation.Alignment;
import org.apache.wicket.extensions.markup.html.tree.table.ColumnLocation.Unit;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;
import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.behavior.AbstractAjaxBehavior;
import org.apache.wicket.model.PropertyModel;
import org.krohm.milleborne.MilleBorneCard;
import org.krohm.milleborne.MilleBornePlayer;
import org.krohm.milleborne.data.Zones;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.wicket.extensions.markup.html.tree.table.IRenderable;
import org.apache.wicket.markup.html.form.Form;
import org.krohm.milleborne.actions.useractions.GameInitAction;
import test.Pannels.CardActionPanel;

/**
 *
 * @author arnaud
 */
public class PocTestPage extends PocMainPage {

    private static final IMilleBorneEngine testMilleBorneEngine = new MilleBorneEngine();
    private static final long gameId = testMilleBorneEngine.newGame();
    private static final Logger logger = LoggerFactory.getLogger(PocTestPage.class);

    public PocTestPage(PageParameters parameters) {
        super(parameters);

        add(new Label("message", "Poc Test Page[" +
                testMilleBorneEngine.getGameList().size() + "]" + gameId));

        Form currentForm = new Form("actionForm") {

            @Override
            protected void onSubmit() {
                logger.error("Dont click at the form !");
            }
        };
        add(currentForm);

        GameInitAction tmpInitAction = new GameInitAction();
        currentForm.add(new test.Pannels.UserActionButton("InitButton", testMilleBorneEngine, gameId, tmpInitAction));



        IColumn columns[] = new IColumn[]{
            new PropertyTreeColumn(new ColumnLocation(Alignment.MIDDLE, 8, Unit.PROPORTIONAL),
            "Tree Column (middle)", "userObject.name"),
            new PropertyRenderableColumn(new ColumnLocation(Alignment.MIDDLE, 2,
            Unit.PROPORTIONAL), "Id", "userObject.uniqueId"),
            new PropertyRenderableColumn(new ColumnLocation(Alignment.MIDDLE, 2,
            Unit.PROPORTIONAL), "TimeStamp", "userObject.timerId"),
            new PropertyRenderableColumn(new ColumnLocation(Alignment.MIDDLE, 3,
            Unit.PROPORTIONAL), "Type", "userObject.type"),
            new PropertyRenderableColumn(new ColumnLocation(Alignment.MIDDLE, 3,
            Unit.PROPORTIONAL), "Subtype", "userObject.subType"),
            new testColumn(new ColumnLocation(Alignment.MIDDLE, 8, Unit.PROPORTIONAL), "Actions",
            "userObject.timerId")};


        //   TreeTable testTreeTable = new TreeTable("testTreeTable", createTreeModel(), columns);
        TreeTable testTreeTable = new TreeTable("testTreeTable", getMilleBorneTreeModel(gameId), columns);


        testTreeTable.getTreeState().setAllowSelectMultiple(false);
        testTreeTable.add(new AbstractAjaxBehavior() {

            public void onRequest() {
                logger.error("GNI !!");
            }
        });
        add(testTreeTable);
        testTreeTable.getTreeState().collapseAll();
    }

    private TreeModel getMilleBorneTreeModel(long gameId) {
        TreeModel model = null;
        MilleBorneCard rootCard = getTreeModelNode("Root");
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(getTreeModelNode("Root"));
        // add RootLevel Zones
        rootNode.add(getZoneView(gameId, Zones.ZONE_DECK, "Deck"));
        rootNode.add(getZoneView(gameId, Zones.ZONE_DISCARD, "Discard"));
        // add Player Level Zones
        for (MilleBornePlayer currentPlayer : testMilleBorneEngine.getPlayers(gameId)) {
            long playerID = currentPlayer.getUniqueId();
            String playerName = currentPlayer.getName();
            DefaultMutableTreeNode currentPlayerNode =
                    new DefaultMutableTreeNode(getTreeModelNode(playerName));
            currentPlayerNode.add(getZoneView(gameId, Zones.PLAYER_ZONES.ZONE_HAND, "Hand", playerID));
            currentPlayerNode.add(getZoneView(gameId, Zones.PLAYER_ZONES.ZONE_DIST, "DistancesCards", playerID));
            currentPlayerNode.add(getZoneView(gameId, Zones.PLAYER_ZONES.ZONE_BATTLE, "Battle", playerID));
            currentPlayerNode.add(getZoneView(gameId, Zones.PLAYER_ZONES.ZONE_SPEED, "SpeedLimitations", playerID));
            currentPlayerNode.add(getZoneView(gameId, Zones.PLAYER_ZONES.ZONE_BOTTE, "Bottes", playerID));
            rootNode.add(currentPlayerNode);

        }
        model = new DefaultTreeModel(rootNode);
        return model;
    }

    private DefaultMutableTreeNode getZoneView(long gameId, long zoneId, String nodeLabel, long playerId) {
        return getZoneView(testMilleBorneEngine.getZoneContent(gameId, zoneId, playerId), nodeLabel);
    }

    private DefaultMutableTreeNode getZoneView(long gameId, long zoneId, String nodeLabel) {
        return getZoneView(testMilleBorneEngine.getZoneContent(gameId, zoneId), nodeLabel);
    }

    private DefaultMutableTreeNode getZoneView(List<MilleBorneCard> zoneList, String nodeLabel) {
        DefaultMutableTreeNode returnNode = new DefaultMutableTreeNode(getTreeModelNode(nodeLabel));
        for (MilleBorneCard currentCard : zoneList) {
            returnNode.add(new DefaultMutableTreeNode(currentCard));
        }
        if (zoneList.size() < 1) {
            returnNode.add(new DefaultMutableTreeNode(getTreeModelNode("<empty>")));
        }
        return returnNode;
    }

    private MilleBorneCard getTreeModelNode(String label) {
        MilleBorneCard returnCard = new MilleBorneCard();
        returnCard.setName(label);
        returnCard.setZoneId(-1);
        returnCard.setTimerId(-1);
        returnCard.setSubType(-1);
        return returnCard;
    }

    private class testColumn extends PropertyRenderableColumn {

        public testColumn(ColumnLocation location, String header, String propertyExpression) {

            super(location, header, propertyExpression);

        }

        @Override
        public Component newCell(MarkupContainer parent, String id, TreeNode node, int level) {
            PropertyModel bla = new PropertyModel(node, getPropertyExpression());
            logger.error("#####>" + bla.getObject().toString());
            Long targetTimerId = Long.parseLong(bla.getObject().toString());
            if (targetTimerId > 0) {
                return new CardActionPanel(id, targetTimerId, gameId, testMilleBorneEngine);
            }
            return new Label(id, "");
        }

        @Override
        public IRenderable newCell(TreeNode node, int level) {
            PropertyModel bla = new PropertyModel(node, getPropertyExpression());
            logger.error("====>" + bla.getObject().toString());
            if (getTreeTable().getTreeState().isNodeSelected(node)) {
                return null;
            } else {
                return super.newCell(node, level);
            }
        }
    }
}
