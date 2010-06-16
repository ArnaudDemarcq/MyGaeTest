/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.pages;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import test.Pannels.HeaderPanel;
import test.Pannels.PocListPanel;

/**
 *
 * @author arnaud
 */
public class PocMainPage extends WebPage {

    public PocMainPage(PageParameters parameters) {
        super(parameters);
        add(new PocListPanel("PocList"));
        add(new HeaderPanel("HeaderPanel"));
    }
}
