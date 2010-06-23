/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.krohm.wicket.component.calendar.fullcalendar;

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
    // JavaScript Libs
    private static final String FC_SCRIPT_NAME = "resources/fullcalendar/fullcalendar.min.js";
    private static final String JQUERY_SCRIPT_NAME = "resources/jquery/jquery.js";
    private static final String JQUERY_UI_SCRIPT_NAME = "resources/jquery/jquery-ui-custom.js";
    // Static CSSs
    private static final String FX_CSS_NAME = "resources/fullcalendar/fullcalendar.css";
    // Custom JavaScripts
    private static final String CUSTOM_JS_NAME = "resources/custom.js";
    private static final String CUSTOM_JS_TEMPLATE_NAME = "resources/customTemplate.tpl.js";

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

    private void initJs() {
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
        AjaxEventBehavior testAjaxBehaviour1 = new AjaxEventBehavior("eventDrop") {

            @Override
            protected void onEvent(AjaxRequestTarget art) {

                logger.error("[***************************************]");
                Map map = ((WebRequestCycle) RequestCycle.get()).getRequest().getParameterMap();
                for (Object o : map.keySet()) {
                    Object[] value = (Object[]) map.get(o);
                    logger.error(o + " ====> " + value[0]);
                }
            }

        };


        add(testAjaxBehaviour1);
        add(TextTemplateHeaderContributor.forJavaScript(FullCalendarComponent.class,
                CUSTOM_JS_TEMPLATE_NAME, getTemplateKeys(this, testAjaxBehaviour1)));

    }

    // Values for Dynamic JavaScripts
    private IModel<Map<String, Object>> getTemplateKeys(
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

    private void logEvent(Object eventObject) {
        if (eventObject == null) {
            logger.error("Null Event");
        }
        logger.error(eventObject.toString());
        logger.error(eventObject.getClass().toString());
    }
}
