package com.sjl.gwt.progressive.client.partial.example2;

import com.google.gwt.core.client.*;
import com.google.gwt.dom.client.Style.BorderStyle;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.*;
import com.sjl.gwt.progressive.client.*;
import com.sjl.gwt.progressive.client.widgets.*;

@RootBinding(tag="div", cssClass="partial2")
public class Partial2RootPanel extends BoundRootPanel
{
    interface MyActivator extends ElementActivator<Partial2RootPanel>{}
    MyActivator activator = GWT.create(MyActivator.class);
    
    @RuntimeUiWidget(tag="div", cssClass="partial2-mid") Partial2MiddleWidget inner;
    
    public Partial2RootPanel(Element anElement)
    {
        setElement(activator.activate(this, anElement));
        
        getElement().getStyle().setBorderColor("red");
        getElement().getStyle().setBorderStyle(BorderStyle.SOLID);
        getElement().getStyle().setBorderWidth(2, Unit.PX);
        
        sinkEvents(Event.ONCLICK);
        
        addDomHandler(new ClickHandler()
        {
            public void onClick(ClickEvent aEvent)
            {
                activator.update(Partial2RootPanel.this, "/partial/partial2-update.html");
            }            
        }, ClickEvent.getType());
    }
    
}
