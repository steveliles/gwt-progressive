package com.sjl.gwt.progressive.client.partial.example1;

import com.google.gwt.core.client.*;
import com.google.gwt.dom.client.Style.BorderStyle;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.*;
import com.sjl.gwt.progressive.client.*;
import com.sjl.gwt.progressive.client.widgets.*;

@RootBinding(tag="div", cssClass="partial1")
public class Partial1RootPanel extends BoundRootPanel
{
    interface MyActivator extends ElementActivator<Partial1RootPanel>{}
    MyActivator activator = GWT.create(MyActivator.class);
    
    @RuntimeUiWidget(tag="div", cssClass="partial1-inner") Partial1InnerWidget inner;
    
    public Partial1RootPanel(Element anElement)
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
                activator.update(Partial1RootPanel.this, "/partial/partial1-update.html", new PageUpdateCallback<Partial1RootPanel>()
                {
                    public void onSuccess(Partial1RootPanel aNewInstance)
                    {
                        Window.alert("We can trigger stuff on update complete too!");
                    }

                    public void onError(Throwable anExc)
                    {
                        Window.alert("Bang .. check the log");
                        GWT.log("oops, something bad happened", anExc);
                    }                    
                });
            }            
        }, ClickEvent.getType());
    }
        
}
