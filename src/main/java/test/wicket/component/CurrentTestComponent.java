/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.wicket.component;

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

public class CurrentTestComponent extends LabeledWebMarkupContainer {

    private static final Logger logger = LoggerFactory.getLogger(CurrentTestComponent.class);
    // Static JavaScripts
    private static final String FC_SCRIPT_NAME = "fullcalendar.min.js";
    private static final String JQUERY_SCRIPT_NAME = "jquery.js";
    private static final String JQUERY_UI_SCRIPT_NAME = "jquery-ui-custom.js";
    // Static CSSs
    private static final String FX_CSS_NAME = "fullcalendar.css";
    // Dynamic JavaScripts
    private static final String TEST_JS_NAME = "CalendarTest.js";

    public CurrentTestComponent(MarkupContainer parent, String id) {
        super(id);
        parent.add(this);
        initJs();
    }

    public CurrentTestComponent(MarkupContainer parent, String id, IModel<?> model) {
        super(id, model);
        parent.add(this);
        initJs();
    }

    private void initJs() {
        // Static JavaScripts
        add(JavascriptPackageResource.getHeaderContribution(CurrentTestComponent.class, JQUERY_SCRIPT_NAME));
        add(JavascriptPackageResource.getHeaderContribution(CurrentTestComponent.class, JQUERY_UI_SCRIPT_NAME));
        add(JavascriptPackageResource.getHeaderContribution(CurrentTestComponent.class, FC_SCRIPT_NAME));
        // Static CSSs
        add(CSSPackageResource.getHeaderContribution(CurrentTestComponent.class, FX_CSS_NAME));
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
        add(TextTemplateHeaderContributor.forJavaScript(CurrentTestComponent.class,
                TEST_JS_NAME, getTemplateKeys(this, testAjaxBehaviour1)));

        logger.error(testAjaxBehaviour1.getCallbackUrl().toString());


    }

    // Values for Dynamic JavaScripts
    private IModel<Map<String, Object>> getTemplateKeys(
            final CurrentTestComponent currentCalendar, final AjaxEventBehavior currentBehaviour) {


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
