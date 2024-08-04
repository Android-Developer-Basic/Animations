package ru.otus.animations

import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import android.animation.ValueAnimator.INFINITE
import android.animation.ValueAnimator.RESTART
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator


class CircleView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    var radius = 20f
    var startDel: Long = 0

    private val paint = Paint().apply {
        color = resources.getColor(R.color.teal)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawCircle((width/2).toFloat(), (height/2).toFloat(), radius, paint)
    }

    fun animateCircle() {
        val radiusHolder = PropertyValuesHolder.ofFloat("radius", 1f, 150f)
        val alphaHolder = PropertyValuesHolder.ofFloat("alpha", 1f, 0f)

        val animatorSecondPart = ValueAnimator.ofPropertyValuesHolder(radiusHolder, alphaHolder).apply {
            duration = 2000
            interpolator = LinearInterpolator()
            startDelay = startDel
            repeatCount = INFINITE
            repeatMode = RESTART
            addUpdateListener {
                val animateRadius = it.getAnimatedValue("radius") as Float
                radius = animateRadius
                val animateAlpha = it.getAnimatedValue("alpha") as Float
                alpha = animateAlpha
                invalidate()
            }
            start()
        }
    }
}