package com.sjl.gwt.progressive.client.example5;

import com.google.gwt.core.client.*;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.*;
import com.google.gwt.user.client.ui.*;
import com.sjl.gwt.progressive.client.*;

public class Test5Widget extends Composite
{
    interface MyActivator extends WidgetActivator<Test5Widget>{};
    static MyActivator activator = GWT.create(MyActivator.class);
    
    @RuntimeUiWidget() Label brokenLabel;
    
    public Test5Widget(Element anElement) 
    {
        initWidget(activator.activate(this, anElement));
        
        if (brokenLabel != null)
        {
            Window.alert("Amazingly our brokenLabel is not broken ... something is amiss.");
        }
    }
}
