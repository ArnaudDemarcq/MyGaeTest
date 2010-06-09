package org.krohm.wicket.component.charts.jfreechart.util;

import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.IAjaxCallDecorator;
import org.apache.wicket.ajax.calldecorator.CancelEventIfNoAjaxDecorator;
import org.apache.wicket.ajax.markup.html.IAjaxLink;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.model.IModel;

public class MapArea extends WebMarkupContainer {

    private static final long serialVersionUID = -135521429660733572L;
    private String shape;
    private String coords;
    private String tooltipText;

    public MapArea(String id, IModel model, String shape, String coords, String tooltipText, final IAjaxLink linkCallback) {
        super(id, model);
        this.shape = shape;
        this.coords = coords;
        this.tooltipText = tooltipText;
        if (linkCallback != null) {
            add(new AjaxEventBehavior("onclick") {

                private static final long serialVersionUID = 2615093257359874075L;

                @Override
                protected void onEvent(AjaxRequestTarget target) {
                    linkCallback.onClick(target);
                }

                protected IAjaxCallDecorator getAjaxCallDecorator() {
                    return new CancelEventIfNoAjaxDecorator();
                }
            });
        }
        setOutputMarkupId(true);
    }

    /**
     * Construct the map area
     *
     * @param id Component identifier
     * @param shape The specific area shape
     * @param coords The coordinates of the area as a comma separated list
     * @param tooltipText The tooltip text, or null to not include it
     * @param linkCallback The link callback function called when the area is click, or null to have no link functionality
     */
    public MapArea(String id, String shape, String coords, String tooltipText, final IAjaxLink linkCallback) {
        this(id, null, shape, coords, tooltipText, linkCallback);
    }

    @Override
    protected void onComponentTag(final ComponentTag tag) {
        super.onComponentTag(tag);
        assert tag.getName().equals("area");
        tag.put("shape", shape);
        tag.put("coords", coords);
        tag.put("href", "#");
        if (tooltipText != null && !tooltipText.isEmpty()) {
            tag.put("title", tooltipText);
        }
    }
}


