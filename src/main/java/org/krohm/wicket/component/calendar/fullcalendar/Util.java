/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.krohm.wicket.component.calendar.fullcalendar;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.collections.MiniMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author arnaud
 */
public class Util {

    private static final Logger logger = LoggerFactory.getLogger(Util.class);
    /*
     * Static public Utils Methods
     */

    protected final static IModel<Map<String, Object>> getTemplateKeys(final Map<String, Object> originalMap) {


        IModel<Map<String, Object>> variablesModel = new AbstractReadOnlyModel<Map<String, Object>>() {

            private Map<String, Object> variables;
            private static final int MAX_ENTRIES = 32;

            public Map<String, Object> getObject() {
                if (variables == null) {
                    this.variables = new MiniMap<String, Object>(MAX_ENTRIES);
                    for (String key : originalMap.keySet()) {
                        variables.put(key, originalMap.get(key));
                    }
                }
                return variables;
            }
        };
        return variablesModel;
    }

    // to convert data from HTTP Request to usable data
    protected static final Map<String, String> convertMap(Map<Object, Object> originalMap) {
        Map<String, String> returnMap = new HashMap<String, String>();
        for (Object objectKey : originalMap.keySet()) {
            Object[] objectValue = (Object[]) originalMap.get(objectKey);
            if (logger.isDebugEnabled()) {
                logger.debug("Found key :<" + objectKey + "> With value :<" + objectValue[0] + ">");
            }
            String key = (String) objectKey;
            String value = (String) objectValue[0];
            returnMap.put(key, value);
        }
        return returnMap;
    }

    /*
     * Private Methods
     */
    // todo : use stringbuffers
    protected final static String getEventListJson(List<EventBean> eventList) {
        if (eventList == null)
        {
            return "[]";
        }
        String returnString = "[";
        int eventNumber = 0;
        for (EventBean currentBean : eventList){
            if (eventNumber > 0){
                returnString +=",\n";
            }
            returnString +=currentBean.toJson();
            eventNumber ++;
        }
        returnString +="]";
        return returnString;
    }
}
