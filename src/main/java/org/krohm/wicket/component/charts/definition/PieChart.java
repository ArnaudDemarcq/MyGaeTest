/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.krohm.wicket.component.charts.definition;

import java.util.Map;

/**
 *
 * @author arnaud
 */
public interface PieChart {

    public Map<String, Number> getData();

    public String getTitle();

}