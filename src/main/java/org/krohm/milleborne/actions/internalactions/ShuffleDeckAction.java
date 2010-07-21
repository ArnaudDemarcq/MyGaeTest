/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.krohm.milleborne.actions.internalactions;

import org.krohm.milleborne.actions.IInternalAction;
import org.krohm.milleborne.engineimpl.MilleBorneGame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author arnaud
 */
public class ShuffleDeckAction extends AbstractInternalAction {

    private static final Logger logger = LoggerFactory.getLogger(ShuffleDeckAction.class);

    public void execute(MilleBorneGame currentGame) throws Exception {
        logger.error("About to Shuffle !");
    }
}
