package com.sjl.gwt.progressive.client.convert;

import com.sjl.gwt.progressive.client.*;

@RuntimeUiAttributeConverter
public class DoubleAttributeConverter extends AttributeConverter<Double> 
{
    public Double convert(String aString) 
    {
        return Double.parseDouble(aString);
    }
}
