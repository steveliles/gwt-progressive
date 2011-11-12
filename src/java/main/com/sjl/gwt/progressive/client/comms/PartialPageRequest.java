package com.sjl.gwt.progressive.client.comms;

import com.google.gwt.dom.client.*;

public interface PartialPageRequest
{
    public interface Callback
    {
        void onSuccess(Element aPartialPage);
        
        void onError(Throwable anException);
    }
    
    public void execute(final Callback aCallback);
}