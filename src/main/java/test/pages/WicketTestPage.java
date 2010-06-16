package test.pages;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.CompoundPropertyModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.basic.Label;

public class WicketTestPage extends PocMainPage {

    private static final Logger logger = LoggerFactory.getLogger(WicketTestPage.class);
    private String processName;
    private String taskName;
    final private List<String> taskList = new ArrayList<String>();
    final private List<String> firstList = getBox1List();
    final private Map<String, Process> processMap = new HashMap<String, Process>();

    public WicketTestPage(PageParameters parameters) {
        super(parameters);


        final Form form = new Form("form") {

            @Override
            protected void onSubmit() {
                logger.error("Value 1 : " + processName);
                logger.error("Value 2 : " + taskName);
            }
        };

        form.setModel(new CompoundPropertyModel(this));

        final WebMarkupContainer taskdiv = new WebMarkupContainer("taskdiv");
        taskdiv.add(new DropDownChoice("taskName", taskList));
        taskdiv.setOutputMarkupId(true);

        final DropDownChoice<String> processNameDropDown = new DropDownChoice<String>("processName", firstList);

        processNameDropDown.add(new AjaxFormComponentUpdatingBehavior("onchange") {

            @Override
            protected void onUpdate(AjaxRequestTarget arg0) {
                logger.error("On change triggered !");
                taskList.clear();
                taskList.addAll(getProcessNodesNames(processName));
                arg0.addComponent(taskdiv);
            }
        });

        form.add(processNameDropDown);
        form.add(taskdiv);
        add(form);

        add(new Label("result", taskName));

    }

    private List<String> getProcessNodesNames(String key) {
        List<String> currentNodesNameList = new ArrayList<String>();

        for (int i = 0; i < 4; i++) {
            currentNodesNameList.add(key + "[" + i + "]");
        }
        return currentNodesNameList;
    }

    private List<String> getBox1List() {
        List<String> currentNodesNameList = new ArrayList<String>();
        currentNodesNameList.add("key1");
        currentNodesNameList.add("key2");
        currentNodesNameList.add("key3");
        currentNodesNameList.add("key4");
        return currentNodesNameList;

    }
}
