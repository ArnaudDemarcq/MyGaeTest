/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.krohm.milleborne.data;

/**
 *
 * @author arnaud
 */
public class CardTypes {

    public static final int TYPE_DISTANCE = 1;
    public static final int TYPE_OBSTACLE = 2;
    public static final int TYPE_PARADE = 3;
    public static final int TYPE_BOTTE = 4;

    public static class OBSTACLE_TYPES {

        public static final int TYPE_DRIVE = 100;
        public static final int TYPE_ACCIDENT = 101;
        public static final int TYPE_GAS = 102;
        public static final int TYPE_TIRE = 103;
        public static final int TYPE_SPEED_LIMIT = 104;
    }

    public static class PARADE_TYPES {

        public static final int TYPE_DRIVE = 200;
        public static final int TYPE_ACCIDENT = 201;
        public static final int TYPE_GAS = 202;
        public static final int TYPE_TIRE = 303;
        public static final int TYPE_SPEED_LIMIT = 204;
    }

    public static class BOTTES_TYPES {

        public static final int TYPE_ACCIDENT = 301;
        public static final int TYPE_GAS = 302;
        public static final int TYPE_TIRE = 303;
        public static final int TYPE_PRIORITARY = 304;
    }
}
