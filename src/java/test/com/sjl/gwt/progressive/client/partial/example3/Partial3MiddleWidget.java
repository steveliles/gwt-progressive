package com.sjl.gwt.progressive.client.partial.example3;

import com.google.gwt.core.client.*;
import com.google.gwt.dom.client.Style.BorderStyle;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.*;
import com.sjl.gwt.progressive.client.*;
import com.sjl.gwt.progressive.client.widgets.*;

public class Partial3MiddleWidget extends AbstractBoundPanel
{
    interface MyActivator extends ElementActivator<Partial3MiddleWidget>{}
    MyActivator activator = GWT.create(MyActivator.class);
    
    @RuntimeUiWidget(tag="h5") Partial3InnerWidget inner;
    
    public Partial3MiddleWidget(Element anElement)
    {
        setElement(activator.activate(this, anElement));
        
        getElement().getStyle().setBorderColor("blue");
        getElement().getStyle().setBorderStyle(BorderStyle.SOLID);
        getElement().getStyle().setBorderWidth(2, Unit.PX);
        
        sinkEvents(Event.ONCLICK);
    }    
}
