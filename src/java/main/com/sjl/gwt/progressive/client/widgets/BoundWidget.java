package com.sjl.gwt.progressive.client.widgets;

import com.google.gwt.user.client.ui.*;
import com.sjl.gwt.progressive.client.*;

/**
 * Convenience class for extending Widget without having to explicitly invoke
 * onAttach in order to get properly engaged with the GWT system (incl. events).
 * GWT-Activator's generator will invoke onAttach for you. 
 * 
 * @author steve
 */
public abstract class BoundWidget extends Widget implements Attachable
{
    @Override
    public void onAttach()
    {
        super.onAttach();
    }
    
    /**
     * hook for initiating some behaviour following a partial-page update and re-bind
     */
    public void onRebind()
    {        
    }
}
