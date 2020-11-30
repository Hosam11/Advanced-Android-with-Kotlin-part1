package com.google.samples.propertyanimation

import android.animation.*
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.view.animation.LinearInterpolator
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView


class MainActivity : AppCompatActivity() {

    lateinit var star: ImageView
    lateinit var rotateButton: Button
    lateinit var translateButton: Button
    lateinit var scaleButton: Button
    lateinit var fadeButton: Button
    lateinit var colorizeButton: Button
    lateinit var showerButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        star = findViewById(R.id.star)
        rotateButton = findViewById(R.id.rotateButton)
        translateButton = findViewById(R.id.translateButton)
        scaleButton = findViewById(R.id.scaleButton)
        fadeButton = findViewById(R.id.fadeButton)
        colorizeButton = findViewById(R.id.colorizeButton)
        showerButton = findViewById(R.id.showerButton)

        rotateButton.setOnClickListener {
            rotater()
        }

        translateButton.setOnClickListener {
            translater()
        }

        scaleButton.setOnClickListener {
            scaler()
        }

        fadeButton.setOnClickListener {
            fader()
        }

        colorizeButton.setOnClickListener {
            colorizer()
        }

        showerButton.setOnClickListener {
            shower()
        }
    }

    private fun ObjectAnimator.disableViewDuringAnimation(view: View) {
        addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationStart(animation: Animator?) {
                view.isEnabled = false
            }

            override fun onAnimationEnd(animation: Animator?) {
                view.isEnabled = true

            }
        })
    }

    private fun rotater() {
        /*
        This line of code creates an ObjectAnimator that acts on the target “star”
        (the ImageView instance that holds our star graphic). It runs an animation on the
        ROTATION property of star.
        Changes to that property will cause the star to rotate around its center.
        There are two other rotation properties (ROTATION_X and ROTATION_Y) that rotate
        around the other axes (in 3D), but these are not typically used in UI animations,
        since UIs are typically 2D.
        */
        /*
        Note: The reason that the animation starts at -360 is that that allows the star to
        complete a full circle (360 degrees) and end at 0, which is the default rotation
        value for a non-rotated view, so it’s a good value to have at the end of the
        animation (in case any other action occurs on that view later, expecting the default
        value).
        */
        val animator = ObjectAnimator.ofFloat(star, View.ROTATION, -360f, 0f)
        with(animator) {
            duration = 500
            disableViewDuringAnimation(rotateButton)
            start()
        }


    }

    private fun translater() {
        val animator = ObjectAnimator.ofFloat(star, View.TRANSLATION_X, 200f)
        //val x = View.TRANSLATION_X
        //Log.i("TAG", "translater1: mame= ${x.name}  type= ${x.type}" )

        with(animator) {
            repeatCount = 1
            repeatMode = ObjectAnimator.REVERSE
            duration = 500
            disableViewDuringAnimation(translateButton)
            start()
        }
        // Log.i("TAG", "translater2: mame= ${x.name}  type= ${x.type}" )

    }

    private fun scaler() {
        // : A PropertyValuesHolder holds information about a single property, along with
        // the values that that property animates between
        val scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, 4f)
        val scaleX2 = PropertyValuesHolder.ofFloat(View.SCALE_X, 8f)
        val scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 4f)
        val scaleY2 = PropertyValuesHolder.ofFloat(View.SCALE_Y, 8f)

        /* An ObjectAnimator can hold multiple PropertyValuesHolder objects, which will all
        animate together, in parallel, when the ObjectAnimator starts. The target that
        these PropertyValueHolder objects animate is specified by the ObjectAnimator.
        The ideal use case for ObjectAnimators which use PropertyValuesHolder parameters
        is when you need to animate several properties on the same object in parallel.
        instead  of this { ObjectAnimator.ofFloat(star, View.TRANSLATION_X, 200f) } we can
        use an intermediate object called a PropertyValuesHolder to hold this information,
        and then create a single ObjectAnimator with multiple PropertyValuesHolder objects.
        This single animator will then run an animation on two or more of these sets of
        properties/values together.
         */
        val animator = ObjectAnimator.ofPropertyValuesHolder(star, scaleX, scaleY)
//        val animator = ObjectAnimator.ofPropertyValuesHolder(star, scaleX, scaleY, scaleX2, scaleY2)
        with(animator) {
            repeatCount = 1
            repeatMode = ObjectAnimator.REVERSE
            // duration = 1000
            disableViewDuringAnimation(scaleButton)
            start()
        }
    }

    private fun fader() {
        /* "Alpha" is a term generally used, especially in computer graphics, to denote the amount
        of opacity in an object. A value of 0 indicates that the object is completely transparent,
        and a value of 1 indicates that the object is completely opaque. View objects have a
        default value of 1. Animations that fade views in or out animate the alpha value
        between 0 and 1.
        */
        val animator = ObjectAnimator.ofFloat(star, View.ALPHA, 0f)
        with(animator) {
            repeatCount = 1
            duration = 500
            repeatMode = ObjectAnimator.REVERSE
            disableViewDuringAnimation(fadeButton)
            start()
        }
    }

    private fun colorizer() {
        // backgroundColor The name is then mapped internally to the appropriate setter/getter
        // information on the target object.
        // The ofArgb() method is the reason that this app builds against minSdk 21
        val animator = ObjectAnimator.ofArgb(star.parent, "backgroundColor", Color.BLACK, Color.RED)
        with(animator) {
            repeatCount = 1
            duration = 500
            repeatMode = ObjectAnimator.REVERSE
            disableViewDuringAnimation(colorizeButton)
            start()
        }
    }

    private fun shower() {
        val container = star.parent as ViewGroup
        val containerW = container.width
        val containerH = container.height
        var starW: Float = star.width.toFloat()
        var starH: Float = star.height.toFloat()
        /*
        Because the star is a VectorDrawable asset, use an AppCompatImageView, which has the ability
        to host that kind of resource.
        */
        val newStar = AppCompatImageView(this)
        with(newStar) {
            setImageResource(R.drawable.ic_star)
            layoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT
            )
            container.addView(newStar)
            val randomNumber = Math.random().toFloat()
            Log.i("TAG", "shower: random= $randomNumber")
            scaleX = randomNumber * 1.5f + .1f
            scaleY = scaleX
            Log.i("TAG", "shower: scaleX=scaleY= $scaleY")
            Log.i("TAG", "shower: before starW=starW= $starW")
            starW *= scaleX
            starH *= scaleY
            Log.i("TAG", "shower: after  starW=starW= $starW")
            translationX = Math.random().toFloat() * containerW - starW / 2
            Log.i("TAG", "shower: translationX= $translationX")
        }
        val mover = ObjectAnimator.ofFloat(
            newStar, View.TRANSLATION_Y,
            -starH, containerH + starH
        )
        mover.interpolator = AccelerateInterpolator(1f)
        val rotator = ObjectAnimator.ofFloat(
            newStar, View.ROTATION,
            (Math.random() * 1080).toFloat()
        )
        rotator.interpolator = LinearInterpolator()
        val set = AnimatorSet()
        with(set) {
            playTogether(mover, rotator)
            duration = (Math.random() * 1500 + 500).toLong()
            addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    container.removeView(newStar)
                }
            })
            start()
        }
        Log.i("TAG", "---------------------------------------------------------")
    }

}
