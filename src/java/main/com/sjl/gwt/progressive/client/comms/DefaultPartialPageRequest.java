package com.sjl.gwt.progressive.client.comms;

import com.google.gwt.dom.client.Element;
import com.google.gwt.http.client.*;
import com.google.gwt.user.client.*;

public class DefaultPartialPageRequest implements PartialPageRequest
{    
    private String url;
    
    public DefaultPartialPageRequest(String aUrl)
    {
        url = aUrl;
    }
    
    @Override
    public void execute(final Callback aCallback)
    {
        RequestBuilder _builder = new RequestBuilder(RequestBuilder.GET, url);
        _builder.setHeader("Accept", "text/html+partial");
        try
        {
            _builder.sendRequest("", new RequestCallback()
            {
                @Override
                public void onResponseReceived(Request aRequest, Response aResponse)
                {                
                    try
                    {
                        Element _root = DOM.createDiv();
                        _root.setInnerHTML(aResponse.getText());
                        
                        aCallback.onSuccess(_root.getFirstChildElement());
                    }
                    catch (Exception anExc)
                    {
                        aCallback.onError(anExc);
                    }
                }                        

                @Override
                public void onError(Request aRequest, Throwable anExc)
                {
                    aCallback.onError(anExc);
                }                    
            });
        }
        catch (Exception anExc)
        {
            aCallback.onError(anExc);            
        }
    }    
}
