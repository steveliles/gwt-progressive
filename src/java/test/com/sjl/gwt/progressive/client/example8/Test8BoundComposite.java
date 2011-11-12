package com.sjl.gwt.progressive.client.example8;

import com.google.gwt.core.client.*;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.BorderStyle;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.*;
import com.google.gwt.user.client.ui.*;
import com.sjl.gwt.progressive.client.*;
import com.sjl.gwt.progressive.client.example8.sub.*;
import com.sjl.gwt.progressive.client.widgets.*;

public class Test8BoundComposite extends BoundComposite
{
    interface MyActivator extends WidgetActivator<Test8BoundComposite> {}
    private static MyActivator activator = GWT.create(MyActivator.class);
    
    @RuntimeUiWidget(cssClass="widget") Test8BoundWidget widget;
    @RuntimeUiWidget(cssClass="label") Label label;
    
    public Test8BoundComposite(Element anElement)
    {
        initWidget(activator.activate(this, anElement));
        
        getElement().getStyle().setBorderColor("red");
        getElement().getStyle().setBorderStyle(BorderStyle.SOLID);
        getElement().getStyle().setBorderWidth(2, Unit.PX);
        
        widget.getElement().getStyle().setBackgroundColor("green");
        label.setText("I live in a " + getClass().getName());
        
        widget.addClickHandler(new ClickHandler()
        {
            public void onClick(ClickEvent aEvent)
            {
                Window.alert("clicked");
            }    
        });
    }
}
