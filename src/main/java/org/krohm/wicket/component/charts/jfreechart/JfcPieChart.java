package org.krohm.wicket.component.charts.jfreechart;

import java.awt.Font;
import java.util.Map;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.krohm.wicket.component.charts.definition.PieChart;
import org.krohm.wicket.component.charts.jfreechart.util.AbstractChartImage;

/**
 *
 * @author arnaud
 */
//implements PieChart
public abstract class JfcPieChart extends AbstractChartImage implements PieChart {

    protected int DEFAULT_HEIGHT = 250;
    protected int DEFAULT_WIDTH = 250;
    protected boolean DEFAULT_LEGEND = true;
    protected Font DEFAULT_FONT = new Font("SansSerif", Font.PLAIN, 12);
    protected String DEFAULT_TITLE = "";

    public JfcPieChart(String id) {
        super(id);
    }

    @Override
    public final JFreeChart getJFreeChart() {

        Map<String, Number> dataMap = this.getData();
        DefaultPieDataset dataSet = new DefaultPieDataset();

        for (String key : dataMap.keySet()) {
            dataSet.setValue(key, dataMap.get(key));
        }

        JFreeChart jfc = ChartFactory.createPieChart(getTitle(), dataSet, getLegend(), true, false);
        PiePlot pp = (PiePlot) jfc.getPlot();
        pp.setSectionOutlinesVisible(false);
        pp.setLabelFont(getFont());
        pp.setCircular(false);
        pp.setLabelGap(0.02);
        return jfc;
    }

    @Override
    public String getTitle() {
        return DEFAULT_TITLE;
    }

    public Font getFont() {
        return DEFAULT_FONT;
    }

    public boolean getLegend() {
        return DEFAULT_LEGEND;
    }

    @Override
    public int getWidth() {
        return DEFAULT_HEIGHT;
    }

    @Override
    public int getHeight() {
        return DEFAULT_WIDTH;
    }
}
