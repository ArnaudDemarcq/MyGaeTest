/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.krohm.wicket.component.charts.jfreechart.util;

import java.awt.image.BufferedImage;
import org.apache.wicket.Resource;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.image.resource.DynamicImageResource;
import org.apache.wicket.protocol.http.WebResponse;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.JFreeChart;

/**
 *
 * @author arnaud
 */
public abstract class AbstractChartImage extends Image implements AbstractJfcChart {

    private static final long serialVersionUID = -7165602010769784429L;
    private transient BufferedImage image;
    private transient ChartRenderingInfo renderingInfo;

    public AbstractChartImage(String id) {
        super(id);
    }

    private BufferedImage createBufferedImage() {
        if (image == null) {
            renderingInfo = new ChartRenderingInfo();
            JFreeChart chart=getJFreeChart();
            image = chart.createBufferedImage(getWidth(), getHeight(), renderingInfo);
        }
        return image;
    }

    public ChartRenderingInfo getRenderingInfo() {
        if (renderingInfo == null) {
            createBufferedImage();
        }
        return renderingInfo;
    }

    @Override
    protected Resource getImageResource() {
        return new DynamicImageResource() {

            private static final long serialVersionUID = -4386816651419227671L;

            @Override
            protected byte[] getImageData() {
                return toImageData(createBufferedImage());
            }

            @Override
            protected void setHeaders(WebResponse response) {
                if (isCacheable()) {
                    super.setHeaders(response);
                } else {
                    response.setHeader("Pragma", "no-cache");
                    response.setHeader("Cache-Control", "no-cache");
                    response.setDateHeader("Expires", 0);
                }
            }
        };
    }

}
