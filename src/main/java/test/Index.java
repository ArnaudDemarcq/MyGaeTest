package test;

import beans.TestEntity;

import org.apache.wicket.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import test.dao.GenericDao;
import test.pages.PocMainPage;

/**
 * Homepage
 */
public class Index extends PocMainPage {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(Index.class);
    @SpringBean(name = "testEntityDao2")
    GenericDao<TestEntity> testEntityDao2;

    public Index(final PageParameters parameters) {
        super(parameters);

    


    }

 

}
