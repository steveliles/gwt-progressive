package com.sjl.gwt.progressive.client.partial.example2;

import com.google.gwt.core.client.*;
import com.google.gwt.dom.client.*;
import com.google.gwt.dom.client.Style.BorderStyle;
import com.google.gwt.dom.client.Style.Unit;
import com.sjl.gwt.progressive.client.*;
import com.sjl.gwt.progressive.client.widgets.*;

public class Partial2MiddleWidget extends BoundWidget
{
    interface MyActivator extends ElementActivator<Partial2MiddleWidget>{}
    MyActivator activator = GWT.create(MyActivator.class);
    
    @RuntimeUiWidget(tag="h5") Partial2InnerWidget inner;
    
    public Partial2MiddleWidget(Element anElement)
    {
        setElement(activator.activate(this, anElement));
        
        getElement().getStyle().setBorderColor("blue");
        getElement().getStyle().setBorderStyle(BorderStyle.SOLID);
        getElement().getStyle().setBorderWidth(2, Unit.PX);        
    }
}
