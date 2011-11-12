package com.sjl.gwt.progressive.client;

import java.lang.annotation.*;

/**
 * Widget fields annotated with @RuntimeUiAttribute will have code generated at gwt-compile-time which
 * seeks to bind those fields (at runtime) onto attributes of the widget's own element, via a simple 
 * converter class.
 * 
 * Bespoke converters can be registered by annotating them with @RuntimeUiAttributeConverter.
 * 
 * @author steve
 */
@Target(ElementType.FIELD)
public @interface RuntimeUiAttribute
{
    String value();
}
