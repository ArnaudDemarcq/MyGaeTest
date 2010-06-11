package org.krohm.wicket.component.charts.jfreechart;

import java.util.Map;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.krohm.wicket.component.charts.definition.HistogramChart;
import org.krohm.wicket.component.charts.jfreechart.util.AbstractChartImage;

/**
 *
 * @author arnaud
 */
//implements PieChart
public abstract class JfcHistogramChart extends AbstractChartImage implements HistogramChart {

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

        CategoryPlot pp = (CategoryPlot) chart.getPlot();


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
