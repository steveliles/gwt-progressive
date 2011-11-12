package com.sjl.gwt.progressive.client.bootstrap;

import com.google.gwt.dom.client.*;

public abstract class TagOnlyActivation extends AbstractActivation
{
    private String tag;
    
    public TagOnlyActivation(String aTag, String anActivatedClassName)
    {
        super(anActivatedClassName);
        
        tag = aTag;
    }
    
    @Override
    public boolean matches(Element anElement)
    {
        return tag.equals(anElement.getTagName());
    }
    
    @Override
    public int compareTo(Activation anActivation)
    {
        if (anActivation instanceof IdActivation)
        {
            return 1;
        }
        
        if (anActivation instanceof CssClassOnlyActivation)
        {
            return 1;
        }
        
        if (anActivation instanceof TagAndCssClassActivation)
        {
            return 1;
        }
        
        if (anActivation instanceof TagOnlyActivation)
        {
            return tag.compareTo(((TagOnlyActivation)anActivation).tag);
        }
        
        return 0;
    }
    
    @Override
    public String toString()
    {
        return "tag-only[" + tag + "]";
    }
}
