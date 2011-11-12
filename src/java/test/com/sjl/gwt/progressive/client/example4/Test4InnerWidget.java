package com.sjl.gwt.progressive.client.example4;

import java.util.*;

import com.google.gwt.core.client.*;
import com.google.gwt.dom.client.Style.BorderStyle;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.*;
import com.google.gwt.user.client.ui.*;
import com.sjl.gwt.progressive.client.*;

public class Test4InnerWidget extends Widget implements Attachable
{
    interface MyActivator extends ElementActivator<Test4InnerWidget>{};
    static MyActivator activator = GWT.create(MyActivator.class);
    
    @RuntimeUiWidget(tag="img") List<Image> images;
    @RuntimeUiWidget(tag="span") InlineLabel label;
    
    public Test4InnerWidget(Element anElement)
    {
        setElement(activator.activate(this, anElement));
        
        label.setText(label.getText().replace("?", "" + images.size()));
        
        getElement().getStyle().setBorderColor("yellow");
        getElement().getStyle().setBorderStyle(BorderStyle.SOLID);
        getElement().getStyle().setBorderWidth(2, Unit.PX);
        
        sinkEvents(Event.MOUSEEVENTS);
        
        addDomHandler(new ClickHandler()
        {
            public void onClick(ClickEvent aEvent)
            {
                Window.alert("Inner-widget clicked!");
            }           
        }, 
        ClickEvent.getType());                
    }
    
    public void poke()
    {
        for (Image _i : images)
        {
            _i.getElement().getStyle().setBorderColor("green");
            _i.getElement().getStyle().setBorderStyle(BorderStyle.DOTTED);
            _i.getElement().getStyle().setBorderWidth(2, Unit.PX);
        }
    }

    @Override
    public void onAttach()
    {
        // TODO Auto-generated method stub
        super.onAttach();
    }    
}
