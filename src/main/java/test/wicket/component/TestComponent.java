/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.wicket.component;

import org.apache.wicket.markup.html.JavascriptPackageResource;
import org.apache.wicket.markup.html.basic.Label;
import test.wicket.component.raphael.RaphaelComponent;

/**
 *
 * @author arnaud
 */
//public class TestComponent extends MarkupContainer {
public class TestComponent extends RaphaelComponent {

    public TestComponent(String id) {
        super(id);
        add(JavascriptPackageResource.getHeaderContribution(TestComponent.class, "TestComponent.js"));
        add(new Label("test", "TEST !!!"));
    }
}
