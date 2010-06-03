/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.wicket.component.raphael;

import org.apache.wicket.markup.html.JavascriptPackageResource;
import org.apache.wicket.markup.html.panel.Panel;

/**
 *
 * @author arnaud
 */
//public class TestComponent extends MarkupContainer {

public class RaphaelComponent extends Panel {

    public RaphaelComponent(String id) {
        super(id);
        add(JavascriptPackageResource.getHeaderContribution(RaphaelComponent.class, "raphael.js"));
    }
}
