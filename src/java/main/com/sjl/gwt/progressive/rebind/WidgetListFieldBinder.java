package com.sjl.gwt.progressive.rebind;

import com.google.gwt.core.ext.typeinfo.*;
import com.google.gwt.user.rebind.*;

public abstract class WidgetListFieldBinder extends AbstractWidgetBinder
{
    static class Css extends WidgetListFieldBinder
    {
        public Css(TypeOracle anOracle)
        {
            super(anOracle);
        }

        @Override
        public boolean canBind(FieldInfo aFieldInfo)
        {
            return ( 
                aFieldInfo.isWidgetField() &&
                aFieldInfo.asWidgetField().isListField() &&
                aFieldInfo.asWidgetField().hasCssClass() && 
                aFieldInfo.asWidgetField().hasNoTag()
            );
        }
        
        @Override
        protected String getSearchMethod(FieldInfo.WidgetField aFieldInfo)
        {
            return String.format("Elements.getByCssClass(e, \"%s\")", aFieldInfo.getCssClass());
        }          
    }

    static class Tag extends WidgetListFieldBinder
    {
        public Tag(TypeOracle anOracle)
        {
            super(anOracle);
        }
        
        @Override
        public boolean canBind(FieldInfo aFieldInfo)
        {
            return ( 
                aFieldInfo.isWidgetField() &&
                aFieldInfo.asWidgetField().isListField() &&
                aFieldInfo.asWidgetField().hasNoCssClass() && 
                aFieldInfo.asWidgetField().hasTag()
            );
        }
        
        @Override
        protected String getSearchMethod(FieldInfo.WidgetField aFieldInfo)
        {
            return String.format("Elements.getByTagName(e, \"%s\")", aFieldInfo.getTag());
        }          
    }

    static class TagAndCss extends WidgetListFieldBinder
    {
        public TagAndCss(TypeOracle anOracle)
        {
            super(anOracle);
        }
        
        @Override
        public boolean canBind(FieldInfo aFieldInfo)
        {
            return ( 
                aFieldInfo.isWidgetField() &&
                aFieldInfo.asWidgetField().isListField() &&
                aFieldInfo.asWidgetField().hasCssClass() && 
                aFieldInfo.asWidgetField().hasTag()
            );
        }

        @Override
        protected String getSearchMethod(FieldInfo.WidgetField aFieldInfo)
        {
            return String.format("Elements.getByTagNameAndCssClass(e, \"%s\", \"%s\")", aFieldInfo.getTag(), aFieldInfo.getCssClass());
        }          
    }
    
    public WidgetListFieldBinder(TypeOracle anOracle)
    {
        super(anOracle);
    }
    
    protected abstract String getSearchMethod(FieldInfo.WidgetField aField);
        
    @Override
    public void bind(JClassType anOwner, FieldInfo aField, SourceWriter aWriter)
    {
        JClassType _listElementType = aField.asWidgetField().getListElementType();
        aWriter.println(String.format("w.%s = new ArrayList<%s>();", aField.getName(), _listElementType.getQualifiedSourceName()));
        aWriter.println(String.format("for (Element _e : %s) {", getSearchMethod(aField.asWidgetField())));
        aWriter.indent();
                
        createAndAddListElement(aField.asWidgetField(), aWriter);
        
        aWriter.outdent();
        aWriter.println("}");
        aWriter.println();
        
        aWriter.println(String.format("for (Widget _w : w.%s) {", aField.getName()));
        aWriter.indent();
        
        logicalAdd(anOwner, "_w", aWriter);                        
        attachIfPossible(aField, "_w", aWriter);
        
        aWriter.outdent();
        aWriter.println("}");
        aWriter.println();
    }
    
    protected void createAndAddListElement(FieldInfo.WidgetField aField, SourceWriter aWriter)
    {
        if (aField.hasWrapElementMethod())
        {
            aWriter.println(String.format("w.%s.add(%s.wrap(_e));", aField.getName(), aField.getListElementType().getQualifiedSourceName()));
        }
        else
        {
            aWriter.println(String.format("w.%s.add(new %s(_e));", aField.getName(), aField.getListElementType().getQualifiedSourceName()));
        }
    }    
}           
