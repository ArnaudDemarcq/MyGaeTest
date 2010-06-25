/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.krohm.wicket.component.calendar.fullcalendar;

import java.util.Date;
import java.util.Map;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author arnaud
 */
public class EventBean {

    // public constants
    public static final String EVENT_CLASS_KEY = "EVENT_CLASS_NAME";
    private static final Logger logger = LoggerFactory.getLogger(FullCalendarComponent.class);
    // Constants
    private static final String EVENT_ROOT = "Event_";
    private static final String EVENT_ID_KEY = EVENT_ROOT + "id";
    private static final String EVENT_TITLE_KEY = EVENT_ROOT + "title";
    private static final String EVENT_START_KEY = EVENT_ROOT + "GEN_start";
    // variables
    private String id;
    private String title;
    private Boolean allDay;
    private Date start;
    private Date end;
    private String url;

    public EventBean() {
    }

    public EventBean(Map<String, String> dataMap) {
        populateFromMap(dataMap);
    }

    public void populateFromMap(Map<String, String> dataMap) {
        for (String key : dataMap.keySet()) {
            String value = dataMap.get(key);
            logger.error("Found key :<" + key + "> With value :<" + value + ">");
        }
        // Mandatory Fields :
        this.setTitle(dataMap.get(EVENT_TITLE_KEY));
        // Optional Fields :
        if (dataMap.get(EVENT_ID_KEY) != null) {
            this.setId(dataMap.get(EVENT_ID_KEY));
        }

        // Dates
        String startString = dataMap.get(EVENT_START_KEY);
        Long startLong = Long.parseLong(startString);
        Date startDate = new Date(startLong);
        logger.error("=>>>>>>>>>>>>>>>" + startDate);



    }

    public Boolean getAllDay() {
        return allDay;
    }

    public void setAllDay(Boolean allDay) {
        this.allDay = allDay;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    /*
     * Json Serialization
     */

    /*
    public final String toJson() {
        JSONObject jsonObject = JSONObject.fromBean(this);
        return jsonObject.toString();
      //  return "{\n" + jsonContent() + "\n}";
    }

    // todo : change to StringWriter
    
    protected String jsonContent() {
        String returnString = "";
        returnString += "\"title\": '" + getTitle() + "',\n";
        returnString += "\"start\": 1278799200";
        return returnString;
    } //&start=1275170400&end=1278799200 /**/
}
