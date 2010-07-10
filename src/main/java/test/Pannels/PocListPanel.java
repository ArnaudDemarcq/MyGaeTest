/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.Pannels;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import test.Index;
import test.pages.CalendarPage;
import test.pages.DaoTestPage;
import test.pages.JfcTestPage;
import test.pages.RaphaelPage;
import test.pages.WicketTestPage;
import test.pages.DroolsTestPage;

/**
 *
 * @author arnaud
 */
public class PocListPanel extends Panel {

    private static final Logger logger = LoggerFactory.getLogger(Index.class);

    public PocListPanel(String id) {
        super(id);
        /*
        add(new BookmarkablePageLink("HomeLink", Index.class));
        add(new BookmarkablePageLink("JfcLink", JfcTestPage.class));
        add(new BookmarkablePageLink("DaoLink", DaoTestPage.class));
        /**/

        add(new ListView<ClassLinkLabel>("pocList", getClassLinkLabel()) {

            @Override
            protected void populateItem(ListItem<ClassLinkLabel> listItem) {
                ClassLinkLabel currentPageClass = listItem.getModelObject();
                BookmarkablePageLink link = new BookmarkablePageLink("link", currentPageClass.getLinkClass());
                link.add(new Label("label", currentPageClass.getLinkLabel()));
                listItem.add(link);
            }
        });/**/

    }

    private List<ClassLinkLabel> getClassLinkLabel() {
        List<ClassLinkLabel> returnList = new ArrayList<ClassLinkLabel>();
        returnList.add(new ClassLinkLabel(Index.class, "HomeLink"));
        returnList.add(new ClassLinkLabel(JfcTestPage.class, "JfcLink"));
        returnList.add(new ClassLinkLabel(DaoTestPage.class, "DaoLink"));
        returnList.add(new ClassLinkLabel(WicketTestPage.class, "WicketLink"));
        returnList.add(new ClassLinkLabel(CalendarPage.class, "CalendarLink"));
        returnList.add(new ClassLinkLabel(RaphaelPage.class, "RaphaelLink"));
                returnList.add(new ClassLinkLabel(DroolsTestPage.class, "DroolsLink"));
        return returnList;
    }

    private class ClassLinkLabel implements Serializable {

        private Class linkClass;
        private String linkLabel;

        public ClassLinkLabel(Class linkClass, String linkLabel) {
            this.linkClass = linkClass;
            this.linkLabel = linkLabel;
        }

        public Class getLinkClass() {
            return linkClass;
        }

        public void setLinkClass(Class linkClass) {
            this.linkClass = linkClass;
        }

        public String getLinkLabel() {
            return linkLabel;
        }

        public void setLinkLabel(String linkLabel) {
            this.linkLabel = linkLabel;
        }
    }
}
