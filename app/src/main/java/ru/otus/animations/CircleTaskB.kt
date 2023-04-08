package ru.otus.animations

import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import androidx.core.content.ContextCompat

class CircleTaskB @JvmOverloads constructor(
    context: Context,
    attr: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attr, defStyleAttr) {

    private var paint = Paint().apply {
        color = ContextCompat.getColor(context, R.color.teal_200)
    }

    private val delay = 500L
    private var startRadius = 100f

    private data class CircleParameters(var radius: Float, var alpha: Int)

    private val circles = arrayOf(
        CircleParameters(startRadius, 0xFF),
        CircleParameters(0f, 0xFF),
        CircleParameters(0f, 0xFF),
        CircleParameters(0f, 0xFF)
    )

    override fun onFinishInflate() {
        super.onFinishInflate()
        setOnClickListener {
            startRadius = 0f
            startAnimation()
        }
    }

    override fun onDraw(canvas: Canvas?) {
        circles.forEach {
            paint.alpha = it.alpha
            canvas?.drawCircle(
                this.width / 2f,
                this.height / 2f,
                it.radius,
                paint
            )
            super.onDraw(canvas)
        }
    }

    private fun startAnimation() {
        (circles.indices).forEach { index ->
            val animatorAlpha = PropertyValuesHolder.ofInt("alpha", 0xFF, 0x0)
            val animatorRadius = PropertyValuesHolder.ofFloat("radius", 0f, 300f)
            ValueAnimator.ofPropertyValuesHolder(animatorAlpha, animatorRadius).apply {
                duration = circles.size * delay
                interpolator = LinearInterpolator()
                repeatCount = Animation.INFINITE
                startDelay = index * delay
                addUpdateListener { animator ->
                    circles[index].radius = animator.getAnimatedValue("radius") as Float
                    circles[index].alpha = animator.getAnimatedValue("alpha") as Int
                    invalidate()
                }
                start()
            }
        }
    }
}