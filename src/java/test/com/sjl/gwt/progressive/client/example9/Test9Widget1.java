package com.sjl.gwt.progressive.client.example9;

import com.google.gwt.core.client.*;
import com.google.gwt.dom.client.*;
import com.google.gwt.dom.client.Style.BorderStyle;
import com.google.gwt.dom.client.Style.Unit;
import com.sjl.gwt.progressive.client.*;
import com.sjl.gwt.progressive.client.widgets.*;

@RootBinding(tag="div")
public class Test9Widget1 extends BoundRootPanel
{
    interface MyActivator extends ElementActivator<Test9Widget1>{}
    static MyActivator activator = GWT.create(MyActivator.class); 

    public Test9Widget1(Element anElement)
    {        
        setElement(activator.activate(this, anElement));
        
        getElement().getStyle().setBorderColor("blue");
        getElement().getStyle().setBorderStyle(BorderStyle.SOLID);
        getElement().getStyle().setBorderWidth(2, Unit.PX);
    }    
        
}
