package com.sjl.gwt.progressive.client.example10;

import com.google.gwt.core.client.*;
import com.google.gwt.dom.client.*;
import com.sjl.gwt.progressive.client.*;
import com.sjl.gwt.progressive.client.widgets.*;

public class Test10Widget2 extends AbstractBoundPanel
{
    interface MyActivator extends ElementActivator<Test10Widget2>{}
    MyActivator activator = GWT.create(MyActivator.class);
    
    @RuntimeUiWidget(cssClass="inner") Test10Widget3 inner;
    
    public Test10Widget2(Element anElement)
    {
        setElement(activator.activate(this, anElement));
    }
    
}
