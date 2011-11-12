package com.sjl.gwt.progressive.client.bootstrap;

import com.google.gwt.dom.client.*;
import com.sjl.gwt.progressive.client.util.*;

public abstract class CssClassOnlyActivation extends AbstractActivation
{
    private String cssClass;
    
    public CssClassOnlyActivation(String aCssClass, String anActivatedClassName)
    {
        super(anActivatedClassName);
        
        cssClass = aCssClass;        
    }
    
    @Override
    public boolean matches(Element anElement)
    {
        return Strings.includes(cssClass, anElement.getClassName());
    }
    
    @Override
    public int compareTo(Activation anActivation)
    {
        if (anActivation instanceof IdActivation)
        {
            return 1;
        }
        
        if (anActivation instanceof TagOnlyActivation)
        {
            return -1;
        }
        
        if (anActivation instanceof TagAndCssClassActivation)
        {
            return 1;
        }
        
        if (anActivation instanceof CssClassOnlyActivation)
        {
            return cssClass.compareTo(((CssClassOnlyActivation)anActivation).cssClass);
        }
        
        return -1;
    }    
    
    @Override
    public String toString()
    {
        return "css-only[" + cssClass + "]";
    }
}
