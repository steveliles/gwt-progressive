package com.sjl.gwt.progressive.rebind;

import java.util.*;

import com.google.gwt.core.ext.typeinfo.*;
import com.sjl.gwt.progressive.client.*;

public class CachingAttributeConverterLookup implements AttributeConverterLookup
{
    private Map<String, String> convertersByType;
    
    public CachingAttributeConverterLookup(TypeOracle anOracle)
    {
        convertersByType = new HashMap<String, String>();
        for (JClassType _type : anOracle.getTypes())
        {
            if (_type.isAnnotationPresent(RuntimeUiAttributeConverter.class))
            {                    
                JClassType _convertsTo = _type.getSuperclass().isParameterized().getTypeArgs()[0];
                convertersByType.put(_convertsTo.getQualifiedSourceName(), _type.getQualifiedSourceName());
            }
        }
    }
    
    @Override
    public String getAttributeConverterClass(String aQualifiedTargetType)
    {
        String _converterType = convertersByType.get(aQualifiedTargetType);
        if (_converterType == null)
        {
            throw new RuntimeException("No converter available for " + aQualifiedTargetType);
        }
        else
        {
            return _converterType;
        }
    }        
}