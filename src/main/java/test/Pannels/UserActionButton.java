/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.Pannels;

import org.apache.wicket.markup.html.form.Button;
import org.krohm.milleborne.IMilleBorneEngine;
import org.krohm.milleborne.actions.IUserAction;

/**
 *
 * @author arnaud
 */
public class UserActionButton extends Button {

    private IUserAction userAction;
    private IMilleBorneEngine mbe;
    private long gameId;

    public UserActionButton(String id, IMilleBorneEngine mbe,
            long gameId, IUserAction userAction) {
        super(id);
        this.userAction = userAction;
        this.mbe = mbe;
        this.gameId = gameId;
    }

    @Override
    public void onSubmit() {
        mbe.executeAction(gameId, userAction);
    }
}
