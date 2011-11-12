package com.sjl.gwt.progressive.rebind;

import com.google.gwt.core.ext.typeinfo.*;
import com.google.gwt.user.rebind.*;

public class AttributeFieldBinder implements FieldBinder
{
    private AttributeConverterLookup converterLookup;
    
    public AttributeFieldBinder(AttributeConverterLookup aConverterLookup)
    {
        converterLookup = aConverterLookup;
    }
    
    public boolean canBind(FieldInfo aFieldInfo)
    {
        return aFieldInfo.isAttributeField() && (aFieldInfo.asAttributeField().hasAttributeName());
    }
    
    public void bind(JClassType anOwnerType, FieldInfo aFieldInfo, SourceWriter aWriter)
    {        
        String _converter = converterLookup.getAttributeConverterClass(aFieldInfo.getQualifiedSourceName());
        bindAttribute(aWriter, aFieldInfo, aFieldInfo.asAttributeField().getAttributeName(), _converter);
    }
    
    private void bindAttribute(SourceWriter aWriter, FieldInfo aFieldInfo, String anAttributeName, String aConverter)
    {
        aWriter.println(String.format("w.%1$s = new %2$s().get(e, \"%3$s\", w.%1$s);", aFieldInfo.getName(), aConverter, anAttributeName));
    }
        
}
