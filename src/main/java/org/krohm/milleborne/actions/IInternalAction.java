/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.krohm.milleborne.actions;

import org.krohm.milleborne.engineimpl.MilleBorneGame;

/**
 *
 * @author arnaud
 */
public interface IInternalAction extends IMilleBorneAction {

    public static int STATUS_NOT_VALIDATED = 0;
    public static int STATUS_VALIDATED = 1;
    public static int STATUS_REJECTED = -1;

    void execute(MilleBorneGame currentGame) throws Exception;

    int getValidationStatus();

    void setValidationStatus(int validationStatus);
}
