package com.sjl.gwt.progressive.rebind;

import java.util.*;

import com.google.gwt.core.ext.typeinfo.*;
import com.google.gwt.user.rebind.*;

public class FieldBinderFactoryImpl implements FieldBinderFactory
{    
    public static FieldBinder MISSING_ATTRIBUTE_PARAMS_BINDER = new MissingAttributeAnnotationParamsBinder();
    public static FieldBinder MISSING_WIDGET_PARAMS_BINDER = new MissingWidgetAnnotationParamsBinder();
    
    private List<FieldBinder> binders;
        
    public FieldBinderFactoryImpl()
    {                
        binders = new ArrayList<FieldBinder>();                
    }        
    
    public void addBinder(FieldBinder aBinder)
    {
        binders.add(aBinder);
    }
    
    @Override
    public boolean hasPotentialBinders(FieldInfo aFieldInfo)
    {
        return (
           aFieldInfo.isAttributeField() ||
           aFieldInfo.isWidgetField()
        );
    }

    @Override
    public FieldBinder getBinder(FieldInfo aFieldInfo)
    {        
        for (FieldBinder _b : binders)
        {
            if (_b.canBind(aFieldInfo))
            {
                return _b;
            }
        }
                
        return FieldBinder.NULL_OBJECT; 
    }

    private static class MissingAttributeAnnotationParamsBinder implements FieldBinder
    {
        @Override
        public boolean canBind(FieldInfo aFieldInfo)
        {
            return (aFieldInfo.isAttributeField() && aFieldInfo.asAttributeField().hasNoAttributeName());
        }

        @Override
        public void bind(JClassType anOwner, FieldInfo aField, SourceWriter aWriter)
        {
            throw new RuntimeException(String.format("Neither tag nor cssClass specified on field %s of class %s: your @RuntimeUiField declaration is invalid.", aField.getName(), anOwner.getName()));    
        }        
    }
    
    private static class MissingWidgetAnnotationParamsBinder implements FieldBinder
    {
        @Override
        public boolean canBind(FieldInfo aFieldInfo)
        {
            return (
                aFieldInfo.isWidgetField() && 
                aFieldInfo.asWidgetField().hasNoId() &&
                aFieldInfo.asWidgetField().hasNoTag() && 
                aFieldInfo.asWidgetField().hasNoCssClass()
            );
        }

        @Override
        public void bind(JClassType anOwner, FieldInfo aField, SourceWriter aWriter)
        {
            throw new RuntimeException(String.format("Neither tag nor cssClass specified on field %s of class %s: your @RuntimeUiField declaration is invalid.", aField.getName(), anOwner.getName()));    
        }        
    }                       
}
