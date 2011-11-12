package com.sjl.gwt.progressive.rebind;

import java.io.*;
import java.util.*;

import com.google.gwt.core.ext.*;
import com.google.gwt.core.ext.TreeLogger.Type;
import com.google.gwt.core.ext.typeinfo.*;
import com.google.gwt.dom.client.*;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.rebind.*;
import com.sjl.gwt.progressive.client.*;
import com.sjl.gwt.progressive.client.bootstrap.*;

public class PageActivatorGenerator extends Generator
{

    @Override
    public String generate(TreeLogger aLogger, GeneratorContext aContext, String aTypeName) 
    throws UnableToCompleteException
    {        
        JClassType _type = aContext.getTypeOracle().findType(aTypeName);
        
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
                
                generate(aLogger, aContext, aTypeName, _implName, _packageName, _dbg);
            }
            catch (Exception anExc)
            {
                throw new RuntimeException(anExc);
            }
        }
        
        // Nothing to do if an implementation already exists
        if (_out != null)
        {
            generate(aLogger, aContext, aTypeName, _implName, _packageName, _out);
        }
        
        return _resultName;

    }

    private void generate(TreeLogger aLogger, GeneratorContext aContext, String aTypeName, String anImplName, String aPackageName, PrintWriter aWriter)
    {
        ClassSourceFileComposerFactory _f = new ClassSourceFileComposerFactory(aPackageName, anImplName);
        _f.addImplementedInterface(aTypeName);                       
        
        _f.addImport(Widget.class.getName());
        _f.addImport(RootPanel.class.getName());
        _f.addImport(Element.class.getName());            
        
        _f.addImport(Activator.class.getName());
        _f.addImport(IdActivation.class.getName());
        
        _f.addImport(TagOnlyActivation.class.getName());
        _f.addImport(TagAndCssClassActivation.class.getName());
        _f.addImport(CssClassOnlyActivation.class.getName());
        
        SourceWriter _sw = _f.createSourceWriter(aContext, aWriter);
        
        _sw.indent();
        _sw.println("public void activate() {");
        _sw.indent();
        _sw.println("Activator _a = new Activator();");
        
        for (JClassType _t : getClassesWithBindingAnnotation(aContext.getTypeOracle(), aLogger))
        {
            RootBinding _binding = _t.getAnnotation(RootBinding.class);
            
            if (hasId(_binding))
            {
                addIdActivation(_sw, _t, _binding);
            }
            else if (hasTag(_binding))
            {
                if (hasCssClass(_binding))
                {
                    addTagAndClassActivation(_sw, _t, _binding);
                }
                else
                {
                    addTagOnlyActivation(_sw, _t, _binding);                        
                }
            }
            else if (hasCssClass(_binding))
            {
                addCssOnlyActivation(_sw, _t, _binding);                    
            }
            else
            {
                aLogger.log(Type.ERROR, "Neither tag nor cssClass specified on class " + _t.getName() + ": your @RootBinding declaration is invalid.");
                throw new RuntimeException();
            }
        }
        
        _sw.println("_a.activate(RootPanel.getBodyElement());");
        _sw.outdent();
        _sw.println("}");
        _sw.outdent();
        
        _sw.commit(aLogger);
    }

    private void addIdActivation(SourceWriter aWriter, JClassType aClassType, RootBinding aBinding)
    {
        aWriter.println(String.format("_a.add(new IdActivation(\"%s\", \"%s\") {", aBinding.id(), aClassType.getSimpleSourceName()));
        activate(aWriter, aClassType);
    }
    
    private void addCssOnlyActivation(SourceWriter aWriter, JClassType aClassType, RootBinding aBinding)
    {
        aWriter.println(String.format("_a.add(new CssClassOnlyActivation(\"%s\", \"%s\") {", aBinding.cssClass(), aClassType.getSimpleSourceName()));
        activate(aWriter, aClassType);
    }

    private void addTagOnlyActivation(SourceWriter aWriter, JClassType aClassType, RootBinding aBinding)
    {
        aWriter.println(String.format("_a.add(new TagOnlyActivation(\"%s\", \"%s\") {", aBinding.tag().toUpperCase(), aClassType.getSimpleSourceName()));
        activate(aWriter, aClassType);
    }

    private void addTagAndClassActivation(SourceWriter aWriter, JClassType aClassType, RootBinding aBinding)
    {
        aWriter.println(String.format("_a.add(new TagAndCssClassActivation(\"%s\", \"%s\", \"%s\") {", aBinding.tag().toUpperCase(), aBinding.cssClass(), aClassType.getSimpleSourceName()));
        activate(aWriter, aClassType);
    }

    private void activate(SourceWriter aWriter, JClassType aClassType)
    {
        aWriter.indent();
        aWriter.println(String.format("public void activate(Element anElement) { attachIfAttachable(new %s(anElement)); }", aClassType.getQualifiedSourceName()));
        aWriter.outdent();
        aWriter.println("});\r\n");
    }

    private List<JClassType> getClassesWithBindingAnnotation(TypeOracle anOracle, TreeLogger aLogger)
    {
        List<JClassType> _result = new ArrayList<JClassType>();
        
        for (JPackage _p : anOracle.getPackages())
        {            
            for (JClassType _t : _p.getTypes())
            {                
                if (_t.isAnnotationPresent(RootBinding.class))
                {
                    aLogger.branch(Type.INFO, "creating @RootBinding activations for " + _t.getName());
                    _result.add(_t);
                }
            }            
        }
        
        return _result;
    }
    
    private boolean hasId(RootBinding anAnnotation)
    {
        return ((anAnnotation.id() != null) && (anAnnotation.id().trim().length() > 0));
    }
    
    private boolean hasTag(RootBinding anAnnotation)
    {
        return ((anAnnotation.tag() != null) && (anAnnotation.tag().trim().length() > 0));
    }

    private boolean hasCssClass(RootBinding anAnnotation)
    {
        return ((anAnnotation.cssClass() != null) && (anAnnotation.cssClass().trim().length() > 0));
    }
}
