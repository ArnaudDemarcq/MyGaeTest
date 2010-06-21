/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.pages;

import org.apache.wicket.PageParameters;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
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

        add(new CurrentTestComponent("calendarOne"));

    }
}
