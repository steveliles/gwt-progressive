package com.sjl.gwt.progressive.client.example10;

import com.google.gwt.core.client.*;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.*;
import com.google.gwt.user.client.*;
import com.sjl.gwt.progressive.client.*;
import com.sjl.gwt.progressive.client.widgets.*;

@RootBinding(tag="div", cssClass="test10root")
public class Test10Widget1 extends BoundRootPanel
{
    interface MyActivator extends ElementActivator<Test10Widget1>{}
    MyActivator activator = GWT.create(MyActivator.class);
    
    // deliberately using a tag that does not match the html
    // as this test is about what happens when the html does not match
    // our expectations!
    @RuntimeUiWidget(tag="span") Test10Widget2 middle;
    
    public Test10Widget1(Element anElement)
    {
        setElement(activator.activate(this, anElement));
        
        getElement().getStyle().setBorderColor("blue");
        getElement().getStyle().setBorderStyle(BorderStyle.SOLID);
        getElement().getStyle().setBorderWidth(1, Unit.PX);
        
        if (middle != null)
        {
            Window.alert("weirdly, middle is bound. Shouldn't be!");
        }
    }
}
