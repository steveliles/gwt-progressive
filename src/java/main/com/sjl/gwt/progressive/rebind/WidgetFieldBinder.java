package com.sjl.gwt.progressive.rebind;

import com.google.gwt.core.ext.typeinfo.*;
import com.google.gwt.user.rebind.*;

public abstract class WidgetFieldBinder extends AbstractWidgetBinder
{
    static class Id extends WidgetFieldBinder
    {
        public Id(TypeOracle anOracle)
        {
            super(anOracle);
        }

        @Override
        public boolean canBind(FieldInfo aFieldInfo)
        {                        
            return ( 
                aFieldInfo.isWidgetField() &&
                aFieldInfo.asWidgetField().isSimpleField() &&
                aFieldInfo.asWidgetField().hasId()
            );
        }

        @Override
        protected void extractElementToBind(FieldInfo.WidgetField aFieldInfo, SourceWriter aWriter)
        {
            aWriter.println(String.format("Element _%sElement = Elements.getFirstById(e, \"%s\");", aFieldInfo.getName(), aFieldInfo.getId()));            
        }
    }
    
    static class Css extends WidgetFieldBinder
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
                aFieldInfo.asWidgetField().isSimpleField() &&
                aFieldInfo.asWidgetField().hasCssClass() && 
                aFieldInfo.asWidgetField().hasNoTag()
            );
        }

        @Override
        protected void extractElementToBind(FieldInfo.WidgetField aFieldInfo, SourceWriter aWriter)
        {
            aWriter.println(String.format("Element _%sElement = Elements.getFirstByCssClass(e, \"%s\");", aFieldInfo.getName(), aFieldInfo.getCssClass()));            
        }
    }

    static class Tag extends WidgetFieldBinder
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
                aFieldInfo.asWidgetField().isSimpleField() &&
                aFieldInfo.asWidgetField().hasNoCssClass() && 
                aFieldInfo.asWidgetField().hasTag()
            );
        }
        
        @Override
        protected void extractElementToBind(FieldInfo.WidgetField aFieldInfo, SourceWriter aWriter)
        {
            aWriter.println(String.format("Element _%sElement = Elements.getFirstByTagName(e, \"%s\");", aFieldInfo.getName(), aFieldInfo.getTag()));   
        }
    }

    static class TagAndCss extends WidgetFieldBinder
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
                aFieldInfo.asWidgetField().isSimpleField() &&
                aFieldInfo.asWidgetField().hasCssClass() && 
                aFieldInfo.asWidgetField().hasTag()
            );
        }

        @Override
        protected void extractElementToBind(FieldInfo.WidgetField aFieldInfo, SourceWriter aWriter)
        {
            aWriter.println(String.format("Element _%sElement = Elements.getFirstByTagNameAndCssClass(e, \"%s\", \"%s\");", aFieldInfo.getName(), aFieldInfo.getTag(), aFieldInfo.getCssClass()));
        }        
    }
    
    public WidgetFieldBinder(TypeOracle anOracle)
    {
        super(anOracle);                        
    }

    protected void bindWithWrapMethod(JClassType anOwner, FieldInfo.WidgetField aFieldInfo, SourceWriter aWriter)
    {
        extractElementToBind(aFieldInfo, aWriter);
        aWriter.println(String.format("if (_%sElement != null) {", aFieldInfo.getName()));
        aWriter.indent();
        aWriter.println(String.format("w.%s = %s.wrap(_%sElement);", aFieldInfo.getName(), aFieldInfo.getQualifiedSourceName(), aFieldInfo.getName()));
        aWriter.outdent();
        aWriter.println("}");
    }
    
    protected void bindWithNewInstance(JClassType anOwner, FieldInfo.WidgetField aFieldInfo, SourceWriter aWriter)
    {
        extractElementToBind(aFieldInfo, aWriter);
        aWriter.println(String.format("if (_%sElement != null) {", aFieldInfo.getName()));
        aWriter.indent();
        aWriter.println(String.format("w.%s = new %s(_%sElement);", aFieldInfo.getName(), aFieldInfo.getQualifiedSourceName(), aFieldInfo.getName()));
        logicalAdd(anOwner, aFieldInfo, aWriter);
        attachIfPossible(aFieldInfo, aWriter);
        aWriter.outdent();
        aWriter.println("}");
    }

    protected abstract void extractElementToBind(FieldInfo.WidgetField aFieldInfo, SourceWriter aWriter);        
    
    public void bind(JClassType anOwner, FieldInfo aFieldInfo, SourceWriter aWriter)
    {
        // special handling for classes with static wrap methods
        if (aFieldInfo.asWidgetField().hasWrapElementMethod())
        {
            bindWithWrapMethod(anOwner, aFieldInfo.asWidgetField(), aWriter);
        } 
        else
        {
            bindWithNewInstance(anOwner, aFieldInfo.asWidgetField(), aWriter);
        }            
    }    
}