package com.sjl.gwt.progressive.rebind;

import java.io.*;
import java.util.*;

import com.google.gwt.core.ext.*;
import com.google.gwt.core.ext.typeinfo.*;
import com.google.gwt.dom.client.*;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.rebind.*;
import com.sjl.gwt.progressive.client.*;
import com.sjl.gwt.progressive.client.util.*;
import com.sjl.gwt.progressive.client.widgets.*;

public class ActivatorGenerator extends Generator
{
    private static final String PKG_NAME = "com.sjl.gwt.progressive.client.";    
    
    private FieldInfoFactory fieldInfoFactory;
    private FieldBinderFactory fieldBinderFactory;
    
    @Override
    public String generate(TreeLogger aLogger, GeneratorContext aContext, String aTypeName) throws UnableToCompleteException
    {                
        TypeOracle _oracle = aContext.getTypeOracle();
                
        fieldInfoFactory = new FieldInfoFactory(_oracle);
        fieldBinderFactory = new PreparedFieldBinderFactory(_oracle);        
        
        JClassType _type;
        try
        {
            _type = _oracle.getType(aTypeName);
        } 
        catch (NotFoundException anExc)
        {
            throw new RuntimeException(anExc);
        }

        if (_type.isInterface() == null)
        {
            // The incoming type wasn't a plain interface, we don't support abstract base classes
            aLogger.log(TreeLogger.ERROR, _type.getQualifiedSourceName() + " is not an interface.", null);
            throw new UnableToCompleteException();
        }

        String _implName = _type.getName().replace('.', '_') + "Impl";
        String _packageName = _type.getPackage().getName();
        String _resultName = _packageName + "." + _implName;
        
        PrintWriter _out = aContext.tryCreate(aLogger, _packageName, _implName);
        
        if (_out != null)
        {
            PrintWriter _dbg = null;
            try
            {
                File _f = File.createTempFile("gwt", "activator");
                _dbg = new PrintWriter(new File(_f.getParentFile(), _resultName));
                _f.delete();
                
                generate(aLogger, aContext, _oracle, _type, _implName, _packageName, _resultName, _dbg, true);
            }
            catch (Exception anExc)
            {
                throw new RuntimeException(anExc);
            }
        }
        
        return generate(aLogger, aContext, _oracle, _type, _implName, _packageName, _resultName, _out, true);
    }

    protected String generate(TreeLogger aLogger, GeneratorContext aContext, TypeOracle _oracle, JClassType _type, String _implName, String _packageName, String _resultName, PrintWriter aWriter, boolean aCommit)
    {
        // Nothing to do if an implementation already exists
        if (aWriter != null)
        {
            ClassSourceFileComposerFactory _f = new ClassSourceFileComposerFactory(_packageName, _implName);
            generateClass(_oracle, aLogger, aContext, _type, _f, aWriter, aCommit);
        }
        return _resultName;
    }

    private void generateClass(TypeOracle anOracle, TreeLogger aLogger, GeneratorContext aContext, JClassType aType, ClassSourceFileComposerFactory aFactory, PrintWriter aWriter, boolean aCommit)
    {
        // add imports
        aFactory.addImport(RootPanel.class.getName());
        aFactory.addImport(Element.class.getName());
        aFactory.addImport(Widget.class.getName());
        aFactory.addImport(Image.class.getName());
        
        aFactory.addImport(List.class.getName());        
        aFactory.addImport(ArrayList.class.getName());

        aFactory.addImport(Replacer.class.getName());
        aFactory.addImport(PageUpdateCallback.class.getName());
        aFactory.addImport(Elements.class.getName());
        aFactory.addImport(Attachable.class.getName());
        aFactory.addImport(BoundRootPanel.class.getName());
        aFactory.addImport(BoundPanel.class.getName());
        aFactory.addImport(BoundWidget.class.getName());
        aFactory.addImport(BoundComposite.class.getName());

        JClassType _activatorType = deduceActivatorType(aType);
        JClassType _ownerType = deduceOwnerType(aType, _activatorType);
        ensureActivationTargetIsOwnerType(aType, _activatorType, _ownerType);
        
        for (JField _f : _ownerType.getFields())
        {
            FieldInfo _fi = fieldInfoFactory.get(_f);
            if (_fi.isWidgetField() && _fi.asWidgetField().isListField())
            {
                aFactory.addImport(_fi.asWidgetField().getListElementType().getQualifiedSourceName());
            }
            else
            {
                aFactory.addImport(_f.getType().getQualifiedSourceName());
            }            
        }
        
        // implement interfaces
        aFactory.addImplementedInterface(aType.getQualifiedSourceName());

        // create the class
        SourceWriter _sw = aFactory.createSourceWriter(aContext, aWriter);
        createReplacerDelegate(anOracle, _sw, _ownerType);
        createActivateMethod(anOracle, aLogger, _sw, _activatorType, _ownerType);
        createReplaceMethods(_sw, _ownerType);
        
        if (aCommit)
        {
            _sw.commit(aLogger);
        }
    }
    
