package test;

import beans.TestEntity;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.Map;

import org.apache.wicket.PageParameters;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.entity.ChartEntity;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.chart.plot.PlotState;
import org.jfree.data.general.DefaultPieDataset;
import org.krohm.wicket.component.charts.jfreechart.JfcPieChart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import test.dao.GenericDao;
import test.wicket.TestHistogramChart;
import test.wicket.TestPieChart;
import test.wicket.component.TestComponent;
import test.wicket.component.jfreechart.MappedChart;
import test.wicket.component.jfreechart.util.ChartImage;
import test.wicket.component.raphael.RaphaelComponent;

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


        /*
        //Panel myPanel = new TestPieChart("jfreechart");
        Panel myPanelHisto = new TestHistogramChart("Histo");
        add(myPanelHisto);

        //Panel myPanel = new TestPieChart("jfreechart");
        Panel myPanelPie = new TestPieChart("Pie");
        add(myPanelPie); /**/

        add(new TestPieChart("Pie"));
        add(new TestHistogramChart("Histo"));
        add(new JfcPieChart("Inner") {

            public Map<String, Number> getData() {

                Map<String, Number> test = new HashMap<String, Number>();
                test.put("hop", 12);
                test.put("hip", 4);
                return test;
            }
        });

    }
}
