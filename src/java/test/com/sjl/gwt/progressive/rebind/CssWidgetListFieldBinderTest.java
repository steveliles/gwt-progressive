package com.sjl.gwt.progressive.rebind;

import org.jmock.*;
import org.jmock.lib.legacy.*;
import org.junit.*;

import com.google.gwt.core.ext.typeinfo.*;
import com.google.gwt.user.rebind.*;
import com.sjl.gwt.progressive.client.widgets.*;

public class CssWidgetListFieldBinderTest
{
    private Mockery ctx;
    private TypeOracle oracle;
    private JClassType owner;
    private JClassType listElement;
    private JClassType boundPanel;
    private SourceWriter writer;
    private FieldInfo.WidgetField fieldInfo;
    
    @Before
    public void setup()
    {
        ctx = new Mockery();
        ctx.setImposteriser(ClassImposteriser.INSTANCE);
        
        oracle = ctx.mock(TypeOracle.class);
        fieldInfo = ctx.mock(FieldInfo.WidgetField.class);
        owner = ctx.mock(JClassType.class, "owner");
        listElement = ctx.mock(JClassType.class, "list-element");
        boundPanel = ctx.mock(JClassType.class, "bound-panel");
        writer = ctx.mock(SourceWriter.class);
    }
    
    @Test
    public void canBindIfCssClassSuppliedWithNullTag()
    {
        ctx.checking(new Expectations() 
        {{ 
            atMost(1).of(fieldInfo).isWidgetField(); will(returnValue(true));
            allowing(fieldInfo).asWidgetField(); will(returnValue(fieldInfo));
            atMost(1).of(fieldInfo).isListField(); will(returnValue(true));
            atMost(1).of(fieldInfo).hasCssClass(); will(returnValue(true));
            atMost(1).of(fieldInfo).hasNoTag(); will(returnValue(true));
        }});
        
        FieldBinder _fb = new WidgetListFieldBinder.Css(oracle);
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
            atMost(1).of(fieldInfo).isListField(); will(returnValue(true));
            atMost(1).of(fieldInfo).hasCssClass(); will(returnValue(true));
            atMost(1).of(fieldInfo).hasNoTag(); will(returnValue(false));
        }});
        
        FieldBinder _fb = new WidgetListFieldBinder.Css(oracle);
        Assert.assertFalse(_fb.canBind(fieldInfo));
        
        ctx.assertIsSatisfied();
    }

    @Test
    public void cannotBindIfFieldIsNotList()
    {
        ctx.checking(new Expectations() 
        {{ 
            atMost(1).of(fieldInfo).isWidgetField(); will(returnValue(true));
            allowing(fieldInfo).asWidgetField(); will(returnValue(fieldInfo));
            atMost(1).of(fieldInfo).isListField(); will(returnValue(false));
            atMost(1).of(fieldInfo).hasCssClass(); will(returnValue(true));
            atMost(1).of(fieldInfo).hasNoTag(); will(returnValue(true));         
        }});
        
        FieldBinder _fb = new WidgetListFieldBinder.Css(oracle);
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
            atMost(1).of(fieldInfo).isListField(); will(returnValue(true));
            atMost(1).of(fieldInfo).hasCssClass(); will(returnValue(false));
            atMost(1).of(fieldInfo).hasNoTag(); will(returnValue(true));      
        }});
        
        FieldBinder _fb = new WidgetListFieldBinder.Css(oracle);
        Assert.assertFalse(_fb.canBind(fieldInfo));
        
        ctx.assertIsSatisfied();
    }
    
    @Test
    public void cannotBindIfNotRuntimeUiWidgetBinding()
    {
        ctx.checking(new Expectations() 
        {{ 
            atMost(1).of(fieldInfo).isWidgetField(); will(returnValue(true));
            allowing(fieldInfo).asWidgetField(); will(returnValue(fieldInfo));
            atMost(1).of(fieldInfo).isListField(); will(returnValue(true));
            atMost(1).of(fieldInfo).hasCssClass(); will(returnValue(false));
            atMost(1).of(fieldInfo).hasNoTag(); will(returnValue(true));     
        }});
        
        FieldBinder _fb = new WidgetListFieldBinder.Css(oracle);
        Assert.assertFalse(_fb.canBind(fieldInfo));
        
        ctx.assertIsSatisfied();
    }    
    
    @Test
    public void producesAppropriateBindingCodeForWrappingWidgets()
    {
        ctx.checking(new Expectations()
        {{
            allowing(fieldInfo).isWidgetField(); will(returnValue(true));
            allowing(fieldInfo).asWidgetField(); will(returnValue(fieldInfo));
            allowing(fieldInfo).getListElementType(); will(returnValue(listElement));
            allowing(fieldInfo).getCssClass(); will(returnValue("css"));
            allowing(fieldInfo).getName(); will(returnValue("field"));            
            allowing(fieldInfo).hasWrapElementMethod(); will(returnValue(true));
            
            allowing(listElement).getQualifiedSourceName(); will(returnValue("com.kv.SomeType"));
            
            allowing(oracle).findType(BoundPanel.class.getCanonicalName()); will(returnValue(boundPanel));
            allowing(owner).isAssignableTo(boundPanel); will(returnValue(true));
            
            allowing(fieldInfo).isAttachable(); will(returnValue(true));            
            ignoring(writer).println(); ignoring(writer).indent(); ignoring(writer).outdent();
            
            oneOf(writer).println("w.field = new ArrayList<com.kv.SomeType>();");
            oneOf(writer).println("for (Element _e : Elements.getByCssClass(e, \"css\")) {");            
            oneOf(writer).println("w.field.add(com.kv.SomeType.wrap(_e));");            
            oneOf(writer).println("}");
            oneOf(writer).println("for (Widget _w : w.field) {");            
            oneOf(writer).println("w.add(_w, false);");
            oneOf(writer).println("((Attachable)_w).onAttach();");            
            oneOf(writer).println("}");            
        }});
        
        FieldBinder _fb = new WidgetListFieldBinder.Css(oracle);
        _fb.bind(owner, fieldInfo, writer);
        
        ctx.assertIsSatisfied();
    }
}
