package com.sjl.gwt.progressive.client;

import com.google.gwt.dom.client.*;
import com.google.gwt.user.client.ui.*;

public interface WidgetActivator<T extends Widget> extends Replacer<T>
{
    /**
     * process the instance of T to bind all @RuntimeUiWidget annotated
     * fields with recursively activated instances.
     */
    public Widget activate(T aT, Element anElement);
        
}
