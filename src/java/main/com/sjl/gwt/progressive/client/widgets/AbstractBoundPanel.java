package com.sjl.gwt.progressive.client.widgets;

import java.util.*;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.*;
import com.google.gwt.user.client.ui.*;
import com.sjl.gwt.progressive.client.*;

/**
 * Convenience class for creating Panel's that are bound with GWT-Activator. 
 * 
 * @author steve
 */
public abstract class AbstractBoundPanel extends Panel implements Attachable, BoundPanel
{    
    private WidgetCollection children = new WidgetCollection(this);        
    
    protected Element getContainer()
    {
        return getElement();
    }
    
    protected void doPhysicalAttach(Widget aWidget)
    {
        getContainer().appendChild(aWidget.getElement());
    }
    
    @Override
    protected void doAttachChildren()
    {
        // do nothing, we're already physically attached
    }
    
    @Override
    public void onAttach()
    {
        super.onAttach();
    }
    
    @Override
    public void add(Widget aChild)
    {
        add(aChild, true);
    }
    
    @Override
    public <T extends Widget> T replace(Widget aToReplace, WidgetFactory<T> aReplaceWith)
    {
        int _index = getChildren().indexOf(aToReplace);                       
        
        getContainer().replaceChild(aReplaceWith.getElement(), aToReplace.getElement());
        
        getChildren().remove(_index);
        
        T _newInstance = aReplaceWith.newWidgetInstance();
        getChildren().insert(_newInstance, _index);
        
        return _newInstance;
    }

    @Override
    public void add(Widget aChild, boolean anIsPhysicalAttach)
    {
        if (anIsPhysicalAttach)
        {
            aChild.removeFromParent();

            // Physical attach.
            doPhysicalAttach(aChild);
        }

        // Logical attach.
        getChildren().add(aChild);
        adopt(aChild);
    }
    
    @Override
    public boolean remove(Widget aWidget)
    {
        // Validate.
        if (aWidget.getParent() != this) {
          return false;
        }
        // Orphan.
        try {
          orphan(aWidget);
        } finally {
          // Physical detach.
          com.google.gwt.user.client.Element elem = aWidget.getElement();
          DOM.removeChild(DOM.getParent(elem), elem);
      
          // Logical detach.
          getChildren().remove(aWidget);
        }
        return true;
    }
    
    @Override
    public Iterator<Widget> iterator()
    {
        return getChildren().iterator();
    }       
    
    public WidgetCollection getChildren()
    {
        return children;
    }
}
