/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.Pannels;

import org.apache.wicket.markup.html.JavascriptPackageResource;
import org.krohm.wicket.component.calendar.fullcalendar.FullCalendarComponent;

/**
 *
 * @author arnaud
 */
public class FullCalendarPanel extends FullCalendarComponent {

    private static final String TEST_FC_SCRIPT_NAME = "CalendarTest.js";

    public FullCalendarPanel(String id) {
        super(id);
        add(JavascriptPackageResource.getHeaderContribution(FullCalendarPanel.class, TEST_FC_SCRIPT_NAME));
    }
}
