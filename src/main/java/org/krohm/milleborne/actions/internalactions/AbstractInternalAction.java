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
public abstract class AbstractInternalAction implements IInternalAction
{
    private Boolean validated = null;

    public Boolean getValidated() {
        return validated;
    }

    public void setValidated(Boolean validated) {
        this.validated = validated;
    }
    

    

}
