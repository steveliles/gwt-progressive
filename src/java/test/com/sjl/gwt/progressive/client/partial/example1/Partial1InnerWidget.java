package com.sjl.gwt.progressive.client.partial.example1;

import com.google.gwt.dom.client.*;
import com.google.gwt.dom.client.Style.BorderStyle;
import com.google.gwt.dom.client.Style.Unit;
import com.sjl.gwt.progressive.client.widgets.*;

public class Partial1InnerWidget extends BoundWidget
{        
    public Partial1InnerWidget(Element anElement)
    {
        setElement(anElement);
        
        getElement().getStyle().setBorderColor("blue");
        getElement().getStyle().setBorderStyle(BorderStyle.SOLID);
        getElement().getStyle().setBorderWidth(2, Unit.PX);        
    }
}
