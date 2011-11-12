package com.sjl.gwt.progressive.client.example9;

import com.google.gwt.core.client.*;
import com.google.gwt.dom.client.*;
import com.google.gwt.dom.client.Style.BorderStyle;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.*;
import com.sjl.gwt.progressive.client.*;
import com.sjl.gwt.progressive.client.widgets.*;

@RootBinding(cssClass="root9")
public class Test9Widget2 extends BoundRootPanel
{
    interface MyActivator extends ElementActivator<Test9Widget2>{}
    static MyActivator activator = GWT.create(MyActivator.class); 

    // the following attributes should have their default values (set below)
    // overridden by the values of attributes in the html
    @RuntimeUiAttribute("text") String textAttr = "default";
    @RuntimeUiAttribute("int") Integer integerAttr = 22;
    @RuntimeUiAttribute("long") Long longAttr = 9L;
    @RuntimeUiAttribute("float") Float floatAttr = 2.3f;
    @RuntimeUiAttribute("double") Double doubleAttr = 2222222.222d;
    @RuntimeUiAttribute("boolean") Boolean booleanAttr = false;
    
    // the following attributes should keep their default values as set below
    // because there is no matching attribute in the html
    @RuntimeUiAttribute("missing-text") String missingTextAttr = "default";
    @RuntimeUiAttribute("missing-int") Integer missingIntegerAttr = 2;
    @RuntimeUiAttribute("missing-long") Long missingLongAttr = 13L;
    @RuntimeUiAttribute("missing-float") Float missingFloatAttr = 2.4f;
    @RuntimeUiAttribute("missing-double") Double missingDoubleAttr = 3333333.333d;
    @RuntimeUiAttribute("missing-boolean") Boolean missingBooleanAttr = true;
    
    // the following is an example of a more complex, customer attribute binding that can be
    // provided by a developer as needed
    @RuntimeUiAttribute("bespoke") Coords coordsAttr;
    @RuntimeUiAttribute("missing-bespoke") Coords missingCoordsAttr = new Coords(3,4);
    
    public Test9Widget2(Element anElement)
    {
        setElement(activator.activate(this, anElement));
        
        getElement().getStyle().setBorderColor("red");
        getElement().getStyle().setBorderStyle(BorderStyle.SOLID);
        getElement().getStyle().setBorderWidth(2, Unit.PX);
        
        add(new Label("attribute 'text' was bound with value '" + textAttr + "' (" + getClass(textAttr) + ")"));
        add(new Label("attribute 'int' was bound with value '" + integerAttr + "' (" + getClass(integerAttr) + ")"));
        add(new Label("attribute 'long' was bound with value '" + longAttr + "' (" + getClass(longAttr) + ")"));
        add(new Label("attribute 'float' was bound with value '" + floatAttr + "' (" + getClass(floatAttr) + ")"));
        add(new Label("attribute 'double' was bound with value '" + doubleAttr + "' (" + getClass(doubleAttr) + ")"));
        add(new Label("attribute 'boolean' was bound with value '" + booleanAttr + "' (" + getClass(booleanAttr) + ")"));
        add(new Label("attribute 'bespoke' was bound with value '" + coordsAttr + "' (" + getClass(coordsAttr) + ")"));
                
        add(new Label("attribute 'missing-text' was bound with value '" + missingTextAttr + "' (" + getClass(missingTextAttr) + ")"));
        add(new Label("attribute 'missing-int' was bound with value '" + missingIntegerAttr + "' (" + getClass(missingIntegerAttr) + ")"));
        add(new Label("attribute 'missing-long' was bound with value '" + missingLongAttr + "' (" + getClass(missingLongAttr) + ")"));
        add(new Label("attribute 'missing-float' was bound with value '" + missingFloatAttr + "' (" + getClass(missingFloatAttr) + ")"));
        add(new Label("attribute 'missing-double' was bound with value '" + missingDoubleAttr + "' (" + getClass(missingDoubleAttr) + ")"));
        add(new Label("attribute 'missing-boolean' was bound with value '" + missingBooleanAttr + "' (" + getClass(missingBooleanAttr) + ")"));
        add(new Label("attribute 'missing-bespoke' was bound with value '" + missingCoordsAttr + "' (" + getClass(missingCoordsAttr) + ")"));
    }
    
    @SuppressWarnings("unchecked")
    private <T> Class<T> getClass(T anObject)
    {
        return (anObject == null) ? null : (Class<T>)anObject.getClass(); 
    }
    
}
