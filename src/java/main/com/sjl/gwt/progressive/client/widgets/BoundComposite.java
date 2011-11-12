package com.sjl.gwt.progressive.client.widgets;

import com.google.gwt.user.client.ui.*;
import com.sjl.gwt.progressive.client.*;

/**
 * Convenience class for extending Composite without having to explicitly invoke
 * onAttach in order to get properly engaged with the GWT system (incl. events).
 * GWT-Activator's generator will invoke onAttach for you. 
 * 
 * @author steve
 */
public abstract class BoundComposite extends Composite implements Attachable
{
    @Override
    public void onAttach()
    {
        super.onAttach();
    }
}
