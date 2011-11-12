package com.sjl.gwt.progressive.client;

import com.google.gwt.core.client.*;
import com.google.gwt.dom.client.*;
import com.sjl.gwt.progressive.client.example3.*;
import com.sjl.gwt.progressive.client.util.*;

public class Example3 implements EntryPoint
{
    public void onModuleLoad()
    {
        // we have to kick the process off at the moment by explicitly seeking our top-level
        // widgets and creating them directly.                
        for (Element _e : Elements.getByCssClass("test-widget")) 
        {
            new Test3OuterWidget(_e);
        }
    }
}