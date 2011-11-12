package com.sjl.gwt.progressive.client.example1;

import com.google.gwt.core.client.*;
import com.google.gwt.dom.client.*;
import com.google.gwt.dom.client.Style.BorderStyle;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.*;
import com.sjl.gwt.progressive.client.*;

/**
 * Test 1 is a quick check to see that we can bind nested elements
 * onto some arbitrary html, using css classes to identify the widgets to bind. 
 * 
 * We'll try to bind onto html that looks like:
 * 
 * ...
 * <div class="outer-widget">
 *   <h3>I'm the outer widget!</h3>
 *   <div class="middle-widget">
 *     <h4>I'm a middle widget!</h4>
 *     <div class="inner-widget">
 *       <h5>I'm the inner widget!</h5>
 *     </div>
 *   </div>
 * </div> 
 * ...
 * 
 * But also, to demonstrate flexibility of the template, we'll bind to:
 * 
 * <table class="outer-widget">
 *   <tr>
 *     <th><h3>I'm the outer widget!</h3></th>
 *   </tr>
 *   <tr>
 *     <td class="middle-widget">
 *       <h4>I'm the middle widget!</h4>
 *       <span class="inner-widget">
 *         <h5>I'm the inner widget!</h5>
 *       </span>
 *     </td>
 *   </tr>
 * </table>
 * 
 * @author steve
 */
public class Example1OuterWidget extends Widget
{
    // should be familiar to UiBinder afficionado's
    interface MyActivator extends ElementActivator<Example1OuterWidget> {}
    private static MyActivator activator = GWT.create(MyActivator.class);
    
    // auto-magically bind the "middle" field onto an html element with
    // class="middle-widget". Doesn't matter where that widget is, provided
    // it is a descendant of the Element provided to our constructor 
    @RuntimeUiWidget(cssClass="middle-widget") Example1MiddleWidget middle;

    public Example1OuterWidget(Element anElement) {
        setElement(activator.activate(this, anElement));
        
        // must attach and set up to detach
        // if we want to play nicely, also we 
        // must do this if we want to be able
        // to sink events
        onAttach();
        RootPanel.detachOnWindowClose(this);
        
        // do something visible to show that we've bound to the element
        getElement().getStyle().setBorderColor("red");
        getElement().getStyle().setBorderStyle(BorderStyle.SOLID);
        getElement().getStyle().setBorderWidth(1, Unit.PX);
    }

}
