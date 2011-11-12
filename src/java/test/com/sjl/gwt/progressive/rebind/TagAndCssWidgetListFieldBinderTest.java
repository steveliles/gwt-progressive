package com.sjl.gwt.progressive.rebind;

import org.jmock.*;
import org.jmock.lib.legacy.*;
import org.junit.*;

import com.google.gwt.core.ext.typeinfo.*;

public class TagAndCssWidgetListFieldBinderTest
{
    private Mockery ctx;
    private TypeOracle oracle;
    private FieldInfo.WidgetField fieldInfo;
    
    @Before
    public void setup()
    {
        ctx = new Mockery();
        ctx.setImposteriser(ClassImposteriser.INSTANCE);
        
        oracle = ctx.mock(TypeOracle.class);
        fieldInfo = ctx.mock(FieldInfo.WidgetField.class);
    }
    
    @Test
    public void canBindIfTagAndCssSupplied()
    {
        ctx.checking(new Expectations() 
        {{ 
            atMost(1).of(fieldInfo).isWidgetField(); will(returnValue(true));
            allowing(fieldInfo).asWidgetField(); will(returnValue(fieldInfo));
            atMost(1).of(fieldInfo).isListField(); will(returnValue(true));
            atMost(1).of(fieldInfo).hasCssClass(); will(returnValue(true));
            atMost(1).of(fieldInfo).hasTag(); will(returnValue(true));
        }});
        
        FieldBinder _fb = new WidgetListFieldBinder.TagAndCss(oracle);
        Assert.assertTrue(_fb.canBind(fieldInfo));
        
        ctx.assertIsSatisfied();
    }

    @Test
    public void cannotBindIfNoTagSupplied()
    {
        ctx.checking(new Expectations() 
        {{ 
            atMost(1).of(fieldInfo).isWidgetField(); will(returnValue(true));
            allowing(fieldInfo).asWidgetField(); will(returnValue(fieldInfo));
            atMost(1).of(fieldInfo).isListField(); will(returnValue(true));
            atMost(1).of(fieldInfo).hasCssClass(); will(returnValue(true));
            atMost(1).of(fieldInfo).hasTag(); will(returnValue(false));
        }});
        
        FieldBinder _fb = new WidgetListFieldBinder.TagAndCss(oracle);
        Assert.assertFalse(_fb.canBind(fieldInfo));
        
        ctx.assertIsSatisfied();
    }
    
    @Test
    public void cannotBindIfNoCssSupplied()
    {
        ctx.checking(new Expectations() 
        {{ 
            atMost(1).of(fieldInfo).isWidgetField(); will(returnValue(true));
            allowing(fieldInfo).asWidgetField(); will(returnValue(fieldInfo));
            atMost(1).of(fieldInfo).isListField(); will(returnValue(true));
            atMost(1).of(fieldInfo).hasCssClass(); will(returnValue(false));
            atMost(1).of(fieldInfo).hasTag(); will(returnValue(true));
        }});
        
        FieldBinder _fb = new WidgetListFieldBinder.TagAndCss(oracle);
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
            atMost(1).of(fieldInfo).hasTag(); will(returnValue(true));        
        }});
        
        FieldBinder _fb = new WidgetListFieldBinder.TagAndCss(oracle);
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
            atMost(1).of(fieldInfo).isListField(); will(returnValue(true));
            atMost(1).of(fieldInfo).hasCssClass(); will(returnValue(true));
            atMost(1).of(fieldInfo).hasTag(); will(returnValue(true));     
        }});
        
        FieldBinder _fb = new WidgetListFieldBinder.TagAndCss(oracle);
        Assert.assertFalse(_fb.canBind(fieldInfo));
        
        ctx.assertIsSatisfied();
    }  
}
