package test;

import org.apache.wicket.PageParameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import test.pages.PocMainPage;

/**
 * Homepage
 */
public class Index extends PocMainPage {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(Index.class);

    public Index(final PageParameters parameters) {
        super(parameters);

    }
}
