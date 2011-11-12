## Easy Progressive-Enhancement with GWT

Progressive-enhancement is a technique for delivering a basic website suitable for search-engines and older web-browsers, then gradually adding additional polish, interactivity and general goodness using targetted javascript.

Traditionally `GWT` works the other way around - it builds all of the `DOM` structure of your web-app in script, so search-engines (and browsers with script disabled) only see an inscrutable mass of script.

`GWT.Progressive` helps to bring your ninja `GWT` skills to bear on web-sites that need progressive enhancement.

## What can GWT.Progressive do for me?

* Bind `GWT` widgets to html elements on static (or server-generated) html pages, using the html as a template and `GWT` magic to "enhance" the user-experience
* Automatically bind nested html elements to properties of widget classes (child widgets, grand-child widgets, etc)
* Bind repeated html elements to List<MyWidgetClass> properties of widget classes
* Automatically bind attributes of html elements to properties of widget classes - with automatic type coercion
* Make partial page-updates a breeze (replacing parts of the `DOM` with new server-generated content)

## Show me how it works

### Element Binding

You can bind a `GWT` widget to any html element on your website - just by adding an annotation to the widget class:

	// bind by id
	@RootBinding(id="mywidget")
	class MyWidget extends Composite {
	    //...
	}

	// bind by tag name
	@RootBinding(tag="h1")
	class MyWidget extends Composite {
	   //...
	}

	// bind by css class
	@RootBinding(cssClass="bind-me")
	class MyWidget extends Composite {
	   //...
	}

	// bind by tag name and css class
	@RootBinding(tag="div", cssClass="bind-me")
	class MyWidget extends Composite {
	   //...
	}

Bind nested html to child widgets:

	// bind the first <h1> tag we find by breadth first
	// search of the DOM within our widget's root element
	@RuntimeUiWidget(tag="h1") MyHeaderWidget header;

	// bind the first element we find by breadth first
	// search that has the css class "foo"
	@RuntimeUiWidget(cssClass="foo") MyFooWidget foo; 

Bind repeated elements to a List of child widgets:

	// bind all the <img> elements we find inside our
	// widget's root element to the list of Image widgets
	@RuntimeUiWidget(tag="img") List<Image> images;

### Attribute Binding

Bind html attributes as properties of your widgets, with automatic type coercion:

	// the following attributes will have their default values (set below)
	// overridden by the values of attributes in the html, if they exist
	@RuntimeUiAttribute("text-attr") String textAttr = "default";
	@RuntimeUiAttribute("int-attr") Integer integerAttr = 22;
	@RuntimeUiAttribute("long-attr") Long longAttr = 9L;
	@RuntimeUiAttribute("float-attr") Float floatAttr = 2.3f;
	@RuntimeUiAttribute("double-attr") Double doubleAttr = 2222222.222d;
	@RuntimeUiAttribute("boolean-attr") Boolean booleanAttr = false;

Bind attributes to custom types by supplying converters - given html like: `<div coords="200x400"> ... </div>`, automatically bind the coords property of our widget as an instance of Coords, converted by CoordsAttributeConverter...

In the `Widget` we're binding:

	// automatically bind our custom Coords class 
	@RuntimeUiAttribute("coords") Coords coords;

The `Coords` class:

	class Coords {
	    public Integer x, y;
	    public Coords(Integer anX, Integer aY) {
	        x = anX;
	        y = aY;
	    }
	}

The `AttributeConverter`:

	@RuntimeUiAttributeConverter
	public class CoordsAttributeConverter 
	extends AttributeConverter<Coords> {
	    public Coords convert(String aString) {
	        String[] _parts = aString.split("x");
	        return new Coords(
				Integer.parseInt(_parts[0]), 
				Integer.parseInt(_parts[1])
	        );
	    }
	}

### Partial Page Updates

Replace chunks of html with new html from the server. Your server can provide this html as it pleases - for example you can call servlets, jsp's, PHP or whatever you like to get the html for the replacement. Your widgets will re-bind to the new html after adding it to the `DOM`.

	// UiBinder afficionados will be familiar with this plumbing
	// which we'll use to invoke our page update...
	interface MyActivator extends ElementActivator<Partial>{}
	MyActivator activator = GWT.create(MyActivator.class);
    
	// here's the widget we're going to update
	@RuntimeUiWidget(tag="div") UpdateMe updateMe;

        ...

	// call this method to trigger the update
	private void someMethod() {
	    activator.update(
	        updateMe, "/update-me.jsp", 
	        new CallMeWhenUpdated()
	    );
	}

