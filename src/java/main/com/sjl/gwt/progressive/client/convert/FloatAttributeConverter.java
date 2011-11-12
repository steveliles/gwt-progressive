package com.sjl.gwt.progressive.client.convert;

import com.sjl.gwt.progressive.client.*;

@RuntimeUiAttributeConverter
public class FloatAttributeConverter extends AttributeConverter<Float> 
{
    public Float convert(String aString) 
    {
        return Float.parseFloat(aString);
    }
}
