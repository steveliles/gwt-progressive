package com.sjl.gwt.progressive.client.example7;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.event.shared.*;
import com.google.gwt.user.client.*;
import com.google.gwt.user.client.ui.*;
import com.sjl.gwt.progressive.client.widgets.*;

public class AnchorElement extends BoundWidget implements HasText, HasClickHandlers
{    
    public AnchorElement(Element aElement)
    {
        setElement(aElement);
        
        sinkEvents(Event.ONMOUSEDOWN);        
    }

    public HandlerRegistration addClickHandler(ClickHandler aHandler)
    {
        return addDomHandler(aHandler, ClickEvent.getType());
    }
    
    public String getText()
    {
        return getElement().getInnerText();
    }
    
    public void setText(String aText)
    {
        getElement().setInnerText(aText);
    }    
}