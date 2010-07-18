/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.krohm.milleborne;

import org.krohm.milleborne.util.UniqueId;

/**
 *
 * @author arnaud
 */
public class MilleBorneObject {

    private final long uniqueId = UniqueId.getUniqueId();
    private long timerId = UniqueId.getUniqueId();

    public long getTimerId() {
        return timerId;
    }

    public long getUniqueId() {
        return uniqueId;
    }

    public void setTimerId(long timerId) {
        this.timerId = timerId;
    }
}
