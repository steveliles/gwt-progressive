package com.sjl.gwt.progressive.client.bootstrap;

import com.google.gwt.dom.client.*;
import com.google.gwt.user.client.ui.*;

public interface Activation extends Comparable<Activation>
{
    public boolean matches(Element anElement);
    
    public void activate(Element anElement);
    
    public void attachIfAttachable(Widget aWidget);

    public String getActivatedClassName();
}
