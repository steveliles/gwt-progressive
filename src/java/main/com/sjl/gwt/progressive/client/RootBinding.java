package com.sjl.gwt.progressive.client;

import java.lang.annotation.*;

/**
 * Widgets annotated with RootBinding are interpreted as "root" level widgets that should be 
 * bound onto existing DOM elements at script-load time. 
 * 
 * The DOM will be scanned looking for matches of tag and cssClass (or one of those, if the other 
 * is not supplied), and when a match is found a new instance of the widget is created and bound 
 * to the matching element. 
 * 
 * Precedence of activation is selected by most specific match wins - where most-to-least specific
 * is defined by:
 *  
 * 1. tag-name + css  (most specific)
 * 2. css
 * 3. tag-name  (least specific)
 * 
 * Within one of those groups, if an element has more than one css-class for which an activation is
 * bound, lexicographical ordering of css class is used (first match wins). TODO: allow controlling
 * precedence with priorities?
 * 
 * @author steve
 */

@Target(ElementType.TYPE)
public @interface RootBinding
{
    String id() default "";    
    
    String tag() default "";

    String cssClass() default "";
    
}
