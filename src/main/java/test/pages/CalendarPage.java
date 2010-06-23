/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.pages;

import org.apache.wicket.PageParameters;
import org.apache.wicket.behavior.IBehavior;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.krohm.wicket.component.calendar.fullcalendar.FullCalendarComponent;

/**
 *
 * @author arnaud
 */
public class CalendarPage extends PocMainPage {

    private static final Logger logger = LoggerFactory.getLogger(CalendarPage.class);

    public CalendarPage(PageParameters parameters) {
        super(parameters);

        FullCalendarComponent calendarOne = new FullCalendarComponent(this,"calendarOne");

        for (IBehavior currentBehaviour : calendarOne.getBehaviors()) {
            logger.error(currentBehaviour.getClass().toString());
        }



    }
}
