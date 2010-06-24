/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.krohm.wicket.component.calendar.fullcalendar;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.RequestCycle;
import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.CSSPackageResource;
import org.apache.wicket.markup.html.JavascriptPackageResource;
import org.apache.wicket.markup.html.form.LabeledWebMarkupContainer;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.protocol.http.WebRequestCycle;
import org.apache.wicket.util.collections.MiniMap;
import org.apache.wicket.util.template.TextTemplateHeaderContributor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FullCalendarComponent extends LabeledWebMarkupContainer {

    private static final Logger logger = LoggerFactory.getLogger(FullCalendarComponent.class);
    // Constants
    private static final String ON_CLICK_EVENT = "eventClick";
    private static final String ON_DROP_EVENT = "eventDrop";
    private static final String EVENT_TYPE_KEY = "EVENT_TYPE";
    // Resources Folders
    private static final String RESOURCES_PATH = "resources";
    private static final String FC_PATH = RESOURCES_PATH + "/fullcalendar";
    private static final String JQUERY_PATH = FC_PATH + "/jquery";
    // JavaScript Libs
    private static final String FC_SCRIPT_NAME = FC_PATH + "/fullcalendar.min.js";
    private static final String JQUERY_SCRIPT_NAME = JQUERY_PATH + "/jquery.js";
    private static final String JQUERY_UI_SCRIPT_NAME = JQUERY_PATH + "/jquery-ui-custom.js";
    // Static CSSs
    private static final String FX_CSS_NAME = FC_PATH + "/fullcalendar.css";
    // Custom JavaScripts
    private static final String CUSTOM_JS_NAME = RESOURCES_PATH + "/custom.js";
    private static final String CUSTOM_JS_TEMPLATE_NAME = RESOURCES_PATH + "/customTemplate.tpl.js";
    // Utils
    private final Map<String, PseudoBehavior> pseudoBehaviors = getPseudoBehaviors();

    public FullCalendarComponent(MarkupContainer parent, String id) {
        super(id);
        parent.add(this);
        initJs();
    }

    public FullCalendarComponent(MarkupContainer parent, String id, IModel<?> model) {
        super(id, model);
        parent.add(this);
        initJs();
    }

    private final void initJs() {
        // Static JavaScripts
        add(JavascriptPackageResource.getHeaderContribution(FullCalendarComponent.class, JQUERY_SCRIPT_NAME));
        add(JavascriptPackageResource.getHeaderContribution(FullCalendarComponent.class, JQUERY_UI_SCRIPT_NAME));
        add(JavascriptPackageResource.getHeaderContribution(FullCalendarComponent.class, FC_SCRIPT_NAME));
        add(JavascriptPackageResource.getHeaderContribution(FullCalendarComponent.class, CUSTOM_JS_NAME));
        // Static CSSs
        add(CSSPackageResource.getHeaderContribution(FullCalendarComponent.class, FX_CSS_NAME));
        // Dynamic JavaScripts
        this.setOutputMarkupId(true);

        // Behaviours *** TEST ***
        AjaxEventBehavior testAjaxBehaviour1 = new AjaxEventBehavior(ON_DROP_EVENT) {

            @Override
            protected void onEvent(AjaxRequestTarget art) {

                logger.error("[***************************************]");
                Map originalMap = ((WebRequestCycle) RequestCycle.get()).getRequest().getParameterMap();
                Map<String, String> convertedMap = convertMap(originalMap);
                String eventType = convertedMap.get(EVENT_TYPE_KEY);
                PseudoBehavior currentPseudoBehavior = pseudoBehaviors.get(eventType);
                currentPseudoBehavior.execute(convertedMap);
                logger.error(currentPseudoBehavior.toString());

            }
        };


        add(testAjaxBehaviour1);
        add(TextTemplateHeaderContributor.forJavaScript(FullCalendarComponent.class,
                CUSTOM_JS_TEMPLATE_NAME, getTemplateKeys(this, testAjaxBehaviour1)));

    }
    /*
     * Overridable Methods
     */

    public List<EventBean> getEventList(Date startDate, Date endDate) {
        return null;
    }

    public void onEventDrop(EventBean currentEventBean, Integer dayDelta, Integer minuteDelta,
            Boolean allDay) {
        logger.error("we are in the eventDrop default Method");
        logger.error("" + currentEventBean);
        logger.error("" + dayDelta);
        logger.error("" + minuteDelta);
        logger.error("" + allDay);

    }

    public void onEventClick(EventBean currentEventBean) {
        logger.error("we are in the eventClick default Method");
        logger.error("" + currentEventBean);
    }

    /*
     * Static private Utils Methods
     */
    // Values for Dynamic JavaScripts
    // TODO => Remake it  getTemplateKeys (Map<String,Object>)
    private final static IModel<Map<String, Object>> getTemplateKeys(
            final FullCalendarComponent currentCalendar, final AjaxEventBehavior currentBehaviour) {


        IModel<Map<String, Object>> variablesModel = new AbstractReadOnlyModel<Map<String, Object>>() {

            private Map<String, Object> variables;
            private static final int MAX_ENTRIES = 32;

            public Map<String, Object> getObject() {
                if (variables == null) {
                    this.variables = new MiniMap<String, Object>(MAX_ENTRIES);
                    variables.put("markupId", currentCalendar.getMarkupId());
                    variables.put("eventBehaviourUrl", currentBehaviour.getCallbackUrl(true).toString());
                }
                return variables;
            }
        };
        return variablesModel;
    }

    private static final Map<String, String> convertMap(Map<Object, Object> originalMap) {
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
     *  PseudoBehaviours
     */
    private final Map<String, PseudoBehavior> getPseudoBehaviors() {
        Map<String, PseudoBehavior> returnMap = new HashMap<String, PseudoBehavior>();
        returnMap.put(ON_DROP_EVENT, new EventDragPseudoBehavior());
        returnMap.put(ON_CLICK_EVENT, new EventClickPseudoBehavior());
        return returnMap;
    }

    private interface PseudoBehavior {

        public void execute(Map<String, String> parameters);
    }

    private class EventDragPseudoBehavior implements PseudoBehavior {

        private static final String DAY_DELTA_KEY = "dayDelta";
        private static final String MINUTE_DELTA_KEY = "minuteDelta";
        private static final String ALL_DAY_KEY = "allDay";
        private static final String REVERT_FUNC_KEY = "revertFunc";

        public void execute(Map<String, String> parameters) {
            logger.error("we are in the eventDrop PseudoBehavior");
            try {
                EventBean currentEventBean = EventFactory.getEventBean(parameters);
                Integer dayDelta = Integer.parseInt(parameters.get(DAY_DELTA_KEY));
                Integer minuteDelta = Integer.parseInt(parameters.get(MINUTE_DELTA_KEY));
                Boolean allDay = "true".equals(parameters.get(ALL_DAY_KEY));

                onEventDrop(currentEventBean, dayDelta, minuteDelta, allDay);
            } catch (Exception ex) {
                logger.error("Error on Drag / Drop Event", ex);
            }

        }
    }

    private class EventClickPseudoBehavior implements PseudoBehavior {

        public void execute(Map<String, String> parameters) {
            try {
                EventBean currentEventBean = EventFactory.getEventBean(parameters);
                onEventClick(currentEventBean);
            } catch (Exception ex) {
                logger.error("Error on Click Event", ex);
            }
        }
    }
}
