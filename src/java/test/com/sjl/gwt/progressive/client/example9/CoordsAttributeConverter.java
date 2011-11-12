package com.sjl.gwt.progressive.client.example9;

import com.sjl.gwt.progressive.client.*;
import com.sjl.gwt.progressive.client.convert.*;

@RuntimeUiAttributeConverter
public class CoordsAttributeConverter extends AttributeConverter<Coords>
{
    public Coords convert(String aString)
    {
        String[] _parts = aString.split("x");
        return new Coords(Integer.parseInt(_parts[0]), Integer.parseInt(_parts[1]));
    }        
}
