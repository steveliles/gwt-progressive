package com.sjl.gwt.progressive.client.partial.example3;

import com.google.gwt.core.client.*;
import com.google.gwt.dom.client.*;
import com.google.gwt.dom.client.Style.BorderStyle;
import com.google.gwt.dom.client.Style.Unit;
import com.sjl.gwt.progressive.client.*;
import com.sjl.gwt.progressive.client.widgets.*;

/**
 * Example rebinding a non-root widget.
 * 
 * @author steve
 */
@RootBinding(tag="div", cssClass="partial3")
public class Partial3RootPanel extends BoundRootPanel
{
    interface MyActivator extends ElementActivator<Partial3RootPanel>{}
    MyActivator activator = GWT.create(MyActivator.class);
    
    @RuntimeUiWidget(tag="div", cssClass="partial3-mid") Partial3MiddleWidget inner;
    
    public Partial3RootPanel(Element anElement)
    {
        setElement(activator.activate(this, anElement));
        
        getElement().getStyle().setBorderColor("red");
        getElement().getStyle().setBorderStyle(BorderStyle.SOLID);
        getElement().getStyle().setBorderWidth(2, Unit.PX);                
    }
}
