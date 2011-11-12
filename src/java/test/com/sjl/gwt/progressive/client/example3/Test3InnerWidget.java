package com.sjl.gwt.progressive.client.example3;

import com.google.gwt.core.client.*;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.*;
import com.google.gwt.user.client.ui.*;
import com.sjl.gwt.progressive.client.*;

/**
 * Test3 looks at binding a widget that extends Composite, and thus
 * requires an inner Widget to bind with.
 * 
 * To achieve this we're using the second "Activator" interface: WidgetActivator,
 * instead of ElementActivator which we used in tests 1 and 2.
 * 
 * To make the example a little more interesting, we'll add a ClickHandler to
 * the widget itself, rather than to the image, to show that we are engaged in
 * the GWT eventing system. NOTE: you only need to "engage" manually if you do not
 * implement Attachable or extend one of the Bound* classes that does implement
 * Attachable. 
 * 
 * @author steve
 */
public class Test3InnerWidget extends Composite
{    
    interface MyActivator extends WidgetActivator<Test3InnerWidget> {}
    private static MyActivator activator = GWT.create(MyActivator.class);
    
    @RuntimeUiWidget(tag="img") Image image;
    
    public Test3InnerWidget(Element anElement)
    {
        initWidget(activator.activate(this, anElement));                
        
        engageWithEventingSystem();
        
        sinkEvents(Event.MOUSEEVENTS);
        
        addDomHandler(new ClickHandler()
        {
            public void onClick(ClickEvent aEvent)
            {
                Window.alert("Inner-widget clicked!");
            }           
        }, 
        ClickEvent.getType());
    }
    
    public void poke()
    {
        Window.alert("Test3OuterWidget poked Test3InnerWidget");
    }

    // we don't get to play with the GWT eventing system unless we invoke
    // onAttach(), which wires us up for eventing. We should also ensure
    // to have ourselves removed when the app is closed.
    private void engageWithEventingSystem()
    {
        onAttach();
        RootPanel.detachOnWindowClose(this);
    }

}
