1- Notification
    (1) Notification Manager
    /*
    -> Notification Manager, which is a system service, helps you to display this content as a notification.
		-> Notification Manager is responsible for sending a notification, updating its contents,and canceling 
			the notification.
		-> NotificationManager is a system service which provides all the functions exposed for notifications api,
		 	including the extension function you added. Anytime you want to send, cancel or update a notification you 
			need to request an instance of the NotificationManager from the system. 
		*/
		(2) Notification Channel/id
		/*
		-> Starting with API level 26 all notifications must be assigned to a channel.
		-> NotificationId represents the current notification instance and is needed for updating or canceling 
			this notification. Since your app will only have one active notification at a given time,
			you can use the same id for all your notifications.
		-> all notifications must be assigned to a channel. Channels represent a type of notification.
		-> All notifications in your channel are grouped together, and users can configure their notification 
			settings for the whole channel. This allows the user to personalize their notification settings based off 
			of the type of notification they're interested in.
		*/
		(3) PendingIntent
		/*
		-> PendingIntent grants rights to another application or the system to perform an operation on behalf of 
			your application. A PendingIntent itself is simply a reference to a token maintained by the system 
			describing the original data used to retrieve it. This means that, even if its owning application's process 
			is killed, the PendingIntent itself will remain usable from other processes it has been given to.
			In this case, the system will use the pending intent to open the app on behalf of you, regardless of 
			whether or not the timer app is running.
		*/
		(4) Firebase Cloud Messages
		/*
		-> Firebase Cloud messages can also be silently handled by the app to perform a task such as
		 updating the app or starting data transfer.
		*/
		(5)
		/*
		-> Message Composer is a tool that helps you to compose and send messages from Firebase console website.
		*/
		(6) Device Registration Token.
		/*
		-> To send a message to a specific device, you need to know that device's registration token.
			On the initial startup of your app, the Firebase back-end generates a registration token for the client 
			app instance.
		-> If you want to target a single device, or create a group of devices which you want to send broadcast messages,
			you will need to access this token by extending Firebase messaging service and overriding OnNewToken.		
		-> OnNewToken function is called automatically if your service is registered in the Android manifest.
			This function is called when you first run your app, or every time Firebase issues a new token for the device.
		-> A token is an access key to your Firebase back-end project. With this token, Firebase nodes which 
		client the back-end is talking to. 
		-> Firebase also knows if this client is valid and has access to this Firebase project.
		*/
		(7) Publish/subscribe 
		/*
		-> Publish/subscribe allows backend apps to push relevant content to interested clients.
		-> Topics, allow you to send a message to multiple devices that have opted into that particular topic.
		-> For clients, topics are specific data sources which the client is interested in.
		-> For the server, topics are groups of devices which are opted into receive updates on the specific data source.
		-> Topics can be used for purposes like subscribing news, weather forecasts or sport results.
		*/

2- Advanced Graphics
	(1)
	/*
	-> If you extend an existing View subclass, override only the behavior or aspects of the appearance that you 
		want to change.
	-> If you extend the View class, draw the custom view's shape and control its appearance by overriding View 
		methods such as onDraw() and onMeasure() in the new class.
	*/
	(2) @JvmOverloads
	/*
	-> The @JvmOverloads annotation instructs the Kotlin compiler to generate overloads for this function 
		that substitute default parameter values.
	*/
	(3) Canvas
	/*
	-> you can create 2D drawings using the drawing methods of the Canvas class. canvas is a 2D drawing 
		surface that provides methods for drawing. This is useful when your app needs to regularly redraw itself,
		because what the user sees changes over time.	
	*/
	(4) Drawing
	/*
	-> you need a view for displaying what you're drawing. This could be one of the views provided by the 
		Android system or you create a custom view that serves as the content view for your app. or use custom view.
	-> custom view comes with its own canvas.
	-> For the most basic way of drawing under canvas of a view, you overwrite the onDraw method 
		and draw on its canvas.
	-> When drawing cumulatively, you need to cache what you have drawn before, mostly because better performance 
		for complex drawings and it's it's a best practice to always apply
	-> here are several ways of caching your data. One is in a bitmap.
		Another way we just save a history of what you've drawn as co-ordinates or instructions.
		Which method you choose depends on your application and your personal preference.
	-> To draw to your caching bitmap, $extraBitmap using the canvas drawing API, you create a caching canvas,
		$extraCanvas for your caching bitmap. Then you draw on your caching canvas, extraCanvas which draws on 
		your caching bitmap, extraBitmap. To display everything on the screen, you tell the views canvas to 
		draw out a caching bitmap. 
	-> To display what you will draw in MyCanvasView, you have to set it as the ContentView of the MainActivity.
	*/
3- Animation
	(1) ObjectAnimator
	/*
	-> the basic building blocks of most Android animations. Property animations are used to animate 
		(or change over time) the value of a property on an object, usually a UI object like an Android view.
	-> ObjectAnimators are class that was specifically created to provide a kind of set and forget animation,
		where you tell it what to animate, the target object and property, and the start and the end values for 
		the animation, and it handles all of the rest of the details about actually running the animation over time 
		on that target object
	*/
	(2) Property
	/*
	-> Note: A property, to the animation system, is a field that is exposed via setters and getters, either implicitly
		(as properties are in Kotlin) or explicitly (via the setter/getter pattern in the Java programming language).
		There are also a special case of properties exposed via the class android.util.Property which is used by the 
		View class, which allows a type-safe approach for animations, 
	-> The Animator system in Android was specifically written to animate properties, meaning that it can animate 
		anything (not just UI elements) that has a setter (and, in some cases, a getter)

	-> There are actually two different ways to access the properties, by regular setter/getter pairs, like 
		setTranslateX()/getTranslateX(), and by static android.util.Property objects, like View.TRANSLATE_X 
		(an object that has both a get() and a set() method). 
	-> use android.util.Propertyobjects, because it has less overhead internally along with better type-safety, 
	*/
	(3) AnimatorSet
	/*
	-> AnimatorSet, which is useful for this slightly more complex animation involving multiple ObjectAnimators..
	-> AnimatorSet is basically a group of animations, along with instructions on when to run those animations.
		It can play animations in parallel, or sequentially.
	-> AnimatorSet can also contain other AnimatorSets, so you can create very complex hierarchical choreography 
		by grouping animators together into these sets. 
	*/