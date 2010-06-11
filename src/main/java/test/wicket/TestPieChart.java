/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.wicket;

import java.util.HashMap;
import java.util.Map;
import org.krohm.wicket.component.charts.jfreechart.JfcPieChart;

/**
 *
 * @author arnaud
 */
public class TestPieChart extends JfcPieChart {



    public TestPieChart(String id) {
        super(id);
    }

    @Override
    public Map<String, Number> getData() {
        Map<String, Number> testMap = new HashMap<String, Number>();
        testMap.put("Key1", getRandom(1, 15));
        testMap.put("Key2", getRandom(1, 15));
        testMap.put("Key3", getRandom(1, 15));
        return testMap;
    }

    @Override
    public String getTitle() {
        return "This is a test Pie Chart";
    }

    private int getRandom(int lower, int higher) {
        int random = (int) (Math.random() * (higher - lower)) + lower;
        return random;
    }

    @Override
    public boolean getLegend() {
        return true;
    }
}
