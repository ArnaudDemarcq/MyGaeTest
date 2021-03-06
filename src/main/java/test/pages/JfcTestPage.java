/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.pages;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.krohm.wicket.component.charts.jfreechart.JfcGraphChart;
import org.krohm.wicket.component.charts.jfreechart.JfcHistogramChart;
import org.krohm.wicket.component.charts.jfreechart.JfcPieChart;
import test.Index;

/**
 *
 * @author arnaud
 */
public class JfcTestPage extends PocMainPage {

    public JfcTestPage(PageParameters parameters) {
        super(parameters);

        /**
         *  A Random Pie Chart
         */
        JfcPieChart innerPieChart = new JfcPieChart("Inner") {

            @Override
            public Map<String, Number> getData() {
                return getPie1Data();
            }
        };
        innerPieChart.setTitle("This is a Pie Chart");
        innerPieChart.setTransparency(0.6F);
        add(innerPieChart);


        /**
         * A Random Inner Histogram
         */
        JfcHistogramChart innerHistogramChart = new JfcHistogramChart("InnerHisto") {

            @Override
            public Map<String, Map<String, Number>> getData() {
                return getHisto1Data();
            }
        };
        innerHistogramChart.setWidth(1000);
        innerHistogramChart.setTitle("This is an Histo Chart");
        add(innerHistogramChart);

        /**
         * A Random Graph Chart
         */
        JfcGraphChart testGraph = new JfcGraphChart("InnerGraph") {

            @Override
            public Map<String, Map<Number, Number>> getData() {
                return getGraphAllData();
            }
        };
        add(testGraph);

    }

    // Random Methods
    private int getRandom(int lower, int higher) {
        int random = (int) (Math.random() * (higher - lower)) + lower;
        return random;
    }

    private Map<String, Map<String, Number>> getHisto1Data() {
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

    private Map<String, Number> getPie1Data() {
        Map<String, Number> testMap = new LinkedHashMap<String, Number>();
        for (int i = 0; i < 3; i++) {
            testMap.put("Key " + (i + 1), getRandom(10, 25));
        }
        return testMap;
    }

    private Map<Number, Number> getGraph1Data() {
        Map<Number, Number> testMap = new HashMap<Number, Number>();
        Integer currentValue = 10;
        for (int i = 0; i < 50; i++) {
            currentValue += getRandom(-4, 5);
            testMap.put(i, currentValue);
        }
        return testMap;
    }

    private Map<String, Map<Number, Number>> getGraphAllData() {
        Map<String, Map<Number, Number>> returnMap = new LinkedHashMap<String, Map<Number, Number>>();
        for (int i = 0; i < 6; i++) {
            returnMap.put("Key " + (i + 1), getGraph1Data());
        }
        return returnMap;

    }

    private List<Color> getColors() {
        List<Color> colorList = new ArrayList<Color>();
        colorList.add(Color.BLACK);
        colorList.add(Color.DARK_GRAY);
        colorList.add(Color.LIGHT_GRAY);
        colorList.add(Color.WHITE);
        return colorList;
    }
}
