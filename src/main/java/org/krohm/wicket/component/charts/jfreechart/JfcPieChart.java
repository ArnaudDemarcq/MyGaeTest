package org.krohm.wicket.component.charts.jfreechart;

import java.awt.Font;
import java.util.Map;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.krohm.wicket.component.charts.jfreechart.util.AbstractChartImage;

/**
 *
 * @author arnaud
 */
//implements PieChart
public abstract class JfcPieChart extends AbstractChartImage {

    protected int height = 250;
    protected int width = 250;
    protected boolean legend = true;
    protected Font font = new Font("SansSerif", Font.PLAIN, 12);
    protected String title = "";

    public JfcPieChart(String id) {
        super(id);
    }

    // Data Required for a Pie
    public abstract Map<String, Number> getData();

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

    //
    // BeanLike Abilities
    //
    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String getTitle() {
        return title;
    }

    public Font getFont() {
        return font;
    }

    public boolean getLegend() {
        return legend;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public void setLegend(boolean legend) {
        this.legend = legend;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
