package com.sjl.gwt.progressive.client.convert;

import com.sjl.gwt.progressive.client.*;

@RuntimeUiAttributeConverter
public class IntegerAttributeConverter extends AttributeConverter<Integer> 
{
    public Integer convert(String aString) 
    {
        return Integer.parseInt(aString);
    }
}
