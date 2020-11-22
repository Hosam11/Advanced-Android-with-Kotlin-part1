package com.example.customfancontroller

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.core.content.withStyledAttributes
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.sin

private enum class FanSpeed(val label: Int) {
    OFF(R.string.fan_off),
    LOW(R.string.fan_low),
    MEDIUM(R.string.fan_medium),
    HIGH(R.string.fan_high);

    fun next() = when (this) {
        OFF -> LOW
        LOW -> MEDIUM
        MEDIUM -> HIGH
        HIGH -> OFF
    }

}

const val TAG = "tag"


// You'll use these as part of drawing the dial indicators and labels.
private const val RADIUS_OFFSET_LABEL = 30
private const val RADIUS_OFFSET_INDICATOR = -35

/**
 * @JvmOverloads  The @JvmOverloads annotation instructs the Kotlin compiler to generate overloads
 * for this function that substitute default parameter values.
 */
class DialView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    // The radius is the current radius of the circle. This value is set when the view is drawn
    // on the screen.
    private var radius = 0.0f

    // The fanSpeed is the current speed of the fan, which is one of the values in the FanSpeed
    // enumeration. By default that value is OFF.
    private var fanSpeed = FanSpeed.OFF         // The active selection.

    //  X,Y position variable which will be used to draw label and indicator circle position
    private val pointPosition: PointF = PointF(0.0f, 0.0f)

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        textAlign = Paint.Align.CENTER
        textSize = 55.0f
        typeface = Typeface.create("", Typeface.BOLD)
    }

    private var fanSpeedLowColor = 0
    private var fanSpeedMediumColor = 0
    private var fanSeedMaxColor = 0

    init {
        isClickable = true
        context.withStyledAttributes(attrs, R.styleable.DialView) {
            fanSpeedLowColor = getColor(R.styleable.DialView_fanColor1, 0)
            fanSpeedMediumColor = getColor(R.styleable.DialView_fanColor2, 0)
            fanSeedMaxColor = getColor(R.styleable.DialView_fanColor3, 0)

        }
    }

    override fun performClick(): Boolean {
        // The call to super.performClick() must happen first, which enables accessibility events
        // as well as calls onClickListener().
        if (super.performClick()) return true

        fanSpeed = fanSpeed.next()
        contentDescription = resources.getString(fanSpeed.label)
        // invalidates the entire view, forcing a call to onDraw() to redraw the view. If something
        // in your custom view changes for any reason, including user interaction, and the change
        // needs to be displayed
        invalidate()
        return true
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        radius = (min(w, h) / 2.0 * 0.8).toFloat()
        Log.i(TAG, "onSizeChanged: radius = $radius")
    }

    private fun PointF.computeXYForSpeed(pos: FanSpeed, radius: Float) {
        // Angles are in radians.
        val startAngle = Math.PI * (9 / 8.0)
        val angle = startAngle + pos.ordinal * (Math.PI / 4)
        x = (radius * cos(angle)).toFloat() + width / 2
        y = (radius * sin(angle)).toFloat() + height / 2

        Log.i(
            TAG, "computeXYForSpeed: startAngle= $startAngle" +
                    " angle= $angle  x= $x   y=$y "
        )
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        // Set dial background color to green if selection not off.
       // paint.color = if (fanSpeed == FanSpeed.OFF) Color.GRAY else Color.GREEN
        paint.color = when (fanSpeed) {
            FanSpeed.OFF -> Color.GRAY
            FanSpeed.LOW -> fanSpeedLowColor
            FanSpeed.MEDIUM -> fanSpeedMediumColor
            FanSpeed.HIGH -> fanSeedMaxColor
        }
        // Draw the dial.
        canvas?.drawCircle((width / 2).toFloat(), (height / 2).toFloat(), radius, paint)
        // Draw the indicator circle.
        val markerRadius = radius + RADIUS_OFFSET_INDICATOR
        pointPosition.computeXYForSpeed(fanSpeed, markerRadius)
        paint.color = Color.BLACK
        canvas?.drawCircle(pointPosition.x, pointPosition.y, radius / 12, paint)
        // Draw the text labels.
        val labelRadius = radius + RADIUS_OFFSET_LABEL
        for (i in FanSpeed.values()) {
            pointPosition.computeXYForSpeed(i, labelRadius)
            val label = resources.getString(i.label)
            canvas?.drawText(label, pointPosition.x, pointPosition.y, paint)
        }
    }
}