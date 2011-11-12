package com.sjl.gwt.progressive.rebind;

import org.jmock.*;
import org.jmock.lib.legacy.*;
import org.junit.*;

import com.google.gwt.core.ext.typeinfo.*;
import com.google.gwt.user.rebind.*;
import com.sjl.gwt.progressive.client.widgets.*;

public class CssWidgetFieldBinderTest
{
    private Mockery ctx;
    private TypeOracle oracle;
    private FieldInfo.WidgetField fieldInfo;
    private SourceWriter writer;
    private JClassType ownerClass;
    private JClassType boundPanelClass;
    
    @Before
    public void setup()
    {
        ctx = new Mockery();
        ctx.setImposteriser(ClassImposteriser.INSTANCE);
        
        oracle = ctx.mock(TypeOracle.class);
        fieldInfo = ctx.mock(FieldInfo.WidgetField.class);
        writer = ctx.mock(SourceWriter.class);
        
        ownerClass = ctx.mock(JClassType.class, "owner");
        boundPanelClass = ctx.mock(JClassType.class, "boundPanel");
    }
    
    @Test
    public void canBindIfCssClassSuppliedWithNullTag()
    {
        ctx.checking(new Expectations() 
        {{ 
            atMost(1).of(fieldInfo).isWidgetField(); will(returnValue(true));
            allowing(fieldInfo).asWidgetField(); will(returnValue(fieldInfo));
            atMost(1).of(fieldInfo).isSimpleField(); will(returnValue(true));
            atMost(1).of(fieldInfo).hasCssClass(); will(returnValue(true));
            atMost(1).of(fieldInfo).hasNoTag(); will(returnValue(true));
        }});
        
        FieldBinder _fb = new WidgetFieldBinder.Css(oracle);
        Assert.assertTrue(_fb.canBind(fieldInfo));
        
        ctx.assertIsSatisfied();
    }

    @Test
    public void cannotBindIfCssClassSuppliedWithNonNullTag()
    {
        ctx.checking(new Expectations() 
        {{ 
            atMost(1).of(fieldInfo).isWidgetField(); will(returnValue(true));
            allowing(fieldInfo).asWidgetField(); will(returnValue(fieldInfo));
            atMost(1).of(fieldInfo).isSimpleField(); will(returnValue(true));
            atMost(1).of(fieldInfo).hasCssClass(); will(returnValue(true));
            atMost(1).of(fieldInfo).hasNoTag(); will(returnValue(false));
        }});
        
        FieldBinder _fb = new WidgetFieldBinder.Css(oracle);
        Assert.assertFalse(_fb.canBind(fieldInfo));
        
        ctx.assertIsSatisfied();
    }

    @Test
    public void cannotBindIfFieldIsList()
    {
        ctx.checking(new Expectations() 
        {{ 
            atMost(1).of(fieldInfo).isWidgetField(); will(returnValue(true));
            allowing(fieldInfo).asWidgetField(); will(returnValue(fieldInfo));
            atMost(1).of(fieldInfo).isSimpleField(); will(returnValue(false));
            atMost(1).of(fieldInfo).hasCssClass(); will(returnValue(true));
            atMost(1).of(fieldInfo).hasNoTag(); will(returnValue(true));         
        }});
        
        FieldBinder _fb = new WidgetFieldBinder.Css(oracle);
        Assert.assertFalse(_fb.canBind(fieldInfo));
        
        ctx.assertIsSatisfied();
    }
    
    @Test
    public void cannotBindIfCssIsNullOrEmpty()
    {
        ctx.checking(new Expectations() 
        {{ 
            atMost(1).of(fieldInfo).isWidgetField(); will(returnValue(true));
            allowing(fieldInfo).asWidgetField(); will(returnValue(fieldInfo));
            atMost(1).of(fieldInfo).isSimpleField(); will(returnValue(true));
            atMost(1).of(fieldInfo).hasCssClass(); will(returnValue(false));
            atMost(1).of(fieldInfo).hasNoTag(); will(returnValue(true));      
        }});
        
        FieldBinder _fb = new WidgetFieldBinder.Css(oracle);
        Assert.assertFalse(_fb.canBind(fieldInfo));
        
        ctx.assertIsSatisfied();
    }
    
