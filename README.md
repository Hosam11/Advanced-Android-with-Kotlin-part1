# Advanced-Android-with-Kotlin-part1

__This repo for what I learned in:__ 
* <a href="https://www.udacity.com/course/advanced-android-with-kotlin--ud940" target="_blank">**Advanced Android with Kotlin.**</a>  
* and those codelabs <a href="https://codelabs.developers.google.com/android-kotlin-fundamentals/"  target="_blank"> **Advanced Android in Kotlin.**</a>
## 
## 1- Notifications - EggTimer App
EggTimer is a timer app for cooking eggs. You can start and stop the timer, choose different cooking intervals then the app send notification.


This app demonstrates the following views and techniques:
*  NotificationManager,  NotificationChannel, NotificationStyle and adding action to notifications.
* Implement a Firebase Cloud Messaging 
* Send a data payload using a RemoteMessage object

## Code Link
* <a href="https://github.com/Hosam11/Advanced-Android-with-Kotlin-part1/tree/main/Notifications"  target="_blank"> **EggTimer App**</a>
## 

## Lesson 2: Advanced Graphics
### 2.1 Creating Custom Views - FanController App
The app creates a circular UI element that resembles a physical fan control. It uses a custom view that extends View to draw a circular fan control dial with an indicator and text labels for the settings: 0 (off), 1 (low), 2 (medium), and 3 (high). When the user clicks the dial, it moves the dial indicator to the next selection, and changes the dial's color from gray to other colors for selections 1-3.

This app demonstrates the following views and techniques:
* Create custom view that extends View
* Override View methods such as onDraw(), performClick() and onSizeChanged()
* Create custom attributes

## Code Link
* <a href="https://github.com/Hosam11/Advanced-Android-with-Kotlin-part1/tree/main/CustomFanController"  target="_blank"> **FanController App**</a>

| | | | |
| :---: |:---:| :---:| :---:|
| ![off](https://user-images.githubusercontent.com/18370055/99906735-5a595600-2ce1-11eb-8cbe-998a683f4cf4.PNG) | ![1](https://user-images.githubusercontent.com/18370055/99906729-59282900-2ce1-11eb-89d3-f92d25e17700.PNG) | ![2](https://user-images.githubusercontent.com/18370055/99906733-59c0bf80-2ce1-11eb-93f2-36af8ac2f037.PNG) |![3](https://user-images.githubusercontent.com/18370055/99906734-59c0bf80-2ce1-11eb-9b2b-7d13184b4393.PNG) |
## 

### 2.2 Drawing on Canvas Objects - MiniPaint App
This app uses a custom view to implement drawing on a canvas in response to touch events. All drawing happens on the UI thread by overriding the custom view's onDraw() method. You can use this technique when drawing always takes less time than a screen refresh cycle on all target devices.


## Code Link
* <a href="https://github.com/Hosam11/Advanced-Android-with-Kotlin-part1/tree/main/MiniPaint"  target="_blank"> **MiniPaint App**</a>

|   |   |   
| :---: |:---:| 
| ![empty](https://user-images.githubusercontent.com/18370055/100122216-c83c8380-2e81-11eb-8fd3-86aefafeb07f.PNG) |![helloWorld](https://user-images.githubusercontent.com/18370055/100122207-c70b5680-2e81-11eb-8f90-2130eb3a5aa6.PNG) |

## 
## Lesson 3: Animation
### 3.1 Property Animation
smiple application that animates stars on the screen by changing various View properties that control position, size, rotation, and translucency when pressed the buttons

This app demonstrates the following views and techniques:
* ObjectAnimator, AnimatorListeners, AnimatorSet, LinearInterpolator, PropertyValuesHolder

## Code Link
* <a href="https://github.com/Hosam11/Advanced-Android-with-Kotlin-part1/tree/main/PropertyAnimation"  target="_blank"> **PropertyAnimation**</a>

## Demo
![animation property demo](https://user-images.githubusercontent.com/18370055/100639739-3d0a3480-333e-11eb-8085-0cf2a1fb272b.gif)






