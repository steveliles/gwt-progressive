package com.sjl.gwt.progressive.client;

import com.google.gwt.core.client.*;
import com.google.gwt.dom.client.*;
import com.google.gwt.user.client.ui.*;
import com.sjl.gwt.progressive.client.example6.*;
import com.sjl.gwt.progressive.client.util.*;

public class Example6 implements EntryPoint
{
    public void onModuleLoad()
    {                
        RootPanel.get().add(new LateUiBindingWidget("Hi!", "This is different...", "template provided at compile time, but bound at runtime (late)", "Might be fun"));
        RootPanel.get().add(new LateUiBindingWidget("Hello!", "This is fun...", "template provided early, but bound at runtime", "Might be nice"));
        RootPanel.get().add(new LateUiBindingWidget("Yoohoo!", "This is interesting...", "template provided at compile time, but bound late", "Might be interesting"));
        RootPanel.get().add(new LateUiBindingWidget("Cooee!", "This is nice...", "template provided early, but bound late", "Might be a giggle"));
        
        // we can very-late-bind our widget too, that can use a different template
        // provided at runtime!
        for (Element _e : Elements.getByCssClass("test-widget")) 
        {
            LateUiBindingWidget _w = new LateUiBindingWidget(_e);
            
            _w.setH3Text("Yo!");
            _w.setH4Text("OK, this one uses very-late-binding, instead of late-binding");
            _w.setParagraphText("Don't confuse me with the rest of these late widgets!");
            _w.setLinkText("..mmm");
        }
    }
}
