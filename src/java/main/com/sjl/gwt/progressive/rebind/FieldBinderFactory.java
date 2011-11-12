package com.sjl.gwt.progressive.rebind;


public interface FieldBinderFactory
{
    public boolean hasPotentialBinders(FieldInfo aFieldInfo);
    
    public FieldBinder getBinder(FieldInfo aFieldInfo);
}
