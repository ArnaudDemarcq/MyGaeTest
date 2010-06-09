package test;

import beans.TestEntity;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
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
        add(new TestComponent("hop"));

        /*
        DefaultPieDataset j = new DefaultPieDataset();
        j.setValue("UNO", new Double(20.0));
        j.setValue("DUE", new Double(10.0));
        j.setValue("TRE", new Double(20.0));
        j.setValue("QUATTRO", new Double(30.0));
        j.setValue("CINQUE", new Double(20.0));

        JFreeChart jfc = ChartFactory.createPieChart("testChart", j, true, true, false);
        PiePlot pp = (PiePlot) jfc.getPlot();
        pp.setSectionOutlinesVisible(false);
        pp.setLabelFont(new Font("SansSerif", Font.PLAIN, 12));
        pp.setNoDataMessage("Nessun Dato Inserito");
        pp.setCircular(false);
        pp.setLabelGap(0.02);

        MappedChart myChart = new MappedChart("jfreechart", jfc, 100  , 100){

        @Override
        protected void onClickCallback(AjaxRequestTarget target, ChartEntity entity) {
        logger.debug("bla");
        }
        };

        Image myImage = new ChartImage( "jfreechart", jfc, 1000  , 1000);
        add (myImage);/**/

        /*
        Panel myPanel = new JfcPieChart("jfreechart", 1000, 1000) {

        @Override
        public Map<String, Number> getData() {
        logger.debug("test");
        return null;
        }


        };/**/
        Panel myPanel = new TestPieChart("jfreechart", 1000, 1000);
        add(myPanel);

    }
}
