package com.sjl.gwt.progressive.rebind;

import java.util.*;

import com.google.gwt.core.ext.typeinfo.*;
import com.google.gwt.user.rebind.*;
import com.sjl.gwt.progressive.client.*;
import com.sjl.gwt.progressive.client.util.*;

public class WidgetFieldInfo implements FieldInfo.WidgetField
{
    JField field;
    TypeOracle oracle;
    RuntimeUiWidget runtimeUiWidget;
    
    public WidgetFieldInfo(JField aField, TypeOracle anOracle)
    {
        field = aField;        
        oracle = anOracle;
    }

    public RuntimeUiWidget getRuntimeUiWidget()
    {
        if (runtimeUiWidget == null)
        {
            runtimeUiWidget = field.getAnnotation(RuntimeUiWidget.class);
        }
        return runtimeUiWidget;
    }
    
    @Override
    public String getName()
    {
        return field.getName();
    }

    @Override
    public boolean isAttributeField()
    {
        return false;
    }
    
    @Override
    public boolean isWidgetField()
    {
        return true;
    }
    
    @Override
    public AttributeField asAttributeField()
    {
        throw new RuntimeException("Not an AttributeField - check first");
    }

    @Override
    public WidgetField asWidgetField()
    {
        return this;
    }

    @Override
    public boolean hasNoId()
    {
        return !hasId();
    }
    
    @Override
    public boolean hasId()
    {
        return Strings.isNotEmpty(getRuntimeUiWidget().id());
    }

    @Override
    public String getId()
    {
        return getRuntimeUiWidget().id();
    }
    
    @Override
    public boolean hasNoTag()
    {
        return !hasTag();
    }
    
    @Override
    public boolean hasTag()
    {
        return Strings.isNotEmpty(getRuntimeUiWidget().tag());
    }

    @Override
    public String getTag()
    {
        return getRuntimeUiWidget().tag();
    }
    
    @Override
    public boolean hasNoCssClass()
    {
        return !hasCssClass();
    }
    
    @Override
    public boolean hasCssClass()
    {
        return (Strings.isNotEmpty(getRuntimeUiWidget().cssClass()));
    }
    
    @Override
    public String getCssClass()
    {
        return getRuntimeUiWidget().cssClass();
    }
    
    @Override
    public boolean isSimpleField()
    {
        return !isListField();
    }
    
    @Override
    public boolean isListField()
    {
        return (List.class.getCanonicalName().equals(field.getType().getQualifiedSourceName()));        
    }

    @Override
    public JClassType getListElementType()
    {
        return field.getType().isParameterized().getTypeArgs()[0];
    }
    
    @Override
    public String getQualifiedSourceName()
    {
        return field.getType().getQualifiedSourceName();
    }
    
    @Override
    public boolean isAttachable()
    {
        return (isSimpleField()) ? 
            getClassType().isAssignableTo(getAttachable()):
            getListElementType().isAssignableTo(getAttachable());
    }
    
    public JClassType getClassType()
    {
        return oracle.findType(getQualifiedSourceName());
    }
    
    private JClassType getAttachable()
    {
        return oracle.findType(Attachable.class.getCanonicalName());
    }
    
    @Override
    public boolean hasWrapElementMethod()
    {
        JClassType _type = (isListField()) ? getListElementType() : getClassType();
            
        for (JMethod _m : _type.getMethods())
        {
            if ("wrap".equals(_m.getName()))
            {
                JParameter[] _params = _m.getParameters();
                return (_params.length == 1) && ("com.google.gwt.dom.client.Element".equals(_params[0].getType().getQualifiedSourceName()));
            }
        }
        return false;
    }
    
    @Override
    public void addImports(ClassSourceFileComposerFactory aTo)
    {
        if (isListField())
        {
            aTo.addImport(getListElementType().getQualifiedSourceName());
        }
        else
        {
            aTo.addImport(getQualifiedSourceName());
        }
    }
}
