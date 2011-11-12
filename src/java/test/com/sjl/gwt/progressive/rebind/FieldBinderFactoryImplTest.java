package com.sjl.gwt.progressive.rebind;

import org.jmock.*;
import org.junit.*;

public class FieldBinderFactoryImplTest
{
    private Mockery ctx;
    private FieldBinder binder;
    private FieldInfo info;
    private FieldInfo.AttributeField attr;
    private FieldInfo.WidgetField widg;
    
    @Before
    public void setup()
    {
        ctx = new Mockery();
        binder = ctx.mock(FieldBinder.class);
        info = ctx.mock(FieldInfo.class);
        attr = ctx.mock(FieldInfo.AttributeField.class);
        widg = ctx.mock(FieldInfo.WidgetField.class);
    }
    
    @Test
    public void selectsMatchingBinder()
    {
        ctx.checking(new Expectations()
        {{
            oneOf(binder).canBind(info); will(returnValue(true));
        }});
        
        FieldBinderFactoryImpl _f = new FieldBinderFactoryImpl();
        _f.addBinder(binder);        
        
        Assert.assertEquals(binder, _f.getBinder(info));
        
        ctx.assertIsSatisfied();
    }
    
    @Test
    public void selectsNullObjectBinderIfNoMatches()
    {
        ctx.checking(new Expectations()
        {{
            oneOf(binder).canBind(info); will(returnValue(false));
        }});
        
        FieldBinderFactoryImpl _f = new FieldBinderFactoryImpl();
        _f.addBinder(binder);        
        
        Assert.assertEquals(FieldBinder.NULL_OBJECT, _f.getBinder(info));
        
        ctx.assertIsSatisfied();
    }
    
    @Test
    public void selectsErrorBinderForBadRuntimeUiAttributeAnnotation()
    {
        ctx.checking(new Expectations()
        {{
            allowing(binder).canBind(attr); will(returnValue(false));            
            
            allowing(attr).isWidgetField(); will(returnValue(false));            
            
            allowing(attr).isAttributeField(); will(returnValue(true));
            allowing(attr).asAttributeField(); will(returnValue(attr));
            allowing(attr).hasNoAttributeName(); will(returnValue(true));            
        }});        
        
        FieldBinderFactoryImpl _f = new FieldBinderFactoryImpl();
        _f.addBinder(binder);
        _f.addBinder(FieldBinderFactoryImpl.MISSING_ATTRIBUTE_PARAMS_BINDER);
        _f.addBinder(FieldBinderFactoryImpl.MISSING_WIDGET_PARAMS_BINDER);
        
        Assert.assertEquals(FieldBinderFactoryImpl.MISSING_ATTRIBUTE_PARAMS_BINDER, _f.getBinder(attr));
        
        ctx.assertIsSatisfied();
    }
    
    @Test
    public void selectsErrorBinderForBadRuntimeUiWidgetAnnotation()
    {
        ctx.checking(new Expectations()
        {{
            allowing(binder).canBind(widg); will(returnValue(false));
            
            allowing(widg).isAttributeField(); will(returnValue(false));
            
            allowing(widg).isWidgetField(); will(returnValue(true));
            allowing(widg).asWidgetField(); will(returnValue(widg));
            allowing(widg).hasNoId(); will(returnValue(true));
            allowing(widg).hasNoTag(); will(returnValue(true));
            allowing(widg).hasNoCssClass(); will(returnValue(true));
        }});        
        
        FieldBinderFactoryImpl _f = new FieldBinderFactoryImpl();
        _f.addBinder(binder);
        _f.addBinder(FieldBinderFactoryImpl.MISSING_ATTRIBUTE_PARAMS_BINDER);
        _f.addBinder(FieldBinderFactoryImpl.MISSING_WIDGET_PARAMS_BINDER);
        
        Assert.assertEquals(FieldBinderFactoryImpl.MISSING_WIDGET_PARAMS_BINDER, _f.getBinder(widg));
        
        ctx.assertIsSatisfied();
    }
}
