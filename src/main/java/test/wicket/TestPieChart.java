/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.wicket;

import java.lang.Number;
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
        testMap.put("Key1", 12);
        testMap.put("Key2", 3);
        testMap.put("Key3", 24);
        return testMap;
    }

    @Override
    public String getTitle() {
        return "This is a test Pie Chart";
    }

    @Override
    public int getWidth() {
        return 250;
    }

    @Override
    public int getHeight() {
        return 250;
    }
}
