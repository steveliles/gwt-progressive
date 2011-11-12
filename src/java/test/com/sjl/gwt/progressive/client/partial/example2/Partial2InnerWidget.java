package com.sjl.gwt.progressive.client.partial.example2;

import com.google.gwt.dom.client.*;
import com.google.gwt.dom.client.Style.*;
import com.sjl.gwt.progressive.client.widgets.*;

public class Partial2InnerWidget extends BoundWidget
{
    public Partial2InnerWidget(Element anElement)
    {
        setElement(anElement);
        
        getElement().getStyle().setBorderColor("yellow");
        getElement().getStyle().setBorderStyle(BorderStyle.SOLID);
        getElement().getStyle().setBorderWidth(2, Unit.PX); 
    }
}
