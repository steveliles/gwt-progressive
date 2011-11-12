package com.sjl.gwt.progressive.client;

import com.google.gwt.core.client.*;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.*;
import com.sjl.gwt.progressive.client.example7.*;
import com.sjl.gwt.progressive.client.util.*;


public class Example7 implements EntryPoint
{
    public void onModuleLoad()
    {                
        // we have to kick the process off at the moment by explicitly seeking our top-level
        // widgets and creating them directly.                
        for (Element _e : Elements.getByCssClass("test-widget")) 
        {
            Test7OuterWidget _test7OuterWidget = new Test7OuterWidget(_e);
            
            _test7OuterWidget.getImageClickHandler().addClickHandler(new ClickHandler()
            {
                public void onClick(ClickEvent aEvent)
                {
                    Window.alert("image clicked!");
                }           
            });
            
            _test7OuterWidget.getSpanClickHandler().addClickHandler(new ClickHandler()
            {
                public void onClick(ClickEvent aEvent)
                {
                    Window.alert("Span clicked!");
                }           
            });
        }
    }
}
