package com.sjl.gwt.progressive.client.example3;

import com.google.gwt.core.client.*;
import com.google.gwt.dom.client.*;
import com.google.gwt.user.client.ui.*;
import com.sjl.gwt.progressive.client.*;

public class Test3OuterWidget extends Composite
{
    interface MyActivator extends WidgetActivator<Test3OuterWidget>{};
    static MyActivator activator = GWT.create(MyActivator.class);
    
    @RuntimeUiWidget(cssClass="inner") Test3InnerWidget inner;
    
    public Test3OuterWidget(Element anElement)
    {
        initWidget(activator.activate(this, anElement));
        
        inner.poke();
    }
}
