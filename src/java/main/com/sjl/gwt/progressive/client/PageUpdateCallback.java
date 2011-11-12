package com.sjl.gwt.progressive.client;

import com.google.gwt.user.client.ui.*;

public interface PageUpdateCallback<T extends Widget>
{
    public void onSuccess(T aNewInstance);
    
    public void onError(Throwable anExc);
}