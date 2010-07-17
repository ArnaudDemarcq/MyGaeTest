/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.krohm.milleborne.util;

import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

/**
 *
 * @author arnaud
 */
public class UniqueId {

    private static AtomicLong currentId = new AtomicLong(new Date().getTime());

    public static long getUniqueId() {
        return currentId.incrementAndGet();
    }
}
