package com.sjl.gwt.progressive.client;

import com.google.gwt.dom.client.*;
import com.sjl.gwt.progressive.client.widgets.*;

public abstract class AbstractBoundRootPanelReplacer<T extends BoundRootPanel> extends AbstractReplacer<T>
{
    @Override
    public void update(T aT, Element anElement, PageUpdateCallback<T> aCallback)
    {
        try
        {
            aT.removeFromParent();
    
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
