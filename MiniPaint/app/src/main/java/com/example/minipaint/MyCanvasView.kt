package com.example.minipaint

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration
import androidx.core.content.res.ResourcesCompat
import kotlin.math.abs

private const val STROKE_WIDTH = 12f // has to be float


class MyCanvasView(context: Context,  attrs: AttributeSet? = null) : View(context, attrs) {


    private lateinit var extraBitmap: Bitmap
    private lateinit var extraCanvas: Canvas

    private val backgroundColor = ResourcesCompat.getColor(resources, R.color.colorBackground, null)
    private val drawColor = ResourcesCompat.getColor(resources, R.color.colorPaint, null)

    /*
    In order to draw, you need a paint object that specifies how things are styled when
    drawn and a path that specify what is being drawn.
    */
    // Set up the paint with which to draw.
    private val paint = Paint().apply {
        color = drawColor
        // Smooths out edges of what is drawn without affecting shape.
        isAntiAlias = true
        // Dithering affects how colors with higher-precision than the device are down-sampled.
        isDither = true
        style = Paint.Style.STROKE // default: FILL
        strokeJoin = Paint.Join.ROUND // default: MITER
        strokeCap = Paint.Cap.ROUND // default: BUTT
        strokeWidth = STROKE_WIDTH // default: Hairline-width (really thin)
    }

    // path is the path of what the user is drawing
    private var path = Path()

    private var motionTouchEventX = 0f
    private var motionTouchEventY = 0f

    /*
    cache the latest x and y values. After the user stops moving and lifts their touch,
    these are the starting point for the next path (the next segment of the line to draw).
    */
    private var currentX = 0f
    private var currentY = 0f

    /*
    Using a path, there is no need to draw every pixel and each time request a refresh
    of the display. Instead, you can (and will) interpolate a path between points for
    much better performance.
    If the finger has barely moved, there is no need to draw.
    If the finger has moved less than the touchTolerance distance, don't draw.
    scaledTouchSlop returns the distance in pixels a touch can wander before the system
    thinks the user is scrolling.
    */
    private val touchTolerance = ViewConfiguration.get(context).scaledTouchSlop

    /*
    As the user draws on the screen, your app constructs the path and saves it in the bitmap
    extraBitmap. The onDraw() method displays the extra bitmap in the view's canvas you could draw
    shapes after drawing the bitmap
    */
    private lateinit var frame: Rect

    /**
     * The onSizeChanged() method is called by the Android system whenever a view changes size.
     * Because the view starts out with no size, the view's onSizeChanged() method is also called
     * after the Activity first creates and inflates it. This onSizeChanged() method is therefore
     * the ideal place to create and set up the view's canvas.
     */
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        /*
        a new bitmap and canvas are created every time the function executes. You need a new bitmap,
        because the size has changed. However, this is a
        memory leak, leaving the old bitmaps around. To fix this, recycle extraBitmap
        before creating the next one by adding this code right after the call to super.
        */
        if (::extraBitmap.isInitialized) extraBitmap.recycle()
        /*
        create an instance of Bitmap with the new width and height, which are the screen size,
        and assign it to extraBitmap
        ARGB_8888 stores each color in 4 bytes and is recommended.
        */
        extraBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
        // Create a Canvas instance from extraBitmap and assign it to extraCanvas.
        extraCanvas = Canvas(extraBitmap)
        // Specify the background color in which to fill extraCanvas.
        extraCanvas.drawColor(backgroundColor)
        // Calculate a rectangular frame around the picture.
        val inset = 40
        frame = Rect(inset, inset, width - inset, height - inset)
    }

    /**
     * Our drawing work from my canvas view happens in onDraw. To start, display the canvas,
     * fill in the screen with a background color that you set in onSizeChanged.
     *
     * draw the contents of the cached extraBitmap on the canvas associated with the view.
     */
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        /*
        0f, 0f  the x and y coordinates (in pixels) of the top left corner
        The 2D coordinate system used for drawing on a Canvas is in pixels, and the origin (0,0)
        is at the top left corner of the Canvas.
         */
        canvas?.drawBitmap(extraBitmap, 0f, 0f, null)
        // Draw a frame around the canvas.
        canvas?.drawRect(frame, paint)
    }

    /**
     * onTouchEvent() method to cache the x and y coordinates of the passed in event.
     * Then use a when expression to handle motion events for touching down on the screen,
     * moving on the screen, and releasing touch on the screen
     */
    override fun onTouchEvent(event: MotionEvent): Boolean {
        motionTouchEventX = event.x
        motionTouchEventY = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> touchStart()
            MotionEvent.ACTION_MOVE -> touchMove()
            MotionEvent.ACTION_UP -> touchUp()
        }

        return true
    }

    private fun touchUp() {
        // Reset the path so it doesn't get drawn again.
        path.reset()
    }

    private fun touchMove() {
        // Calculate the distance that has been moved (dx, dy).
        val dx = abs(motionTouchEventX - currentX)
        val dy = abs(motionTouchEventY - currentY)
        // If the movement was further than the touch tolerance, add a segment to the path.
        if (dx >= touchTolerance || dy >= touchTolerance) {
            // QuadTo() adds a quadratic bezier from the last point,
            // approaching control point (x1,y1), and ending at (x2,y2).
            // Using quadTo() instead of lineTo() create a smoothly drawn line without corners
            path.quadTo(
                currentX,
                currentY,
                (motionTouchEventX + currentX) / 2,
                (motionTouchEventY + currentY) / 2
            )
            // Set the starting point for the next segment to the endpoint of this segment.
            currentX = motionTouchEventX
            currentY = motionTouchEventY
            // Draw the path in the extra bitmap to cache it.
            extraCanvas.drawPath(path, paint)
        }
        invalidate()
    }

    private fun touchStart() {
        path.reset()
        path.moveTo(motionTouchEventX, motionTouchEventY)
        currentX = motionTouchEventX
        currentY = motionTouchEventY
    }

    /**
     * clear the all drawing
     */
    fun clear(){
        extraCanvas.drawColor(backgroundColor)
    }
}