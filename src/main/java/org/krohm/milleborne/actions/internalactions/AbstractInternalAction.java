/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.krohm.milleborne.actions.internalactions;

import org.krohm.milleborne.actions.IInternalAction;

/**
 *
 * @author arnaud
 */
public abstract class AbstractInternalAction implements IInternalAction {

    private int validationStatus = 0;

    public int getValidationStatus() {
        return validationStatus;
    }

    public void setValidationStatus(final int validationStatus) {
        this.validationStatus = validationStatus;
    }
}
