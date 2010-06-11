package test;

import beans.TestEntity;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.krohm.wicket.component.charts.jfreechart.JfcHistogramChart;
import org.krohm.wicket.component.charts.jfreechart.JfcPieChart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import test.dao.GenericDao;

/**
 * Homepage
 */
public class Index extends WebPage {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(Index.class);
    @SpringBean(name = "testEntityDao2")
    GenericDao<TestEntity> testEntityDao2;

    public Index(final PageParameters parameters) {

        TestEntity tmpTestEntity = new TestEntity();
        tmpTestEntity.setIntValue(12);
        tmpTestEntity.setStringValue("This is a Entity String");

        // creates a TestEntityBean
        testEntityDao2.create(tmpTestEntity);
        logger.error(">>>>>>>>>>>>>>>>>" + tmpTestEntity.getId());/**/


        // Add the simplest type of label
        String tmpText = "Wicket Hello World :";
        add(new Label("message", tmpText + tmpTestEntity.getId()));


        // A Random Inner Pie
        JfcPieChart innerPieChart = new JfcPieChart("Inner") {

            @Override
            public Map<String, Number> getData() {
                return getPie1Data();
            }
        };
        innerPieChart.setWidth(500);
        innerPieChart.setTitle("This is a Chart Defined as an Inner Class");
        add(innerPieChart);


        // And a Random Inner Histogram
        JfcHistogramChart innerHistogramChart = new JfcHistogramChart("InnerHisto") {

            @Override
            public Map<String, Map<String, Number>> getData() {
                return getHisto1Data();
            }
        };
        innerHistogramChart.setWidth(1000);
        innerHistogramChart.setTitle("This is a Chart Defined as an Inner Class");
        innerHistogramChart.setColorList(getColors());
        innerHistogramChart.setTransparency(0.6F);
        add(innerHistogramChart);
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
        Map<String, Number> testMap = new HashMap<String, Number>();
        for (int i = 0; i < 3; i++) {
            testMap.put("Key " + (i + 1), getRandom(10, 25));
        }
        return testMap;
    }

    private List<Color> getColors() {
        List<Color> colorList = new ArrayList<Color>();
        colorList.add(Color.BLUE);
        colorList.add(Color.WHITE);
        colorList.add(Color.BLACK);
        colorList.add(Color.PINK);
        return colorList;
    }
}
