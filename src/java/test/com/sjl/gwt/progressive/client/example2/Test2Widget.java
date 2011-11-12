package com.sjl.gwt.progressive.client.example2;

import com.google.gwt.core.client.*;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.*;
import com.google.gwt.user.client.ui.*;
import com.sjl.gwt.progressive.client.*;

/**
 * This test looks at binding multiple widgets for use within the enclosing
 * widget. The nested widgets can appear anywhere within the html template,
 * with any amount of html in between them (which will be ignored).
 * 
 * For simplicity we'll bind Image widgets, to save having to create a special
 * widget to bind. When binding Image widgets we take advantage of the wrap(Element)
 * method kindly supplied by google. This is a special case for Image widgets only
 * and most standard gwt widgets cannot currently be bound in this way.
 * 
 * See /war/test2.html for sample html this binds to, but remember that the nested
 * Image widgets, e.g. "small" will bind to the first <img> tag with class="small"
 * that is found by breadth-first search within the element that Test2Widget binds
 * to, no matter what your html looks like, so its pretty flexible.
 * 
 * @author steve
 */
public class Test2Widget extends Widget
{
    // should be familiar to UiBinder afficionado's
    interface MyActivator extends ElementActivator<Test2Widget> {}
    private static MyActivator activator = GWT.create(MyActivator.class);
    
    @RuntimeUiWidget(tag="img", cssClass="small") Image small;
    @RuntimeUiWidget(tag="img", cssClass="medium") Image medium;
    @RuntimeUiWidget(tag="img", cssClass="large") Image large;
    
    public Test2Widget(Element anElement) 
    {
        // this will set our element and bind all 3 of our image fields.
        setElement(activator.activate(this, anElement));
        
        small.addClickHandler(newClickHandler("small was clicked"));
        medium.addClickHandler(newClickHandler("medium was clicked"));
        large.addClickHandler(newClickHandler("large was clicked"));
    }
    
    private ClickHandler newClickHandler(final String aMessage)
    {
        return new ClickHandler() 
        {
            public void onClick(ClickEvent aEvent)
            {
                Window.alert(aMessage);
            }            
        };
    }
}
