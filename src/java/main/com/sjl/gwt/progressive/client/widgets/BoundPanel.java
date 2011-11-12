package com.sjl.gwt.progressive.client.widgets;

import com.google.gwt.user.client.ui.*;
import com.sjl.gwt.progressive.client.*;

public interface BoundPanel 
{

    /**
     * Special add method that is known to the Activator generator and allows it
     * to perform "logical add" without disrupting the DOM.
     * 
     * @param aChild
     * @param anIsPhysicalAttach
     */
    public abstract void add(Widget aChild, boolean anIsPhysicalAttach);

    /**
     * Replaces the given widget with a new instance created by the factory.
     * The NEW widget instance is returned.
     * 
     * @param <T>
     * @param aToReplace
     * @param aFactory
     * @return
     */
    public <T extends Widget> T replace(Widget aToReplace, WidgetFactory<T> aFactory);
}