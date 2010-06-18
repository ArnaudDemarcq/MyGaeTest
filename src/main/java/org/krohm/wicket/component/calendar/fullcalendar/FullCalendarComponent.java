/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.krohm.wicket.component.calendar.fullcalendar;

import org.apache.wicket.markup.html.JavascriptPackageResource;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.html.resources.StyleSheetReference;

/**
 *
 * @author arnaud
 */
//public class TestComponent extends MarkupContainer {
public class FullCalendarComponent extends Panel {

    private static final String FC_SCRIPT_NAME = "fullcalendar.min.js";
    private static final String JQUERY_SCRIPT_NAME = "jquery.js";
    private static final String JQUERY_UI_SCRIPT_NAME = "jquery-ui-custom.js";
    private static final String FX_CSS_NAME = "fullcalendar.css";

    public FullCalendarComponent(String id) {
        super(id);
        add(JavascriptPackageResource.getHeaderContribution(FullCalendarComponent.class, JQUERY_SCRIPT_NAME));
        add(JavascriptPackageResource.getHeaderContribution(FullCalendarComponent.class, JQUERY_UI_SCRIPT_NAME));
        add(JavascriptPackageResource.getHeaderContribution(FullCalendarComponent.class, FC_SCRIPT_NAME));
        //add(JavascriptPackageResource.getHeaderContribution(FullCalendarComponent.class, FX_CSS_NAME));

    }
}
