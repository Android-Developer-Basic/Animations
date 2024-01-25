package ru.otus.animations

import android.animation.Animator
import android.animation.Animator.AnimatorListener
import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class LoadingAnimationView @JvmOverloads constructor(
    context: Context? = null,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
): View(context, attrs, defStyleAttr, defStyleRes) {

    private val margin = 100

    private val centerX get() = width / 2f;
    private val centerY get() = height / 2f
    private val radius get() = minOf(width, height) / 4f - margin

    private val leftCircleOriginalPositionX: Float get() = centerX - radius - (margin / 2)
    private var leftDx = 0f
    private var leftScale = 1f
    private val rightCircleOriginalPositionX: Float get() = centerX + radius + (margin / 2)
    private var rightDx = 0f
    private var rightScale = 1f
    private var rightAlpha = 255

    private val paint = Paint()
    private var isRunningAnimation = false

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)

        leftDx = leftCircleOriginalPositionX
        rightDx = rightCircleOriginalPositionX
    }

    fun startFirstAnimation(){
        if (isRunningAnimation) return

        isRunningAnimation = true
        val animatorSet = AnimatorSet()
        animatorSet.apply {
            duration = 2000
            startDelay = 200

            playTogether(
                ValueAnimator.ofFloat(leftCircleOriginalPositionX, rightCircleOriginalPositionX).apply {
                    addUpdateListener {
                        leftDx = it.animatedValue as Float
                        invalidate()
                    }
                },
                ValueAnimator.ofFloat(1f, 1.1f, 1f).apply {
                    addUpdateListener {
                        leftScale = it.animatedValue as Float
                        invalidate()
                    }
                },
                ValueAnimator.ofFloat(rightCircleOriginalPositionX, leftCircleOriginalPositionX).apply {
                    addUpdateListener {
                        rightDx = it.animatedValue as Float
                        invalidate()
                    }
                },
                ValueAnimator.ofFloat(1f, 0.7f, 1f).apply {
                    addUpdateListener {
                        rightScale = it.animatedValue as Float
                        invalidate()
                    }
                },
                ValueAnimator.ofInt(255, 0, 255).apply {
                    addUpdateListener {
                        rightAlpha = it.animatedValue as Int
                        invalidate()
                    }
                }
            )

            addListener(object : AnimatorListener {
                override fun onAnimationStart(animation: Animator) {

                }

                override fun onAnimationEnd(animation: Animator) {
                    startSecondAnimation()
                }

                override fun onAnimationCancel(animation: Animator) {

                }

                override fun onAnimationRepeat(animation: Animator) {

                }

            })
        }.start()
    }

    private fun startSecondAnimation(){
        val animatorSet = AnimatorSet()

        animatorSet.apply {
            duration = 2000
            startDelay = 200

            playTogether(
                ValueAnimator.ofFloat(rightCircleOriginalPositionX, leftCircleOriginalPositionX).apply {
                    addUpdateListener {
                        leftDx = it.animatedValue as Float
                        invalidate()
                    }
                },
                ValueAnimator.ofFloat(leftCircleOriginalPositionX, rightCircleOriginalPositionX).apply {
                    addUpdateListener {
                        rightDx = it.animatedValue as Float
                        invalidate()
                    }
                }
            )

            addListener(object : AnimatorListener {
                override fun onAnimationStart(animation: Animator) {

                }

                override fun onAnimationEnd(animation: Animator) {
                    isRunningAnimation = false
                }

                override fun onAnimationCancel(animation: Animator) {

                }

                override fun onAnimationRepeat(animation: Animator) {

                }
            })
        }.start()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.drawCircle(leftDx, centerY, radius * leftScale, paint.apply {
                color = Color.parseColor("#7c4fff")
            })
        canvas?.drawCircle(rightDx, centerY, radius * rightScale, paint.apply {
                color = Color.parseColor("#ff39d3")
                alpha = rightAlpha
            })
    }
}