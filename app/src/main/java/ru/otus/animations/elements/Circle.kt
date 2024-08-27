package ru.otus.animations.elements

import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import android.animation.ValueAnimator.INFINITE
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator


class Circle @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {


    private val paint = Paint().apply {
        color = Color.CYAN
    }

    fun setColor(color: Int) {
        paint.color = color
    }

    data class CircleParameters(var radius: Float = 0f, var alpha: Int = 255)

    var circles = ArrayList<CircleParameters>()

    override fun onDraw(canvas: Canvas) {
        circles.forEach {
            paint.alpha = it.alpha
            canvas.drawCircle(
                (width / 2).toFloat(), (height / 2).toFloat(), it.radius, paint
            )
            super.onDraw(canvas)
        }
    }

    fun waveAnimation(delay: Long = 500) {
        (circles.indices).forEach { index ->
            val radiusAnimatedValue = PropertyValuesHolder.ofFloat("radius", 0f, 300f)
            val alphaAnimatedValue = PropertyValuesHolder.ofInt("alpha", 0xFF, 0x0)
            ValueAnimator.ofPropertyValuesHolder(radiusAnimatedValue, alphaAnimatedValue)
                .apply {
                    duration = circles.size * delay
                    interpolator = LinearInterpolator()
                    repeatCount = INFINITE
                    startDelay = index * delay
                    addUpdateListener {
                        circles[index].radius = it.getAnimatedValue("radius") as Float
                        circles[index].alpha = it.getAnimatedValue("alpha") as Int
                        invalidate()
                    }
                    start()
                }
        }


    }
}