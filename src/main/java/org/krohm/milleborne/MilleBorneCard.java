/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.krohm.milleborne;

/**
 *
 * @author arnaud
 */
public class MilleBorneCard extends MilleBorneObject {

    /**
     * Card Information
     */
    private int type;
    private int subType;
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

    public int getSubType() {
        return subType;
    }

    public void setSubType(int subType) {
        this.subType = subType;
    }
}
