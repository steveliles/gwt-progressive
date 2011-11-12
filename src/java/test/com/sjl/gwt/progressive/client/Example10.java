package com.sjl.gwt.progressive.client;

import com.google.gwt.core.client.*;

public class Example10 implements EntryPoint
{

    @Override
    public void onModuleLoad()
    {        
        // test "automatic" activation...
        PageActivator _activator = GWT.create(PageActivator.class);
        _activator.activate();
    }

}
