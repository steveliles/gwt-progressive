package com.sjl.gwt.progressive.client;

import java.lang.annotation.*;

/**
 * Widget fields annotated with @RuntimeUiWidget will have code generated at gwt-compile-time which
 * seeks to bind those fields (at runtime) onto parts of the DOM nested within the enclosing widget's 
 * element. 
 * 
 * @author steve
 */
@Target(ElementType.FIELD)
public @interface RuntimeUiWidget
{

    String tag() default "";

    String cssClass() default "";
    
    String id() default "";

}
