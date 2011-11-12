package com.sjl.gwt.progressive.client.example6;

import com.google.gwt.core.client.*;
import com.google.gwt.dom.client.Element;
import com.google.gwt.resources.client.*;
import com.google.gwt.user.client.*;
import com.google.gwt.user.client.ui.*;
import com.sjl.gwt.progressive.client.*;

/**
 * Trying out some ideas. I'm thinking of this one as "late ui-binding" as compared to 
 * UiBinder's "early" ui-binding, and examples 1-5 in this project which I think of as
 * "very late ui-binding" :). 
 * 
 * GWT's own UiBinder does the binding at gwt-compile time (early), such that the way the 
 * DOM elements are actually built at runtime is much more like standard GWT - create 
 * elements and add them to the page programmatically.
 * 
 * This one (late binding) uses a pure, unadulterated html template which is provided at 
 * compile time but bound at runtime. There are benefits and concessions here as compared 
 * to UiBinder, but right now i'm just seeing what we can do :). If it turns out to be
 * useful we can certainly modify the generator to do compile time checks to make sure
 * that the template and the code match up in their expectations.
 * 
 * Examples 1-5 (very late binding) receive the template at runtime. 
 * 
 * @author steve
 */
public class LateUiBindingWidget extends Composite
{       
    // create a client-bundle that provides our template as 
    // a text resource
    interface MyTemplate extends ClientBundle
    {
        @Source("LateUiBindingWidget.template.html")
        public TextResource template();
    }
    
    // create an instance of the client bundle so we can get our template
    static MyTemplate template = GWT.create(MyTemplate.class);
    
    // standard activator setup
    interface MyActivator extends WidgetActivator<LateUiBindingWidget>{};
    static MyActivator activator = GWT.create(MyActivator.class);    
    
    private static Element createElementWithTemplate()
    {
        Element _el = DOM.createDiv();
        _el.setInnerHTML(template.template().getText());
        return _el;
    }
    
    
    @RuntimeUiWidget(tag="h3") Header h3;
    @RuntimeUiWidget(tag="h4") Header h4;
    @RuntimeUiWidget(tag="p") Paragraph p;
    @RuntimeUiWidget(tag="a") LateAnchor a;
    
    
    public LateUiBindingWidget(Element anElement)
    {
        initWidget(activator.activate(this, anElement));
        
        getElement().getStyle().setBackgroundColor("#fee");
    }
    
    public LateUiBindingWidget(String aH3, String aH4, String aP, String aLinkText)
    {
        this(createElementWithTemplate());                                
        
        getElement().getStyle().setBackgroundColor("#eef");
        
        h3.setText(aH3);
        h4.setText(aH4);
        p.setText(aP);
        a.setText(aLinkText);
    }        
    
    public void setH3Text(String aText)
    {
        h3.setText(aText);
    }
    
    public void setH4Text(String aText)
    {
        h4.setText(aText);
    }
    
    public void setParagraphText(String aText)
    {
        p.setText(aText);
    }
    
    public void setLinkText(String aText)
    {
        a.setText(aText);
    }
}


// some fairly dumb widgets to bind onto our template ...


class TextWidget extends Widget implements HasText
{
    public TextWidget(Element anElement)
    {        
        setElement(anElement);
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

class Header extends TextWidget
{
    public Header(Element anElement)
    {
        super(anElement);
    }
}

class Paragraph extends TextWidget
{
    public Paragraph(Element anElement)
    {
        super(anElement);
    }
}

/**
 * Cannot use the GWT Anchor class - its "wrap" method wants the element to
 * be already attached to the document, but in our case it is not attached to 
 * the document until the LateUiBindingWidget is add()'ed to another widget that
 * is attached to the document, which is already too late for us.
 * 
 * @author steve
 */
class LateAnchor extends TextWidget
{
    public LateAnchor(Element anElement)
    {
        super(anElement);
    }
}
