/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.wicket.component;

import org.apache.wicket.markup.html.JavascriptPackageResource;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;

/**
 *
 * @author arnaud
 */
//public class TestComponent extends MarkupContainer {

public class TestComponent extends Panel {


    public TestComponent(String id) {
        super(id);
        add(new Label("test", "TEST !!!"));
        add(JavascriptPackageResource.getHeaderContribution(TestComponent.class, "raphael.js"));
        //
    }

    /*@Override
    protected void onRender(MarkupStream arg0) {
    arg0.next();
    }*/
}
