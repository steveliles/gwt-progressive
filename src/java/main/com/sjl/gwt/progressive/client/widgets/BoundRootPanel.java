package com.sjl.gwt.progressive.client.widgets;

import com.google.gwt.user.client.ui.*;
import com.sjl.gwt.progressive.client.*;

/**
 * Extend this if you want to make widgets that serve as the root binding point for 
 * activation. These widgets are special in that they need to register to be detached 
 * on window close, whereas any child widgets must not. 
 * 
 * @author steve
 */
public abstract class BoundRootPanel extends AbstractBoundPanel implements Attachable
{       
    @Override
    public void onAttach()
    {
        super.onAttach();
        
        // Make sure we get cleaned up
        RootPanel.detachOnWindowClose(this);
    }    
}
