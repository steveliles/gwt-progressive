package com.sjl.gwt.progressive.rebind;

import com.google.gwt.core.ext.typeinfo.*;
import com.google.gwt.user.rebind.*;

public interface FieldBinder
{
    public static FieldBinder NULL_OBJECT = new FieldBinder() 
    {
        public boolean canBind(FieldInfo aFieldInfo)
        {
            return false;
        }
        
        public void bind(JClassType anOwner, FieldInfo aFieldInfo, SourceWriter aWriter)
        {
            // deliberate no-op
        }
    };
    
    public boolean canBind(FieldInfo aFieldInfo);
    
    public void bind(JClassType anOwner, FieldInfo aFieldInfo, SourceWriter aWriter);
}
