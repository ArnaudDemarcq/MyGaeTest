/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.krohm.wicket.component.charts;

import java.awt.Font;
import java.util.List;
import java.util.Map;

/**
 *
 * @author arnaud
 */
public interface HistogramChart {

    public Map<String, Map<String, Number>> getData();

    public String getTitle();

    

}