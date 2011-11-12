package com.sjl.gwt.progressive.client;

/**
 * Interface known to the GWT-Activator generator that allows it to invoke onAttach on
 * your behalf to correctly engage your widgets with the GWT system so that they can
 * participate in the eventing system. 
 * 
 * If, for whatever reason, you choose not to implement this interface you should invoke 
 * onAttach yourself at the earliest opportunity. 
 * 
 * Implement Attachable if you are extending other GWT widgets that do not themselves
 * extend BoundComposite or BoundWidget.
 * 
 * @author steve
 */
public interface Attachable
{
    public void onAttach();
}
