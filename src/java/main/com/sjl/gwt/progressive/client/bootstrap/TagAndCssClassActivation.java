package com.sjl.gwt.progressive.client.bootstrap;

import com.google.gwt.dom.client.*;
import com.sjl.gwt.progressive.client.util.*;

public abstract class TagAndCssClassActivation extends AbstractActivation
{
    private String tag;
    private String cssClass;
    
    public TagAndCssClassActivation(String aTag, String aCssClass, String anActivatedClassName)
    {
        super(anActivatedClassName);
        
        tag = aTag;
        cssClass = aCssClass;
    }

    @Override
    public boolean matches(Element anElement)
    {
        return tag.equals(anElement.getTagName()) && Strings.includes(cssClass, anElement.getClassName());
    }      
    
    @Override
    public int compareTo(Activation anActivation)
    {
        if (anActivation instanceof IdActivation)
        {
            return 1;
        }
        
        if (anActivation instanceof TagAndCssClassActivation)
        {
            TagAndCssClassActivation _other = (TagAndCssClassActivation) anActivation;
            int _tags = tag.compareTo(_other.tag);
            
            return (_tags != 0) ? _tags : cssClass.compareTo(_other.cssClass);
        }
        
        return -1;
    }
    
    @Override
    public String toString()
    {
        return "tag[" + tag + "]+css[" + cssClass + "]";
    }
}
