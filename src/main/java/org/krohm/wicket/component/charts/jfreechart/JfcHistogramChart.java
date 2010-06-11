package org.krohm.wicket.component.charts.jfreechart;

import java.awt.Font;
import java.util.List;
import java.util.Map;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisSpace;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.statistics.HistogramType;
import org.jfree.ui.RectangleInsets;
import org.krohm.wicket.component.charts.HistogramChart;
import org.krohm.wicket.component.charts.PieChart;
import org.krohm.wicket.component.charts.jfreechart.parent.JfcPanel;

/**
 *
 * @author arnaud
 */
//implements PieChart
public abstract class JfcHistogramChart extends JfcPanel implements HistogramChart {

    public JfcHistogramChart(String id) {
        super(id);
    }

    @Override
    public final JFreeChart getJFreeChart() {
        Map<String, Map<String, Number>> dataMap = this.getData();

        DefaultCategoryDataset histoDataSet = new DefaultCategoryDataset();
        for (String currentCategory : dataMap.keySet()) {
            Map<String, Number> currentCategoryData = dataMap.get(currentCategory);
            for (String currentId : currentCategoryData.keySet()) {
                Number currentValue = currentCategoryData.get(currentId);
                histoDataSet.addValue(currentValue, currentCategory, currentId);
            }
        }

        JFreeChart chart = ChartFactory.createBarChart3D(getTitle(),
                getXAxisLabel(), getYAxisLabel(),
                histoDataSet,
                getOrientation(), getLegend(),
                true, true);
        return chart;
    }

    // Default Title
    @Override
    public String getTitle() {
        return "";
    }

    public PlotOrientation getOrientation() {
        return PlotOrientation.VERTICAL;
    }

    public String getXAxisLabel() {
        return "";
    }

    public String getYAxisLabel() {
        return "";
    }

    // default Legend
    public boolean getLegend() {
        return true;
    }

    //default Width
    @Override
    public int getWidth() {
        return 250;
    }

    //default Heigth
    @Override
    public int getHeight() {
        return 250;
    }
}
