package com.sjl.gwt.progressive.rebind;

import com.google.gwt.core.ext.typeinfo.*;
import com.google.gwt.user.rebind.*;
import com.sjl.gwt.progressive.client.*;

public class FieldInfoFactory implements FieldInfo.Factory
{
    private TypeOracle oracle;
    
    public FieldInfoFactory(TypeOracle anOracle)
    {
        oracle = anOracle;
    }
    
    @Override
    public FieldInfo get(final JField aField)
    {
        if (aField.isAnnotationPresent(RuntimeUiWidget.class))
        {
            return new WidgetFieldInfo(aField, oracle);
        }
        else if (aField.isAnnotationPresent(RuntimeUiAttribute.class))
        {
            return new AttributeFieldInfo(aField, oracle);
        }
        else
        {
            return new FieldInfo()
            {
                @Override
                public boolean isAttributeField()
                {
                    return false;
                }

                @Override
                public boolean isWidgetField()
                {
                    return false;
                }

                @Override
                public AttributeField asAttributeField()
                {
                    throw new RuntimeException("Not an AttributeField");
                }

                @Override
                public WidgetField asWidgetField()
                {
                    throw new RuntimeException("Not aWidgetField");
                }

                @Override
                public String getQualifiedSourceName()
                {
                    return aField.getType().getQualifiedSourceName();
                }

                @Override
                public String getName()
                {
                    return aField.getName();
                }

                @Override
                public JClassType getClassType()
                {
                    return oracle.findType(getQualifiedSourceName());
                }

                @Override
                public void addImports(ClassSourceFileComposerFactory aTo)
                {
                    // no-op
                }                                               
            };
        }
    }

}
