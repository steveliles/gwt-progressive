package com.sjl.gwt.progressive.client;

import com.google.gwt.dom.client.*;
import com.google.gwt.user.client.ui.*;
import com.sjl.gwt.progressive.client.comms.*;
import com.sjl.gwt.progressive.client.comms.PartialPageRequest.*;

public abstract class AbstractReplacer<T extends Widget> implements Replacer<T>
{
    public abstract T newWidgetInstance(Element anElement);        

    @Override
    public void update(T aT, Element anElement)
    {
        update(aT, anElement, new PageUpdateCallbackAdapter<T>());
    }

    @Override
    public void update(T aT, String aUrl)
    {
        update(aT, aUrl, new PageUpdateCallbackAdapter<T>());
    }

    @Override
    public void update(final T aWidgetToReplace, final String aUrl, final PageUpdateCallback<T> aCallback)
    {
        new DefaultPartialPageRequest(aUrl).execute(new Callback()
        {            
            public void onSuccess(Element aPartialPage)
            {
                AbstractReplacer.this.update(aWidgetToReplace, aPartialPage, aCallback);                
            }
            
            @Override
            public void onError(Throwable anException)
            {
                aCallback.onError(anException);                
            }
        });               
    }

}
