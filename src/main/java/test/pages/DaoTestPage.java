/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.pages;

import beans.TestEntity;
import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import test.dao.GenericDao;

/**
 *
 * @author arnaud
 */
public class DaoTestPage extends PocMainPage {

    private static final Logger logger = LoggerFactory.getLogger(DaoTestPage.class);
    @SpringBean(name = "testEntityDao2")
    GenericDao<TestEntity> testEntityDao2;

    public DaoTestPage(PageParameters parameters) {
        super(parameters);
        TestEntity tmpTestEntity = new TestEntity();
        tmpTestEntity.setIntValue(12);
        tmpTestEntity.setStringValue("This is a Entity String");

        // creates a TestEntityBean
        testEntityDao2.create(tmpTestEntity);
        logger.error(">>>>>>>>>>>>>>>>>" + tmpTestEntity.getId());/**/


        // Add the simplest type of label
        String tmpText = "Wicket Hello World :";
        add(new Label("message", tmpText + tmpTestEntity.getId()));
    }
}
