/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.krohm.wicket.component.charts.jfreechart.parent;

import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.panel.Panel;
import org.jfree.chart.JFreeChart;
import org.krohm.wicket.component.charts.jfreechart.util.ChartImage;

/**
 *
 * @author arnaud
 */
public abstract class JfcPanel extends Panel {

    protected static final String IMAGE_ID = "image";

    public JfcPanel(String id) {
        super(id);
        Image JfcImage = new ChartImage(IMAGE_ID, getJFreeChart(), getWidth(), getHeight());
        add(JfcImage);
    }

    public abstract JFreeChart getJFreeChart();

    public abstract int getWidth();

    public abstract int getHeight();

    @Override
    public boolean isTransparentResolver() {
        return true;
    }


}
