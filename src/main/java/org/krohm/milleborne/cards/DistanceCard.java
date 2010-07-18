/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.krohm.milleborne.cards;

import org.krohm.milleborne.MilleBorneCard;
import org.krohm.milleborne.data.CardTypes;

/**
 *
 * @author arnaud
 */
public class DistanceCard extends MilleBorneCard {

    public DistanceCard(int DistanceValue) {
        this.setType(CardTypes.TYPE_DISTANCE);
        this.setDist(DistanceValue);
        this.setName("" + DistanceValue +" Bornes");
    }
}
