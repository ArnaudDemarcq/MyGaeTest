/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.krohm.milleborne.actions;

import org.krohm.milleborne.MilleBorneGame;

/**
 *
 * @author arnaud
 */
public interface IInternalAction extends IMilleBorneAction{

    public void execute(MilleBorneGame currentGame) throws Exception;
    

}
