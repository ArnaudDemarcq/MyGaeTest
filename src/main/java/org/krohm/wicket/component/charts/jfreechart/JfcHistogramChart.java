package org.krohm.wicket.component.charts.jfreechart;

import java.awt.Color;
import java.util.List;
import java.util.Map;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.krohm.wicket.component.charts.jfreechart.util.AbstractChartImage;

/**
 *
 * @author arnaud
 */
//implements PieChart
public abstract class JfcHistogramChart extends AbstractChartImage {

    protected int height = 250;
    protected int width = 250;
    protected boolean legend = true;
    protected String title = "";
    protected String XAxisLabel = "";
    protected String YAxisLabel = "";
    protected PlotOrientation Orientation = PlotOrientation.VERTICAL;
    protected List<Color> colorList = null;
    protected float transparency = 1F;

    public JfcHistogramChart(String id) {
        super(id);
    }

    // Data Required for a Histogram Chart
    public abstract Map<String, Map<String, Number>> getData();

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

        // Manages the colors
        if (colorList != null) {
            CategoryItemRenderer renderer = pp.getRenderer();
            for (int i = 0; i < colorList.size(); i++) {
                renderer.setSeriesPaint(i, colorList.get(i));
            }
        }
        // Manages Transparency
        pp.setForegroundAlpha(transparency);
        
        return chart;
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

    public PlotOrientation getOrientation() {
        return Orientation;
    }

    public void setOrientation(PlotOrientation Orientation) {
        this.Orientation = Orientation;
    }

    public String getXAxisLabel() {
        return XAxisLabel;
    }

    public void setXAxisLabel(String XAxisLabel) {
        this.XAxisLabel = XAxisLabel;
    }

    public String getYAxisLabel() {
        return YAxisLabel;
    }

    public void setYAxisLabel(String YAxisLabel) {
        this.YAxisLabel = YAxisLabel;
    }

    public boolean getLegend() {
        return legend;
    }

    public void setLegend(boolean legend) {
        this.legend = legend;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Color> getColorList() {
        return colorList;
    }

    public void setColorList(List<Color> colorList) {
        this.colorList = colorList;
    }

    public float getTransparency() {
        return transparency;
    }

    public void setTransparency(float transparency) {
        this.transparency = transparency;
    }
}
