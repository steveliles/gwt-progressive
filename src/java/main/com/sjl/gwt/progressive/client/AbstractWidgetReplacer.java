package com.sjl.gwt.progressive.client;

import com.google.gwt.dom.client.*;
import com.google.gwt.user.client.ui.*;

public abstract class AbstractWidgetReplacer<T extends Widget> extends AbstractReplacer<T>
{
    @Override
    public void update(T aT, Element anElement, PageUpdateCallback<T> aCallback)
    {        
        try
        {
            Element _parent = (Element) aT.getElement().getParentElement();                        
            _parent.replaceChild(anElement, aT.getElement());            
            
            aCallback.onSuccess(newWidgetInstance(anElement));
        }
        catch (Exception anExc)
        {
            aCallback.onError(anExc);
        }
    }
}
