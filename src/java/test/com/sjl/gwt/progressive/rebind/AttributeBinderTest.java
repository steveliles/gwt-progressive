package com.sjl.gwt.progressive.rebind;

import org.jmock.*;
import org.jmock.lib.legacy.*;
import org.junit.*;

import com.google.gwt.user.rebind.*;

public class AttributeBinderTest
{
    private Mockery ctx;
    
    private AttributeConverterLookup lookup;    
    private SourceWriter writer;    
    private FieldInfo.AttributeField fieldInfo;
    
    @Before
    public void setup()
    {
        ctx = new Mockery();
        ctx.setImposteriser(ClassImposteriser.INSTANCE);
        
        fieldInfo = ctx.mock(FieldInfo.AttributeField.class);
        
        lookup = ctx.mock(AttributeConverterLookup.class);
        writer = ctx.mock(SourceWriter.class);        
    }

    @Test
    public void canBindIfAnnotationSuppliesAttributeName()
    {
        ctx.checking(new Expectations()
        {{
            atMost(1).of(fieldInfo).isAttributeField(); will(returnValue(true));
            atMost(1).of(fieldInfo).asAttributeField(); will(returnValue(fieldInfo));
            atMost(1).of(fieldInfo).hasAttributeName(); will(returnValue(true));            
        }});
        
        AttributeFieldBinder _binder = new AttributeFieldBinder(lookup);
        Assert.assertTrue(_binder.canBind(fieldInfo));            
        
        ctx.assertIsSatisfied();
    }
    
    @Test
    public void cannotBindIfAnnotationIsNotRuntimeUiAttribute()
    {
        ctx.checking(new Expectations()
        {{
            atMost(1).of(fieldInfo).isAttributeField(); will(returnValue(false));
            atMost(1).of(fieldInfo).asAttributeField(); will(returnValue(fieldInfo));
            atMost(1).of(fieldInfo).hasAttributeName(); will(returnValue(true));
        }});
        
        AttributeFieldBinder _binder = new AttributeFieldBinder(lookup);
        Assert.assertFalse(_binder.canBind(fieldInfo));            
        
        ctx.assertIsSatisfied();
    }
    
    @Test
    public void cannotBindIfAnnotationDoesNotSupplyAttributeName()
    {
        ctx.checking(new Expectations()
        {{
            atMost(1).of(fieldInfo).isAttributeField(); will(returnValue(true));
            atMost(1).of(fieldInfo).asAttributeField(); will(returnValue(fieldInfo));
            atMost(1).of(fieldInfo).hasAttributeName(); will(returnValue(false));            
        }});
        
        AttributeFieldBinder _binder = new AttributeFieldBinder(lookup);
        Assert.assertFalse(_binder.canBind(fieldInfo));            
        
        ctx.assertIsSatisfied();
    }
    
    @Test
    public void generatesCodeToConvertAndBindHtmlAttributeValuesAtRuntime()
    {
        ctx.checking(new Expectations()
        {{
            oneOf(fieldInfo).asAttributeField(); will(returnValue(fieldInfo));
            oneOf(fieldInfo).getQualifiedSourceName(); will(returnValue("com.kv.SomeTargetClass"));
            oneOf(lookup).getAttributeConverterClass("com.kv.SomeTargetClass"); will(returnValue("com.kv.SomeConverterClass"));            
            
            oneOf(fieldInfo).getName(); will(returnValue("field"));
            oneOf(fieldInfo).getAttributeName(); will(returnValue("attr"));            
            
            oneOf(writer).println("w.field = new com.kv.SomeConverterClass().get(e, \"attr\", w.field);");
        }});
        
        AttributeFieldBinder _binder = new AttributeFieldBinder(lookup);        
        _binder.bind(null, fieldInfo, writer);
        
        ctx.assertIsSatisfied();
    }
}
