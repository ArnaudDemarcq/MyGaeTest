/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.Pannels;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.panel.Panel;
import org.krohm.milleborne.IMilleBorneEngine;
import org.krohm.milleborne.actions.useractions.CoupFourreAction;
import org.krohm.milleborne.actions.useractions.DiscardAction;
import org.krohm.milleborne.actions.useractions.PlayCardAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author arnaud
 */
public class CardActionPanel extends Panel {

    private long gameId;
    private long cardTimerId;
    private IMilleBorneEngine mbe;
    private static final Logger logger = LoggerFactory.getLogger(CardActionPanel.class);

    public CardActionPanel(String id, long gameIdIn, long cardTimerIdIn, IMilleBorneEngine mbeIn) {
        super(id);
        this.gameId = gameIdIn;
        this.cardTimerId = cardTimerIdIn;
        this.mbe = mbeIn;


        Form currentForm = new Form("actionForm") {

            @Override
            protected void onSubmit() {
                logger.error("Dont click at the form !");
            }
        };
        add(currentForm);


        //currentForm.add(new Label("testLabel", "Gni !" + gameId + "-" + cardTimerId));
        currentForm.add(new Button("PlayButton") {

            @Override
            public void onSubmit() {
                logger.error("Attempting to play card <" + cardTimerId + "> of game <" + gameId + ">");
                PlayCardAction currentAction = new PlayCardAction();

                mbe.executeAction(gameId, currentAction);


            }
        });

        currentForm.add(new Button("DiscardButton") {

            @Override
            public void onSubmit() {
                logger.error("Attempting to discard card <" + cardTimerId + "> of game <" + gameId + ">");
                DiscardAction currentAction = new DiscardAction();

                mbe.executeAction(gameId, currentAction);
            }
        });

        currentForm.add(new Button("CoupFourreButton") {

            @Override
            public void onSubmit() {
                logger.error("Attempting to Coup Fourre card <" + cardTimerId + "> of game <" + gameId + ">");
                CoupFourreAction currentAction = new CoupFourreAction();

                mbe.executeAction(gameId, currentAction);
            }
        });
    }
}
