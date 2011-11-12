package com.sjl.gwt.progressive.client.example7;

import com.google.gwt.core.client.*;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.dom.client.*;
import com.google.gwt.user.client.ui.*;
import com.sjl.gwt.progressive.client.*;

public class Test7OuterWidget extends Composite
{
    interface MyActivator extends WidgetActivator<Test7OuterWidget>{};
    static MyActivator activator = GWT.create(MyActivator.class);
    
    @RuntimeUiWidget(cssClass="inner") Test7InnerWidget inner;
    
    public Test7OuterWidget(Element anElement)
    {
        initWidget(activator.activate(this, anElement));
    }    
    
    public HasClickHandlers getImageClickHandler()
    {
        return inner.getImageClickHandler();
    }
    
    public HasClickHandlers getSpanClickHandler()
    {
        return inner.getSpanClickHandler();
    }
}
