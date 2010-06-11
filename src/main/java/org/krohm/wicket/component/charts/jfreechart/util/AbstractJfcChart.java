/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.krohm.wicket.component.charts.jfreechart.util;

import org.jfree.chart.JFreeChart;

/**
 *
 * @author arnaud
 */
public interface AbstractJfcChart {

    public abstract JFreeChart getJFreeChart();

    public int getWidth();

    public int getHeight();
}
