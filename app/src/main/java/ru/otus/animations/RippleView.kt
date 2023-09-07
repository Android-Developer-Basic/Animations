package ru.otus.animations

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.ColorFilter
import android.graphics.Paint
import android.graphics.PixelFormat
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator

class RippleView @JvmOverloads constructor(
    context: Context? = null,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : View(context, attrs, defStyleAttr, defStyleRes) {
    private val animatedCircles = mutableListOf<AnimatedCircleDrawable>()

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        post {
            generateAnimatedCircle()
        }
    }

    private fun generateAnimatedCircle() {
        animatedCircles.add(AnimatedCircleDrawable().apply {
            startAnimation(height / 2f)
            onAnimationUpdate = {
                invalidate()
            }
            onAnimationEnd = {
                animatedCircles.remove(this)
            }
            onReadyToNext = {
                generateAnimatedCircle()
            }
        })
    }

    override fun onDraw(canvas: Canvas?) {
        canvas ?: return

        super.onDraw(canvas)

        animatedCircles.forEach { circleDrawable ->
            circleDrawable.draw(canvas)
        }
        invalidate()
    }

    private inner class AnimatedCircleDrawable : Drawable() {
        private val paint = Paint().apply {
            color = Color.parseColor("#46d7df")
            style = Paint.Style.FILL
        }

        private var radius = 0f
        private var opacity = 1f

        private val circleXPos
            get() = width / 2f
        private val circleYPos
            get() = height / 2f

        var onAnimationUpdate: (() -> Unit)? = null
        var onReadyToNext: (() -> Unit)? = null
        var onAnimationEnd: (() -> Unit)? = null

        fun startAnimation(toRadius: Float) {
            AnimatorSet().apply {
                playTogether(
                    ValueAnimator.ofFloat(0f, toRadius).apply {
                        addUpdateListener {
                            radius = it.animatedValue as Float
                            if (radius >= toRadius / 10) {
                                onReadyToNext?.invoke()
                                onReadyToNext = null
                            }
                            onAnimationUpdate?.invoke()
                        }
                    },
                    ValueAnimator.ofFloat(1f, 0f).apply {
                        addUpdateListener {
                            opacity = it.animatedValue as Float
                            onAnimationUpdate?.invoke()
                        }
                    },
                )
                addListener(object : Animator.AnimatorListener {
                    override fun onAnimationStart(animation: Animator) {

                    }

                    override fun onAnimationEnd(animation: Animator) {
                        this@AnimatedCircleDrawable.onAnimationEnd?.invoke()
                    }

                    override fun onAnimationCancel(animation: Animator) {

                    }

                    override fun onAnimationRepeat(animation: Animator) {

                    }
                })
                interpolator = AccelerateDecelerateInterpolator()
                duration = 3000
            }.start()
        }

        override fun draw(canvas: Canvas) {
            paint.alpha = (opacity * 255).toInt()
            canvas.drawCircle(circleXPos, circleYPos, radius, paint)
        }

        override fun setAlpha(alpha: Int) {

        }

        override fun setColorFilter(colorFilter: ColorFilter?) {

        }

        @Deprecated("Deprecated in Java")
        override fun getOpacity() = PixelFormat.UNKNOWN

    }
}