package com.sjl.gwt.progressive.client.convert;

import com.sjl.gwt.progressive.client.*;

@RuntimeUiAttributeConverter
public class LongAttributeConverter extends AttributeConverter<Long>
{
    @Override
    public Long convert(String aString)
    {
        return Long.parseLong(aString);
    }
}
