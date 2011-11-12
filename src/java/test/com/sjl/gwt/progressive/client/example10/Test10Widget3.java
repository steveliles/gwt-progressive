package com.sjl.gwt.progressive.client.example10;

import com.google.gwt.core.client.*;
import com.google.gwt.dom.client.*;
import com.sjl.gwt.progressive.client.*;
import com.sjl.gwt.progressive.client.widgets.*;

public class Test10Widget3 extends BoundComposite
{
    interface MyActivator extends WidgetActivator<Test10Widget3>{}
    MyActivator activator = GWT.create(MyActivator.class);
    
    public Test10Widget3(Element anElement)
    {
        initWidget(activator.activate(this, anElement));
    }
}
