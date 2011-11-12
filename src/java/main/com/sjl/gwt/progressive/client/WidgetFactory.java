package com.sjl.gwt.progressive.client;

import com.google.gwt.dom.client.*;
import com.google.gwt.user.client.ui.*;

public interface WidgetFactory<T extends Widget>
{
    public Element getElement();
    
    public T newWidgetInstance();
}
