package com.sjl.gwt.progressive.rebind;

import org.jmock.*;
import org.jmock.lib.legacy.*;
import org.junit.*;

import com.google.gwt.core.ext.typeinfo.*;
import com.sjl.gwt.progressive.client.*;

public class CachingAttributeConverterLookupTest
{
    private Mockery ctx;
    
    private TypeOracle oracle;
    private JClassType targetType;
    private JClassType converterType;    
    private JClassType converterSuperClass;
    private JParameterizedType parameterizedConverterSuperClass;
    
    @Before
    public void setup()
    {
        ctx = new Mockery();        
        ctx.setImposteriser(ClassImposteriser.INSTANCE);
        
        oracle = ctx.mock(TypeOracle.class);
        
        targetType = ctx.mock(JClassType.class, "target");
        converterType = ctx.mock(JClassType.class, "converter");        
        converterSuperClass = ctx.mock(JClassType.class, "super");
        parameterizedConverterSuperClass = ctx.mock(JParameterizedType.class, "para");
    }    
    
    @Test
    public void findsConverterCapableOfConvertingStringsToTargetClass()
    {
        ctx.checking(new Expectations() 
        {{ 
            oneOf(oracle).getTypes(); will(returnValue(new JClassType[]{ converterType }));            
            oneOf(converterType).isAnnotationPresent(RuntimeUiAttributeConverter.class); will(returnValue(true));
            
            oneOf(converterType).getSuperclass(); will(returnValue(converterSuperClass));
            oneOf(converterSuperClass).isParameterized(); will(returnValue(parameterizedConverterSuperClass));
            oneOf(parameterizedConverterSuperClass).getTypeArgs(); will(returnValue(new JClassType[]{ targetType }));
            
            oneOf(targetType).getQualifiedSourceName(); will(returnValue("com.kv.SomeTargetType"));
            oneOf(converterType).getQualifiedSourceName(); will(returnValue("com.kv.SomeConverterType"));
        }});
        
        CachingAttributeConverterLookup _lookup = new CachingAttributeConverterLookup(oracle);
        Assert.assertEquals("com.kv.SomeConverterType", _lookup.getAttributeConverterClass("com.kv.SomeTargetType"));
        
        ctx.assertIsSatisfied();
    }
    
    public void throwsExceptionIfNoConverterAvailableForTargetType()
    {
        ctx.checking(new Expectations() 
        {{ 
            oneOf(oracle).getTypes(); will(returnValue(new JClassType[]{ }));            
        }});
        
        try
        {
            CachingAttributeConverterLookup _lookup = new CachingAttributeConverterLookup(oracle);
            _lookup.getAttributeConverterClass("com.kv.SomeTargetType");
            
            Assert.fail("Expected an exception!");
        }
        catch (RuntimeException anExc)
        {
            // expected
        }
        
        ctx.assertIsSatisfied();
    }

}
