package ru.otus.animations

import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator

class CircleView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(
    context,
    attrs,
    defStyleAttr
) {
    private var radius: Float = 0f
    private var alpha: Float = 0f
    private var animator: ValueAnimator? = null

    private val paint = Paint().apply {
        color = resources.getColor(R.color.teal)
        style = Paint.Style.FILL
    }

    init {
        secondTask()
        setOnClickListener {
            restartSecondTask()
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawCircle(width / 2f, height / 2f, radius, paint)
        paint.alpha = (255 * (1f - radius / 130f)).toInt()
    }

    fun secondTask() {
        val radiusHolder = PropertyValuesHolder.ofFloat("radius", 0f, 130f)

        val animator = ValueAnimator.ofPropertyValuesHolder(radiusHolder).apply {
            duration = 3000
            interpolator = LinearInterpolator()
            repeatCount = ValueAnimator.INFINITE
            repeatMode = ValueAnimator.RESTART
            addUpdateListener {
                radius = it.getAnimatedValue("radius") as Float
                invalidate()
            }
        }
        animator.start()
    }

    private fun restartSecondTask() {
        animator?.cancel()
        secondTask()
    }
}
