package org.krohm.wicket.component.charts.jfreechart;

import java.awt.Font;
import java.util.Map;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.krohm.wicket.component.charts.PieChart;
import org.krohm.wicket.component.charts.jfreechart.parent.JfcPanel;

/**
 *
 * @author arnaud
 */
//implements PieChart
public abstract class JfcPieChart extends JfcPanel implements PieChart {

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

        JFreeChart jfc = ChartFactory.createPieChart(getTitle(), dataSet, true, true, false);
        PiePlot pp = (PiePlot) jfc.getPlot();
        pp.setSectionOutlinesVisible(false);
        pp.setLabelFont(getFont());
        pp.setCircular(false);
        pp.setLabelGap(0.02);
        return jfc;
    }

    @Override
    public String getTitle() {
        return "";
    }

    public Font getFont() {
        return new Font("SansSerif", Font.PLAIN, 12);
    }
}
