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
public class BotteCard extends MilleBorneCard {

    public BotteCard(String name, int subType) {
        this.setName(name);
        this.setSubType(subType);
        this.setType(CardTypes.TYPE_BOTTE);
    }
}
