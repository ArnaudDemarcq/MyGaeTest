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
public class MilleBorneCard {

    /**
     * Card Information
     */
    private long uniqueId = UniqueId.getUniqueId();
    private long timerId = UniqueId.getUniqueId();
    private int type;
    private int dist;
    private String name;
    private long controlerId;
    private long zoneId;

    /**
     * And Corresponding getters /setters
     *
     */
    public int getDist() {
        return dist;
    }

    public void setDist(int dist) {
        this.dist = dist;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getUniqueId() {
        return uniqueId;
    }

    public long getControlerId() {
        return controlerId;
    }

    public void setControlerId(long controlerId) {
        this.controlerId = controlerId;
    }

    public long getZoneId() {
        return zoneId;
    }

    public void setZoneId(long zoneId) {
        this.zoneId = zoneId;
    }

    public long getTimerId() {
        return timerId;
    }

    public void setTimerId(long timerId) {
        this.timerId = timerId;
    }
    
}
