package com.sjl.gwt.progressive.client.example7;

import com.google.gwt.core.client.*;
import com.google.gwt.dom.client.*;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.ui.*;
import com.sjl.gwt.progressive.client.*;

public class Test7InnerWidget extends Composite
{    
    interface MyActivator extends WidgetActivator<Test7InnerWidget> {}
    private static MyActivator activator = GWT.create(MyActivator.class);
    
    @RuntimeUiWidget(tag="img") Image image;
    @RuntimeUiWidget(tag="span") AnchorElement span;
    
    public Test7InnerWidget(Element anElement)
    {
        initWidget(activator.activate(this, anElement));        
    }

    public HasClickHandlers getImageClickHandler()
    {
        return image;
    }

    public HasClickHandlers getSpanClickHandler()
    {
        return span;
    }
    
}
