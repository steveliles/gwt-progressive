package com.sjl.gwt.progressive.client.bootstrap;

import com.google.gwt.dom.client.*;

public abstract class IdActivation extends AbstractActivation
{
    private String id;
    
    public IdActivation(String anId, String anActivatedClassName)
    {
        super(anActivatedClassName);
        
        id = anId;
    }
    
    @Override
    public boolean matches(Element anElement)
    {
        return id.equals(anElement.getId());
    }
    
    @Override
    public int compareTo(Activation anActivation)
    {
        if (anActivation instanceof IdActivation)
        {
            return id.compareTo(((IdActivation)anActivation).id);
        }
                
        return 1;
    }
    
    @Override
    public String toString()
    {
        return "id-only[" + id + "]";
    }
}