    private void createReplacerDelegate(TypeOracle anOracle, SourceWriter aWriter, JClassType aType)
    {
        aWriter.println(String.format("Replacer delegate = new %s() {", getReplacerTypeName(anOracle, aType)));
        aWriter.indent();
        
        aWriter.println(String.format("public %s newWidgetInstance(Element anElement) {", aType.getQualifiedSourceName()));
        aWriter.indent();
        aWriter.println(String.format("return new %s(anElement);", aType.getQualifiedSourceName()));
        aWriter.outdent();
        aWriter.println("}");
        
        aWriter.outdent();
        aWriter.println("};");
    }
        
    private String getReplacerTypeName(TypeOracle anOracle, JClassType aType)
    {
        if (aType.isAssignableTo(anOracle.findType(PKG_NAME + "widgets.BoundRootPanel")))
        {
            return String.format(PKG_NAME + "AbstractBoundRootPanelReplacer<%s>", aType.getQualifiedSourceName());
        }
        
        if (aType.isAssignableTo(anOracle.findType(PKG_NAME + "widgets.BoundPanel")))
        {
            return String.format(PKG_NAME + "AbstractBoundPanelReplacer<%s>", aType.getQualifiedSourceName());
        }
        
        return String.format(PKG_NAME + "AbstractWidgetReplacer<%s>", aType.getQualifiedSourceName()); 
    }
    
    private void createReplaceMethods(SourceWriter aWriter, JClassType aType)
    {
        aWriter.indent();
        aWriter.println(String.format("public void update(%s w, Element e) {", aType.getName()));
        aWriter.indent();

        aWriter.println("delegate.update(w, e); ");

        aWriter.outdent();
        aWriter.println("}");
        aWriter.outdent();
        
        aWriter.println();

        aWriter.indent();
        aWriter.println(String.format("public void update(%1$s w, Element e, PageUpdateCallback<%1$s> c) {", aType.getName()));
        aWriter.indent();

        aWriter.println("delegate.update(w, e, c); ");

        aWriter.outdent();
        aWriter.println("}");
        aWriter.outdent();
        
        aWriter.println();

        aWriter.indent();
        aWriter.println(String.format("public void update(%s w, String u) {", aType.getName()));
        aWriter.indent();

        aWriter.println("delegate.update(w, u); ");

        aWriter.outdent();
        aWriter.println("}");
        aWriter.outdent();
        
        aWriter.indent();
        aWriter.println(String.format("public void update(%1$s w, String u, PageUpdateCallback<%1$s> c) {", aType.getName()));
        aWriter.indent();

        aWriter.println("delegate.update(w, u, c); ");

        aWriter.outdent();
        aWriter.println("}");
        aWriter.outdent();
    }
    
    private void ensureActivationTargetIsOwnerType(JClassType aType, JClassType anActivatorType, JClassType anOwnerType)
    {
        JClassType _targetType = anActivatorType.isParameterized().getTypeArgs()[0];
        
        if (!_targetType.equals(anOwnerType))
        {
            throw new RuntimeException(String.format("Expected %s<%s>, but got %s<%s>, check your parameterization of %s", aType.getSimpleSourceName(), anOwnerType.getSimpleSourceName(), aType.getSimpleSourceName(), _targetType.getSimpleSourceName(), aType.getQualifiedSourceName()));
        }
    }

    private JClassType deduceActivatorType(JClassType aType)
    {
        JClassType[] _activatorTypes = aType.getImplementedInterfaces();
        if (_activatorTypes.length == 0)
        {
            throw new RuntimeException("No implemented interfaces for " + aType.getName());
        }
        return _activatorTypes[0];
    }

    private JClassType deduceOwnerType(JClassType aType, JClassType anActivatorType)
    {
        JClassType[] _typeArgs = anActivatorType.isParameterized().getTypeArgs();
        if (_typeArgs.length != 1)
        {
            throw new RuntimeException("Owner type parameter is required for type %s" + aType.getName());
        }
        
        if (aType.isMemberType())
        {
            return aType.getEnclosingType();
        }
        else
        {
            return _typeArgs[0];
        }
    }

    private void createActivateMethod(TypeOracle anOracle, TreeLogger aLogger, SourceWriter aWriter, JClassType anActivatorType, JClassType anOwnerType)
    {
        aWriter.indent();
        aWriter.println(String.format("public %s activate(%s w, final Element e) {", getReturnType(anActivatorType), anOwnerType.getName()));
        aWriter.indent();

        aWriter.println("if (e == null) return null;");        
        
        createDynamicBindingCodeForAnnotatedFields(anOwnerType, aWriter);

        if ("ElementActivator".equals(anActivatorType.getName()))
        {
            aWriter.println("return e; ");
        }
        else
        {
            aWriter.println("return new Widget(){{ setElement(e); }};");
        }
        aWriter.outdent();
        aWriter.println("}");
        aWriter.println();
        aWriter.outdent();
    }

    private String getReturnType(JClassType anActivatorType)
    {
        JMethod _m = anActivatorType.getMethods()[0];
        return _m.getReturnType().getSimpleSourceName();
    }
    
    private void createDynamicBindingCodeForAnnotatedFields(JClassType anOwner, SourceWriter aWriter)
    {
        for (JField _f : anOwner.getFields())
        {
            FieldInfo _fi = fieldInfoFactory.get(_f);
            if (fieldBinderFactory.hasPotentialBinders(_fi))
            {
                FieldBinder _binder = fieldBinderFactory.getBinder(_fi);
                _binder.bind(anOwner, _fi, aWriter);
            }
        }
    }

}
