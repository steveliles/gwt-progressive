package com.sjl.gwt.progressive.client;

import com.google.gwt.dom.client.*;
import com.google.gwt.user.client.ui.*;

public interface Replacer<T extends Widget>
{       
    /**
     * bind the given element as a new instance of T, replacing the current
     * instance in the DOM and (if possible) in the parent widget hierarchy.
     */
    public void update(T aT, Element anElement, PageUpdateCallback<T> aCallback);
    public void update(T aT, Element anElement);
    
    /**
     * retrieve a partial page update from aUrl and bind the resulting html
     * as a new instance of T, replacing the current instance in the DOM and
     * (if possible) in the parent widget hierarchy.
     */
    public void update(T aT, String aUrl, PageUpdateCallback<T> aCallback);
    public void update(T aT, String aUrl);
}
