package com.sjl.gwt.progressive.client.example4;

import java.util.*;

import com.google.gwt.core.client.*;
import com.google.gwt.dom.client.*;
import com.google.gwt.dom.client.Style.BorderStyle;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.*;
import com.sjl.gwt.progressive.client.*;

public class Test4OuterWidget extends Composite
{
    interface MyActivator extends WidgetActivator<Test4OuterWidget>{};
    static MyActivator activator = GWT.create(MyActivator.class);
    
    @RuntimeUiWidget(cssClass="inner") List<Test4InnerWidget> inners;
    
    public Test4OuterWidget(Element anElement)
    {
        initWidget(activator.activate(this, anElement));
        
        getElement().getStyle().setBorderColor("red");
        getElement().getStyle().setBorderStyle(BorderStyle.DASHED);
        getElement().getStyle().setBorderWidth(2, Unit.PX);
        
        for (Test4InnerWidget _w : inners)
        {
            _w.poke();
        }
    }
}
