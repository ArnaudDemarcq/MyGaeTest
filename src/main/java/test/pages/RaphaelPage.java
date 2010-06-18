package test.pages;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.wicket.PageParameters;
import test.wicket.component.TestComponent;

public class RaphaelPage extends PocMainPage {

    private static final Logger logger = LoggerFactory.getLogger(RaphaelPage.class);

    public RaphaelPage(PageParameters parameters) {
        super(parameters);


        add(new TestComponent("raph"));


    }
}
