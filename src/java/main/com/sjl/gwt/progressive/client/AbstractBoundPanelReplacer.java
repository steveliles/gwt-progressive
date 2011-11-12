package com.sjl.gwt.progressive.client;

import com.google.gwt.dom.client.*;
import com.google.gwt.user.client.ui.*;
import com.sjl.gwt.progressive.client.widgets.*;

public abstract class AbstractBoundPanelReplacer<T extends Widget> extends AbstractReplacer<T>
{
    public abstract T newWidgetInstance(Element anElement);

    @Override
    public void update(T aT, final Element anElement, PageUpdateCallback<T> aCallback)
    {
        if (aT.getParent() == null)
        {
            aCallback.onError(new RuntimeException("This widget does not have a parent, and should probably be declared as a BoundRootPanel!"));
        }

        try
        {
            BoundPanel _p = ((BoundPanel)aT.getParent());
            
            aCallback.onSuccess(_p.replace(aT, new WidgetFactory<T>()
            {                
                @Override
                public Element getElement()
                {
                    return anElement;
                }

                @Override
                public T newWidgetInstance()
                {
                    return AbstractBoundPanelReplacer.this.newWidgetInstance(anElement);
                }                
            }));
        }
        catch (Exception anExc)
        {
            aCallback.onError(anExc);
        }
    }            
}
