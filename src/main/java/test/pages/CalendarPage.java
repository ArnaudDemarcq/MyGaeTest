/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.pages;

import java.util.Map;
import org.apache.wicket.PageParameters;
import org.apache.wicket.RequestCycle;
import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.behavior.IBehavior;
import org.apache.wicket.protocol.http.WebRequestCycle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import test.Pannels.FullCalendarPanel;
import test.wicket.component.CurrentTestComponent;

/**
 *
 * @author arnaud
 */
public class CalendarPage extends PocMainPage {

    private static final Logger logger = LoggerFactory.getLogger(CalendarPage.class);

    public CalendarPage(PageParameters parameters) {
        super(parameters);

        CurrentTestComponent calendarOne = new CurrentTestComponent(this,"calendarOne");

        for (IBehavior currentBehaviour : calendarOne.getBehaviors()) {
            logger.error(currentBehaviour.getClass().toString());
        }



    }
}
