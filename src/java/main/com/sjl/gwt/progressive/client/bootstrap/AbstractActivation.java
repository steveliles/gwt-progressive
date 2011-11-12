package com.sjl.gwt.progressive.client.bootstrap;

import com.google.gwt.user.client.ui.*;
import com.sjl.gwt.progressive.client.*;

public abstract class AbstractActivation implements Activation
{
    private String activatedClass;
    
    public AbstractActivation(String anActivatedClass)
    {
        activatedClass = anActivatedClass;
    }
    
    @Override
    public String getActivatedClassName()
    {
        return activatedClass;
    }

    @Override
    public void attachIfAttachable(Widget aWidget)
    {
        if (aWidget instanceof Attachable)
        {
            ((Attachable) aWidget).onAttach();
        }
    }

}
