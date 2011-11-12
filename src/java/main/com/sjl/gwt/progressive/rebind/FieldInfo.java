package com.sjl.gwt.progressive.rebind;

import com.google.gwt.core.ext.typeinfo.*;
import com.google.gwt.user.rebind.*;

public interface FieldInfo
{
    public interface Factory 
    {
        public FieldInfo get(JField aField);
    }

    public boolean isAttributeField();
    
    public boolean isWidgetField();
    
    public AttributeField asAttributeField();
    
    public WidgetField asWidgetField();    
    
    public void addImports(ClassSourceFileComposerFactory aTo);
    
    public abstract String getQualifiedSourceName();

    public abstract String getName();
        
    public JClassType getClassType();  
    

    public interface WidgetField extends FieldInfo
    {
        public abstract boolean hasNoId();
        
        public abstract boolean hasId();
        
        public abstract String getId();
        
        public abstract boolean hasNoTag();

        public abstract boolean hasTag();

        public abstract String getTag();
        
        public abstract boolean hasNoCssClass();

        public abstract boolean hasCssClass();

        public abstract String getCssClass();
        
        public abstract boolean isSimpleField();

        public abstract boolean isListField();

        public abstract JClassType getListElementType();
        
        public boolean hasWrapElementMethod();
        
        public boolean isAttachable();
    }
    
    public interface AttributeField extends FieldInfo
    {
        public abstract boolean hasAttributeName();

        public abstract boolean hasNoAttributeName();

        public abstract String getAttributeName();
    }
}