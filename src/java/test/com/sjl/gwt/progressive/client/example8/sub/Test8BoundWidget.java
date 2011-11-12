package com.sjl.gwt.progressive.client.example8.sub;

import com.google.gwt.core.client.*;
import com.google.gwt.dom.client.Style.BorderStyle;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.event.shared.*;
import com.google.gwt.user.client.*;
import com.sjl.gwt.progressive.client.*;
import com.sjl.gwt.progressive.client.widgets.*;

public class Test8BoundWidget extends BoundWidget implements HasClickHandlers
{
    interface MyActivator extends ElementActivator<Test8BoundWidget>{}
    private static MyActivator activator = GWT.create(MyActivator.class);
    
    public Test8BoundWidget(Element anElement)
    {
        setElement(activator.activate(this, anElement));
        
        getElement().getStyle().setBorderColor("blue");
        getElement().getStyle().setBorderStyle(BorderStyle.SOLID);
        getElement().getStyle().setBorderWidth(2, Unit.PX);
        
        sinkEvents(Event.ONCLICK);
    }

    @Override
    public HandlerRegistration addClickHandler(ClickHandler aHandler)
    {
        return addDomHandler(aHandler, ClickEvent.getType());
    }
        
}
