package com.sjl.gwt.progressive.rebind;

import com.google.gwt.core.ext.typeinfo.*;

public class PreparedFieldBinderFactory extends FieldBinderFactoryImpl
{
    public PreparedFieldBinderFactory(TypeOracle anOracle)
    {
        super();
        
        // attribute field binder
        addBinder(new AttributeFieldBinder(new CachingAttributeConverterLookup(anOracle)));
        
        // simple (widget) field binders
        addBinder(new WidgetFieldBinder.Id(anOracle));
        addBinder(new WidgetFieldBinder.Css(anOracle));
        addBinder(new WidgetFieldBinder.Tag(anOracle));
        addBinder(new WidgetFieldBinder.TagAndCss(anOracle));
        
        // list (widget) field binders
        addBinder(new WidgetListFieldBinder.Css(anOracle));
        addBinder(new WidgetListFieldBinder.Tag(anOracle));
        addBinder(new WidgetListFieldBinder.TagAndCss(anOracle));
        
        // error handling - programmer has forgotten to add params to
        // the field annotations
        addBinder(MISSING_ATTRIBUTE_PARAMS_BINDER);
        addBinder(MISSING_WIDGET_PARAMS_BINDER);
    }
}
