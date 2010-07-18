/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.Pannels;

import org.apache.wicket.markup.html.form.Form;
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

        // Define Actions
        PlayCardAction targetPlayCardAction = new PlayCardAction();
        DiscardAction targetDiscardAction = new DiscardAction();
        CoupFourreAction targetCoupFourreAction = new CoupFourreAction();


        // add Buttons
        currentForm.add(new UserActionButton("PlayButton", mbe, gameId, targetPlayCardAction));
        currentForm.add(new UserActionButton("CoupFourreButton", mbe, gameId, targetCoupFourreAction));
        currentForm.add(new UserActionButton("DiscardButton", mbe, gameId, targetDiscardAction));
    }

}