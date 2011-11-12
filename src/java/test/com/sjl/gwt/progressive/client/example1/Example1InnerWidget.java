package com.sjl.gwt.progressive.client.example1;

import com.google.gwt.dom.client.Style.*;
import com.google.gwt.dom.client.*;
import com.google.gwt.user.client.ui.*;

public class Example1InnerWidget extends Widget
{
    public Example1InnerWidget(Element anElement) {
        setElement(anElement);
        onAttach();
        RootPanel.detachOnWindowClose(this);
    
        // do something visible to show that we've bound to the element
        getElement().getStyle().setColor("green");
        getElement().getStyle().setFontStyle(FontStyle.ITALIC);        
    }
}
