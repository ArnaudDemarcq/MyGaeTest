/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.krohm.wicket.component.calendar.fullcalendar;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.wicket.IRequestTarget;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.RequestCycle;
import org.apache.wicket.Response;
import org.apache.wicket.ajax.AbstractDefaultAjaxBehavior;
import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.CSSPackageResource;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.markup.html.JavascriptPackageResource;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.LabeledWebMarkupContainer;
import org.apache.wicket.model.IModel;
import org.apache.wicket.protocol.http.WebRequestCycle;
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

        // Behaviour for Event Management (Drag / Drop / Click ...)
        AjaxEventBehavior eventManagerAjaxBehaviour = new AjaxEventBehavior("EventBehavior") {

            @Override
            protected void onEvent(AjaxRequestTarget art) {

                logger.error("[***************************************]");
                Map originalMap = ((WebRequestCycle) RequestCycle.get()).getRequest().getParameterMap();
                Map<String, String> convertedMap = Util.convertMap(originalMap);
                String eventType = convertedMap.get(EVENT_TYPE_KEY);
                PseudoBehavior currentPseudoBehavior = pseudoBehaviors.get(eventType);
                currentPseudoBehavior.execute(convertedMap);
            }
        };
        add(eventManagerAjaxBehaviour);


        IRequestTarget testIRequestTarget = new IRequestTarget() {

            public void detach(RequestCycle requestCycle) {
            }

            public void respond(RequestCycle requestCycle) {
                logger.error("[#######################################]");
            }
        };
        // Behavior for dynamic Event List Management

        AbstractDefaultAjaxBehavior eventListAjaxBehaviour = new AbstractDefaultAjaxBehavior() {

            @Override
            protected void respond(AjaxRequestTarget target) {
                logger.error("We ARE in the respond Method !!");
                logger.error("[#######################################]");
                // getArguments
                Map originalMap = ((WebRequestCycle) RequestCycle.get()).getRequest().getParameterMap();
                Map<String, String> convertedMap = Util.convertMap(originalMap);
                Date startDate = new Date(1000 * Long.parseLong(convertedMap.get("start")));
                Date endDate = new Date(1000 * Long.parseLong(convertedMap.get("end")));
                logger.error("Start Date :<" + startDate + ">. End Date :<" + endDate + ">.");
                logger.error(convertedMap.toString());

                getRequestCycle().setRequestTarget(new IRequestTarget() {

                    @Override
                    public void detach(RequestCycle requestCycle) {
                        // nothing to do here
                    }

                    @Override
                    public void respond(RequestCycle requestCycle) {
                        // Add JSON script to the response
                        String eventJson = Util.getEventListJson(getEventList(new Date(), new Date())).toString();
                        logger.error(eventJson);
                        requestCycle.getResponse().write(eventJson);
                    }
                });


                //Response currentResponse = RequestCycle.get().getResponse();
                //currentResponse.reset();

                //String eventJson = Util.getEventListJson(getEventList(new Date(), new Date()));
                //logger.error(eventJson);
                //currentResponse.println(eventJson + "\n \\\\ ");
                //
                //target.appendJavascript(eventJson +"\n \\\\\ ");
            }

            /*
            protected void respond_old(AjaxRequestTarget target) {
                logger.error("We ARE in the respond Method !!");
                logger.error("[#######################################]");
                Response currentResponse = RequestCycle.get().getResponse();
                currentResponse.reset();
                String eventJson = Util.getEventListJson(getEventList(new Date(), new Date()));
                logger.error(eventJson);
                target.appendJavascript("testFunction(" + eventJson + ");");
            }/**/
        };
        add(eventListAjaxBehaviour);

        // Template Map Generation
        Map<String, Object> originalTemplateMap = new HashMap<String, Object>();
        originalTemplateMap.put("markupId", getMarkupId());
        originalTemplateMap.put("eventBehaviourUrl", eventManagerAjaxBehaviour.getCallbackUrl(true).toString());
        originalTemplateMap.put("getListBehaviourUrl", eventListAjaxBehaviour.getCallbackUrl(true).toString());
        //originalTemplateMap.put("getListBehaviourUrl", testIRequestTarget.);


        add(TextTemplateHeaderContributor.forJavaScript(FullCalendarComponent.class,
                CUSTOM_JS_TEMPLATE_NAME, Util.getTemplateKeys(originalTemplateMap)));

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
