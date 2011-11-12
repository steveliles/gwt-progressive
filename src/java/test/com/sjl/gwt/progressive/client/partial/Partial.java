package com.sjl.gwt.progressive.client.partial;

import com.google.gwt.core.client.*;
import com.sjl.gwt.progressive.client.*;

public class Partial implements EntryPoint
{
    @Override
    public void onModuleLoad()
    {        
        // test "automatic" activation...
        PageActivator _activator = GWT.create(PageActivator.class);
        _activator.activate();
    }
}
