package com.sjl.gwt.progressive.client.example9;

import java.util.*;

import com.google.gwt.core.client.*;
import com.google.gwt.dom.client.*;
import com.google.gwt.dom.client.Style.BorderStyle;
import com.google.gwt.dom.client.Style.Unit;
import com.sjl.gwt.progressive.client.*;
import com.sjl.gwt.progressive.client.example9.sub.*;
import com.sjl.gwt.progressive.client.widgets.*;

@RootBinding(tag="div", cssClass="root9")
public class Test9Widget3 extends BoundRootPanel
{
    interface MyActivator extends ElementActivator<Test9Widget3>{}
    static MyActivator activator = GWT.create(MyActivator.class); 

    @RuntimeUiWidget(tag="h5") List<Test9SubWidget> sub;
    
    public Test9Widget3(Element anElement)
    {
        setElement(activator.activate(this, anElement));
        
        getElement().getStyle().setBorderColor("green");
        getElement().getStyle().setBorderStyle(BorderStyle.SOLID);
        getElement().getStyle().setBorderWidth(2, Unit.PX);        
    }
}