    @Test
    public void cannotBindIfNotRuntimeUiWidgetBinding()
    {
        ctx.checking(new Expectations() 
        {{ 
            atMost(1).of(fieldInfo).isWidgetField(); will(returnValue(false));
            allowing(fieldInfo).asWidgetField(); will(returnValue(fieldInfo));
            atMost(1).of(fieldInfo).isSimpleField(); will(returnValue(true));
            atMost(1).of(fieldInfo).hasCssClass(); will(returnValue(false));
            atMost(1).of(fieldInfo).hasNoTag(); will(returnValue(true));     
        }});
        
        FieldBinder _fb = new WidgetFieldBinder.Css(oracle);
        Assert.assertFalse(_fb.canBind(fieldInfo));
        
        ctx.assertIsSatisfied();
    }    
    
    @Test
    public void producesAppropriateBindingCodeForWrappingWidgets()
    {
        ctx.checking(new Expectations()
        {{
            allowing(fieldInfo).asWidgetField(); will(returnValue(fieldInfo));
            allowing(fieldInfo).hasWrapElementMethod(); will(returnValue(true));
            
            allowing(fieldInfo).getName(); will(returnValue("field"));            
            allowing(fieldInfo).getCssClass(); will(returnValue("css"));
            allowing(fieldInfo).getQualifiedSourceName(); will(returnValue("com.kv.WidgetType"));
            
            oneOf(writer).println("Element _fieldElement = Elements.getFirstByCssClass(e, \"css\");");
            oneOf(writer).println("if (_fieldElement != null) {");
            oneOf(writer).indent();            
            oneOf(writer).println("w.field = com.kv.WidgetType.wrap(_fieldElement);");
            oneOf(writer).outdent();
            oneOf(writer).println("}");
        }});
        
        FieldBinder _fb = new WidgetFieldBinder.Css(oracle);
        _fb.bind(null, fieldInfo, writer);
        
        ctx.assertIsSatisfied();
    }
    
    @Test
    public void producesAppropriateBindingCodeForNewingWidgets()
    {
        ctx.checking(new Expectations()
        {{
            allowing(fieldInfo).isWidgetField(); will(returnValue(true));
            allowing(fieldInfo).asWidgetField(); will(returnValue(fieldInfo));
            allowing(fieldInfo).hasWrapElementMethod(); will(returnValue(false));
            
            allowing(oracle).findType(BoundPanel.class.getCanonicalName()); will(returnValue(boundPanelClass));
            allowing(ownerClass).isAssignableTo(boundPanelClass); will(returnValue(true));
            allowing(fieldInfo).isAttachable(); will(returnValue(true));
            
            allowing(fieldInfo).getName(); will(returnValue("field"));            
            allowing(fieldInfo).getCssClass(); will(returnValue("css"));
            allowing(fieldInfo).getQualifiedSourceName(); will(returnValue("com.kv.WidgetType"));
            
            oneOf(writer).println("Element _fieldElement = Elements.getFirstByCssClass(e, \"css\");");
            oneOf(writer).println("if (_fieldElement != null) {");
            oneOf(writer).indent();            
            oneOf(writer).println("w.field = new com.kv.WidgetType(_fieldElement);");            
            oneOf(writer).println("w.add(w.field, false);");
            oneOf(writer).println("((Attachable)w.field).onAttach();");
            oneOf(writer).outdent();
            oneOf(writer).println("}");
        }});
        
        FieldBinder _fb = new WidgetFieldBinder.Css(oracle);
        _fb.bind(ownerClass, fieldInfo, writer);
        
        ctx.assertIsSatisfied();
    }
}
