package com.sjl.gwt.progressive.client.convert;

import com.sjl.gwt.progressive.client.*;

@RuntimeUiAttributeConverter
public class StringAttributeConverter extends AttributeConverter<String> 
{
    public String convert(String aString) 
    {
        return aString;
    }
}