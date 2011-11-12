package com.sjl.gwt.progressive.client.convert;

import com.sjl.gwt.progressive.client.*;

@RuntimeUiAttributeConverter
public class BooleanAttributeConverter extends AttributeConverter<Boolean> 
{
    public Boolean convert(String aString) 
    {
        return Boolean.parseBoolean(aString);
    }
}