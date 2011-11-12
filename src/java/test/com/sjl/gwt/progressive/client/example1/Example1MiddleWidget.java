package com.sjl.gwt.progressive.client.example1;

import com.google.gwt.core.client.*;
import com.google.gwt.dom.client.*;
import com.google.gwt.dom.client.Style.BorderStyle;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.*;
import com.sjl.gwt.progressive.client.*;

public class Example1MiddleWidget extends Widget
{
    interface MyActivator extends ElementActivator<Example1MiddleWidget> {}
    private static MyActivator activator = GWT.create(MyActivator.class);
    
    @RuntimeUiWidget(cssClass="inner-widget") Example1InnerWidget inner;

    public Example1MiddleWidget(Element anElement) {
        setElement(activator.activate(this, anElement));
        
        onAttach();
        RootPanel.detachOnWindowClose(this);
    
        // do something visible to show that we've bound to the element
        getElement().getStyle().setBorderColor("blue");
        getElement().getStyle().setBorderStyle(BorderStyle.SOLID);
        getElement().getStyle().setBorderWidth(1, Unit.PX);
    }
}
