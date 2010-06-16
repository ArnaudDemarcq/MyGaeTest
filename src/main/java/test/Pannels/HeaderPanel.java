package test.Pannels;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author arnaud
 */
public class HeaderPanel extends Panel{

    public HeaderPanel(String id) {
        super(id);

        add(new Label ("LoginName", "LoginName"));
        

    }





    

}