You can register callbacks to trigger when a partial update completes - successfully or otherwise. Here's a more complete partial update example:

    @RootBinding(tag="div")
    public class Partial extends BoundRootPanel
        // UiBinder afficionados will be familiar with this
        interface MyActivator extends ElementActivator<Partial>{}
        MyActivator activator = GWT.create(MyActivator.class);
    
        @RuntimeUiWidget(tag="div") UpdateMe updateMe;
    
        public Partial(Element anElement)
        {
            setElement(activator.activate(this, anElement));
                
            // Update the page when we're clicked...
            addDomHandler(new ClickHandler() {
                public void onClick(ClickEvent aEvent) {
                    activator.update(
                        Partial.this, "/update-me.jsp", 
                        new CallMeWhenUpdated()
                    );
                });
            }            
        }, ClickEvent.getType());

        class CallMeWhenUpdated 
        implements PageUpdateCallback<Partial>() {
            public void onSuccess(Partial anUpdated) {
                Window.alert("Updated!");
            }

            public void onError(Throwable anExc) {
                GWT.log("oops, something bad happened", anExc);
            }   
        }
    }

### OK, Give me a complete example...

Here's the HTML we're going to enhance:

	<html>
	  <body>
	    <div class="widget">
	      <img class="first" src="/image-1.png">
	      <img class="second" src="/image-2.png">
	    </div>
	    <!-- our script loads last, and progressive 
	         enhancement begins -->
	    <script 
	        type="text/javascript" language="javascript" 
	        src="example/example.nocache.js?cache=20111112180652">
	    </script>
	  </body>
	</html>

Here's the widget we're going to bind to the `<div>` of css-class "widget":

	@RootBinding(cssClass="widget")
	class MyWidget extends BoundRootPanel {
	    interface MyActivator extends ElementActivator<MyWidget>{}
	    MyActivator activator = GWT.create(MyWidget.class);

	    @RuntimeUiWidget(cssClass="first") Image first;
	    @RuntimeUiWidget(cssClass="second") Image second;

	    public MyWidget(Element anElement) {
	        setElement(activator.activate(this, anElement);

	        // ... now we do some enhancements, e.g. add
	        // click-handlers to our image's our something
	    }
	}

We'll need an EntryPoint - this is still a `GWT` application:

	class Example1 implements EntryPoint {
	    public void onModuleLoad() {
	        PageActivator _activator = GWT.create(PageActivator.class);
	        _activator.activate();
	    }
	}


... and a `GWT` module descriptor (`.gwt.xml`) of course:

	<module rename-to="example">
	  <inherits name="com.google.gwt.user.User"/>
	  <inherits name="com.sjl.gwt.progressive.Progressive"/>
  
	  <entry-point class="com.sjl.example.client.Example" />

	  <!-- for maximum compatibility, use the xs linker -->
	  <add-linker name="xs" />
	</module>

## OK, I want to use it, so ...

### What are the rules?

`GWT.Progressive` requires that you follow a few simple rules to ensure that your widgets get "activated" correctly, integrated into the GWT/browser eventing system, etc.

Your widgets _must_:

* Have a public `Constructor` that takes a `com.google.gwt.dom.client.Element` argument, OR
* Have a `public static <T> wrap()` method that returns an instance of your widget class (this is how native GWT widgets like Image and Label are bound by GWT.Progressive).
* `GWT.create()` a special class that allows `GWT.Progressive`'s generator to work its magic on your widgets (either `com.sjl.gwt.progressive.client.ElementActivator` or `com.sjl.gwt.progressive.client.WidgetActivator`).
* Set your Widget's Element as the return value from activation (`setElement(activator.activate(this, anElement))`) or init your Composite with it (`initWidget(activator.activate(this, anElement))`).

If you've ever used UiBinder, this pattern will be very familiar to you.

### Where can I find more examples?

In the github repository, in the [test source tree](https://github.com/steveliles/gwt-progressive/tree/master/src/java/test/com/sjl/gwt/progressive).

### Any examples in the wild?

The twitter feed in my blog side-bar for one...

### Can I use this in my products?

Sure, its available under the Apache-2.0 license, but ... disclaimer: use at your own risk and discretion, I cannot accept responsibility for any issues that may arise from using this library.

### Can I modify the code, fix bugs, etc?

Knock yourself out :) ... I'd love to hear about it if you do.

## Final Words

I just want to say - props to my employer ([KnowledgeView ltd.](http://www.knowledgeview.com)) for allowing me to release the code, some of which was developed on their dime. Mucho thanks KV :)

You can find some more background on `GWT.Progressive` in some of my earlier blog posts: [part-1](http://steveliles.github.com/progressive_enhancement_with_gwt_part_1.html), [part-2](http://steveliles.github.com/progressive_enhancement_with_gwt_part_2.html), and [part-3](http://steveliles.github.com/progressive_enhancement_with_gwt_part_3.html)