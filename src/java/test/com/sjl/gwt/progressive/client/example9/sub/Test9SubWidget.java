package com.sjl.gwt.progressive.client.example9.sub;

import com.google.gwt.core.client.*;
import com.google.gwt.dom.client.*;
import com.google.gwt.dom.client.Style.BorderStyle;
import com.google.gwt.dom.client.Style.Unit;
import com.sjl.gwt.progressive.client.*;
import com.sjl.gwt.progressive.client.widgets.*;

public class Test9SubWidget extends BoundComposite
{
    interface MyActivator extends WidgetActivator<Test9SubWidget>{}
    MyActivator activator = GWT.create(MyActivator.class);
    
    public Test9SubWidget(Element anElement)
    {
        initWidget(activator.activate(this, anElement));
        
        getElement().getStyle().setBorderColor("yellow");
        getElement().getStyle().setBorderStyle(BorderStyle.SOLID);
        getElement().getStyle().setBorderWidth(2, Unit.PX);
    }
}
