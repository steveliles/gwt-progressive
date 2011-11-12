package com.sjl.gwt.progressive.client.bootstrap;

import java.util.*;

import com.google.gwt.core.client.*;
import com.google.gwt.dom.client.*;
import com.sjl.gwt.progressive.client.util.*;

public class Activator
{
    private TreeSet<Activation> activations;
    
    public Activator()
    {
        activations = new TreeSet<Activation>();        
    }
    
    public void add(Activation anActivation)
    {   
        if (!activations.add(anActivation))
        {
            GWT.log("More than one activation shares the same binding, " + anActivation.getActivatedClassName() + " binding will be ignored.");
        }
    }
    
    public void activate(Element aStartingPoint)
    {                
        Elements.each(aStartingPoint, new Elements.ElementVisitor()
        {
            public boolean with(Element anElement)
            {                
                for (Activation _a : activations)
                {
                    if (_a.matches(anElement))
                    {
                        _a.activate(anElement);
                        return false;
                    }
                }
                return true;
            }
        });
    }
}
