/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.wicket;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import org.krohm.wicket.component.charts.jfreechart.JfcHistogramChart;

/**
 *
 * @author arnaud
 */
public class TestHistogramChart extends JfcHistogramChart {

    public TestHistogramChart(String id) {
        super(id);
    }

    public Map<String, Map<String, Number>> getData() {
        Map<String, Map<String, Number>> returnMap = new LinkedHashMap<String, Map<String, Number>>();
        for (int i = 0; i < 4; i++) {
            Map<String, Number> currentMap = new LinkedHashMap<String, Number>();
            returnMap.put("Category " + (i + 1), currentMap);
            for (int j = 0; j < 10; j++) {
                currentMap.put("Data " + (j + 1), getRandom(2, 14));
            }
        }
        return returnMap;
    }

    private int getRandom(int lower, int higher) {
        int random = (int) (Math.random() * (higher - lower)) + lower;
        return random;
    }

    @Override
    public String getTitle() {
        return "This is a Test Histogram";
    }

    @Override
    public String getXAxisLabel() {
        return "X";
    }

    @Override
    public String getYAxisLabel() {
        return "Y";
    }

    @Override
    public int getWidth() {
        return 1000;
    }

    @Override
    public int getHeight() {
        return 250;
    }
}
