package com.sjl.gwt.progressive.client;

import com.google.gwt.core.client.*;
import com.google.gwt.user.client.ui.*;

public class PageUpdateCallbackAdapter<T extends Widget> implements PageUpdateCallback<T>
{
    @Override
    public void onSuccess(T aNewInstance)
    {
        GWT.log("Page update completed successfully");
    }

    @Override
    public void onError(Throwable anExc)
    {
        GWT.log("Page update failed", anExc);
    }
}
