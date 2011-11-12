package com.sjl.gwt.progressive.client.convert;

import com.google.gwt.dom.client.*;
import com.sjl.gwt.progressive.client.util.*;

public abstract class AttributeConverter<T> 
{  
    public T get(Element anElement, String anAttributeName, T aDefaultValue) 
    {
        String _param = anElement.getAttribute(anAttributeName);
        return Strings.isEmpty(_param) ? aDefaultValue : convert(_param);
    }

    public abstract T convert(String aString);
}
