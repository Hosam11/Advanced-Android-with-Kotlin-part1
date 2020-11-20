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