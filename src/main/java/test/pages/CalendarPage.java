/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.pages;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.wicket.PageParameters;
import org.apache.wicket.behavior.IBehavior;
import org.krohm.wicket.component.calendar.fullcalendar.EventBean;
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

        FullCalendarComponent calendarOne = new FullCalendarComponent(this, "calendarOne") {

            @Override
            public void onEventClick(EventBean currentEventBean) {
                logger.error("We are in the End User  Implementation !");
                logger.error("ID : <" + currentEventBean.getId() + ">");
                logger.error("TITLE : <" + currentEventBean.getTitle() + ">");
            }

            @Override
            public List<EventBean> getEventList(Date startDate, Date endDate) {
                List<EventBean> returnList = new ArrayList<EventBean>();

                returnList.add(getEventBean("First Event Ever !", new Date()));

                return returnList;
            }
        };

        /*
        for (IBehavior currentBehaviour : calendarOne.getBehaviors()) {
            logger.error(currentBehaviour.getClass().toString());
        }/**/



    }

    private EventBean getEventBean(String title, Date startDate) {
        EventBean returnEventBean = new EventBean();
        returnEventBean.setTitle(title);
        returnEventBean.setStart(startDate);
        return returnEventBean;


    }
}
