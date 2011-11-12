package com.sjl.gwt.progressive.client.example8;

import com.google.gwt.core.client.*;
import com.google.gwt.dom.client.*;
import com.google.gwt.user.client.ui.*;
import com.sjl.gwt.progressive.client.*;
import com.sjl.gwt.progressive.client.example8.sub.*;
import com.sjl.gwt.progressive.client.widgets.*;

@RootBinding(cssClass="root8")
public class Test8BoundRootPanel extends BoundRootPanel
{
    interface MyActivator extends ElementActivator<Test8BoundRootPanel> {}
    private static MyActivator activator = GWT.create(MyActivator.class);
    
    @RuntimeUiWidget(id="composite") Test8BoundComposite composite;
    @RuntimeUiWidget(cssClass="widget") Test8BoundWidget widget;
    
    public Test8BoundRootPanel(Element anElement)
    {
        setElement(activator.activate(this, anElement));
        
        // dynamically add a new child - the count should include the two bound widgets
        // so this first label should show 2 children (can't include itself til after its added!)
        add(new Label("I have " + getChildren().size() + " children!"));
        
        // dynamically add a new child - the count should include the two bound widgets
        // so this second label should show 3 children (can't include itself til after its added!)
        add(new Label("I have " + getChildren().size() + " children!"));        
    }       
}
