/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package test.pages;

import org.apache.wicket.PageParameters;
import test.Pannels.FullCalendarPanel;

/**
 *
 * @author arnaud
 */
public class CalendarPage  extends PocMainPage{

    public CalendarPage(PageParameters parameters) {
        super(parameters);
        add(new FullCalendarPanel("calendar"));
    }



}
