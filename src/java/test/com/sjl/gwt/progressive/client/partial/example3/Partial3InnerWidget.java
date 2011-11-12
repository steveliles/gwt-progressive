package com.sjl.gwt.progressive.client.partial.example3;

import com.google.gwt.core.client.*;
import com.google.gwt.dom.client.*;
import com.google.gwt.dom.client.Style.BorderStyle;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.*;
import com.sjl.gwt.progressive.client.*;
import com.sjl.gwt.progressive.client.widgets.*;

public class Partial3InnerWidget extends BoundWidget
{
    interface MyActivator extends ElementActivator<Partial3InnerWidget>{}
    MyActivator activator = GWT.create(MyActivator.class);
    
    public Partial3InnerWidget(Element anElement)
    {
        setElement(activator.activate(this, anElement));
        
        getElement().getStyle().setBorderColor("yellow");
        getElement().getStyle().setBorderStyle(BorderStyle.SOLID);
        getElement().getStyle().setBorderWidth(2, Unit.PX); 
        
        addDomHandler(new ClickHandler()
        {
            public void onClick(ClickEvent aEvent)
            {
                activator.update(Partial3InnerWidget.this, "/partial/partial3-update.html");
            }            
        }, ClickEvent.getType());
    }

}
