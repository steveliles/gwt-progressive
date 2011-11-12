package com.sjl.gwt.progressive.rebind;

import com.google.gwt.core.ext.typeinfo.*;
import com.google.gwt.user.rebind.*;
import com.sjl.gwt.progressive.client.widgets.*;

public abstract class AbstractWidgetBinder implements FieldBinder
{
    private TypeOracle oracle;    
    private JClassType boundPanel;

    public AbstractWidgetBinder(TypeOracle anOracle)
    {
        oracle = anOracle;        
    }
    
    public abstract void bind(JClassType anOwner, FieldInfo aField, SourceWriter aWriter);
    
    protected JClassType getClassType(JField aField)
    {
        return oracle.findType(aField.getType().getQualifiedSourceName());
    }

    protected void logicalAdd(JClassType anOwner, FieldInfo aFieldInfo, SourceWriter aWriter)
    {
        logicalAdd(anOwner, String.format("w.%s", aFieldInfo.getName()), aWriter);
    }

    protected void logicalAdd(JClassType anOwner, String aVariableName, SourceWriter aWriter)
    {
        if (anOwner.isAssignableTo(getBoundPanel()))
        {
            aWriter.println(String.format("w.add(%s, false);", aVariableName));
        }
    }

    protected void attachIfPossible(FieldInfo aFieldInfo, SourceWriter aWriter)
    {
        attachIfPossible(aFieldInfo, String.format("w.%s", aFieldInfo.getName()), aWriter);
    }

    protected void attachIfPossible(FieldInfo aFieldInfo, String aVariableName, SourceWriter aWriter)
    {
        if (aFieldInfo.isWidgetField() && aFieldInfo.asWidgetField().isAttachable())
        {
            aWriter.println(String.format("((Attachable)%s).onAttach();", aVariableName));
        }
    }
    
    private JClassType getBoundPanel()
    {
        if (boundPanel == null)
        {            
            boundPanel = oracle.findType(BoundPanel.class.getCanonicalName());
        }
        return boundPanel;
    }
        
}
