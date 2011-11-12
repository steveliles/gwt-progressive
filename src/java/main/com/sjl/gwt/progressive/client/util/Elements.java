package com.sjl.gwt.progressive.client.util;

import java.util.*;

import com.google.gwt.dom.client.*;
import com.google.gwt.user.client.ui.*;

public class Elements
{
    public interface Predicate
    {
        public boolean accept(Element anElement);
    }
    
    public interface ElementVisitor
    {
        /**
         * @param anElement
         * @return true if visiting should continue by recursing within the element,
         * false to skip to next sibling element.
         */
        public boolean with(Element anElement);
    }
    
    public static void each(Element aStartingElement, ElementVisitor aVisitor)
    {
        NodeList<Node> _children = aStartingElement.getChildNodes();
        for (int i = 0; i < _children.getLength(); i++)
        {
            Node _n = _children.getItem(i);
            if (Element.is(_n))
            {
                Element _e = (Element) Element.as(_n);
                if (aVisitor.with(_e))
                {
                    each(_e, aVisitor);
                } 
            }
        }        
    }

    public static List<Element> getChildren(Element anElement)
    {
        return collectElements(anElement, new Predicate()
        {
            public boolean accept(Element anElement)
            {
                return true;
            }
        });
    }

    public static Element getFirstById(Element aParent, final String anId)
    {
        return getFirstMatch(aParent, new Predicate()
        {
            public boolean accept(Element anElement)
            {
                return anId.equals(anElement.getId());
            }
        });
    }
    
    public static Element getFirstByCssClass(Element aParent, final String aCssClass)
    {
        return getFirstMatch(aParent, new Predicate()
        {
            public boolean accept(Element anElement)
            {
                return Strings.includes(aCssClass, anElement.getClassName());
            }
        });
    }

    public static List<Element> getByCssClass(String aClassName)
    {
        return getByCssClass(RootPanel.get().getElement(), aClassName);
    }

    public static List<Element> getByCssClass(Element aParent, final String aClassName)
    {
        return collectElements(aParent, new Predicate()
        {
            public boolean accept(Element anElement)
            {
                return Strings.includes(aClassName, anElement.getClassName());
            }
        });
    }

    public static Element getFirstByTagName(Element aParent, String aTagName)
    {
        final String _tagName = aTagName.toUpperCase();
        return getFirstMatch(aParent, new Predicate()
        {
            public boolean accept(Element anElement)
            {
                return anElement.getTagName().equals(_tagName);
            }
        });
    }

    public static List<Element> getByTagName(Element aParent, String aTagName)
    {
        final String _tagName = aTagName.toUpperCase();
        return collectElements(aParent, new Predicate()
        {
            public boolean accept(Element anElement)
            {
                return anElement.getTagName().equals(_tagName);
            }
        });
    }

    public static Element getFirstByTagNameAndCssClass(Element aParent, String aTagName, final String aCssClass)
    {
        final String _tagName = aTagName.toUpperCase();
        return getFirstMatch(aParent, new Predicate()
        {
            public boolean accept(Element anElement)
            {
                return (anElement.getTagName().equals(_tagName)) && (Strings.includes(aCssClass, anElement.getClassName()));
            }
        });
    }
    
    public static List<Element> getByTagNameAndCssClass(Element aParent, String aTagName, final String aClassName)
    {
        final String _tagName = aTagName.toUpperCase();
        return collectElements(aParent, new Predicate()
        {
            public boolean accept(Element anElement)
            {
                return (anElement.getTagName().equals(_tagName)) && (Strings.includes(aClassName, anElement.getClassName()));
            }
        });
    }

    // breadth-first search for first matching element
    private static Element getFirstMatch(Element aParent, Predicate aPredicate)
    {
        NodeList<Node> _children = aParent.getChildNodes();
        for (int i = 0; i < _children.getLength(); i++)
        {
            Node _n = _children.getItem(i);
            if (Element.is(_n))
            {
                Element _e = (Element) Element.as(_n);
                if (aPredicate.accept(_e))
                {
                    return _e;
                }
            }
        }
        // not found at current level, drill down
        for (int i = 0; i < _children.getLength(); i++)
        {
            Node _n = _children.getItem(i);
            if (Element.is(_n))
            {
                Element _e = getFirstMatch((Element) Element.as(_n), aPredicate);
                if (_e != null)
                {
                    return _e;
                }
            }
        }
        return null;
    }

    private static List<Element> collectElements(Element aParent, Predicate aPredicate)
    {
        List<Element> _result = new ArrayList<Element>();
        NodeList<Node> _children = aParent.getChildNodes();
        for (int i = 0; i < _children.getLength(); i++)
        {
            Node _n = _children.getItem(i);
            if (Element.is(_n))
            {
                Element _e = (Element) Element.as(_n);
                if (aPredicate.accept(_e))
                {
                    _result.add(_e);
                } 
                else
                {
                    _result.addAll(collectElements(_e, aPredicate));
                }
            }
        }
        return _result;
    }        

}
