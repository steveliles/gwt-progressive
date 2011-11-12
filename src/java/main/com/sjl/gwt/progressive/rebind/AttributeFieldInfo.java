package com.sjl.gwt.progressive.rebind;

import com.google.gwt.core.ext.typeinfo.*;
import com.google.gwt.user.rebind.*;
import com.sjl.gwt.progressive.client.*;

public class AttributeFieldInfo implements FieldInfo.AttributeField
{
    JField field;
    TypeOracle oracle;
    RuntimeUiAttribute runtimeUiAttribute;
    
    public AttributeFieldInfo(JField aField, TypeOracle anOracle)
    {
        field = aField;        
        oracle = anOracle;
    }

    public RuntimeUiAttribute getRuntimeUiAttribute()
    {
        if (runtimeUiAttribute == null)
        {
            runtimeUiAttribute = field.getAnnotation(RuntimeUiAttribute.class);
        }
        return runtimeUiAttribute;
    }    

    @Override
    public boolean isAttributeField()
    {
        return true;
    }

    @Override
    public boolean isWidgetField()
    {
        return false;
    }

    @Override
    public AttributeField asAttributeField()
    {
        return this;
    }

    @Override
    public WidgetField asWidgetField()
    {
        throw new RuntimeException("Not a WidgetField - check first");
    }

    @Override
    public String getQualifiedSourceName()
    {
        return field.getType().getQualifiedSourceName();
    }

    @Override
    public String getName()
    {
        return field.getName();
    }

    @Override
    public JClassType getClassType()
    {
        return oracle.findType(getQualifiedSourceName());
    }

    @Override
    public boolean hasAttributeName()
    {
        return getRuntimeUiAttribute().value() != null;
    }

    @Override
    public boolean hasNoAttributeName()
    {
        return !hasAttributeName();
    }

    @Override
    public String getAttributeName()
    {
        return getRuntimeUiAttribute().value();
    }

    @Override
    public void addImports(ClassSourceFileComposerFactory aTo)
    {
        aTo.addImport(getQualifiedSourceName());
    }
}
