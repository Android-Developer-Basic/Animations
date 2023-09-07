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
import android.view.animation.AccelerateDecelerateInterpolator

class TikTokView @JvmOverloads constructor(
    context: Context? = null,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
): View(context, attrs, defStyleAttr, defStyleRes) {
    private val paintFirst = Paint().apply {
        style = Paint.Style.FILL
        color = Color.parseColor("#7c4fff")
    }

    private val paintSecond = Paint().apply {
        style = Paint.Style.FILL
        color = Color.parseColor("#ff39d3")
    }

    private val radius: Float
        get() = width / 16f

    private val margin: Float // Дефолт расстояния между кругами
        get() = radius / 2.5f

    private val firstCircleXPos: Float
        get() = (width / 2) - (margin / 2) - radius

    private val secondCircleXPos: Float
        get() = firstCircleXPos + (radius * 2) + margin

    private val cirlesYPos: Float // Cмещение по Y
        get() = height / 2f

    private var firstXPos = 0f
    private var secondXPos = 0f
    private var firstCircleSizeScale = 1f
    private var secondCircleSizeScale = 1f
    private var alphaCoeff = 1f

    private val interpolator = AccelerateDecelerateInterpolator()

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        post { startFirstAnimation() }
    }

    private fun startFirstAnimation() {
        AnimatorSet().apply {
            playTogether(
                ValueAnimator.ofFloat(0f, secondCircleXPos - firstCircleXPos).apply {//Анимируем положение по X первого кружочка
                    addUpdateListener { animator ->
                        firstXPos = animator.animatedValue as Float
                        invalidate()
                    }
                },
                ValueAnimator.ofFloat(0f, -(secondCircleXPos - firstCircleXPos)).apply {//Анимируем положение по X второго кружочка
                    addUpdateListener { animator ->
                        secondXPos = animator.animatedValue as Float
                        invalidate()
                    }
                },
                ValueAnimator.ofFloat(1f, 1.2f, 1f).apply {//Анимируем размер первого кружочка
                    addUpdateListener { animator ->
                        firstCircleSizeScale = animator.animatedValue as Float
                        invalidate()
                    }
                },
                ValueAnimator.ofFloat(1f, 0.7f, 1f).apply {//Анимируем размер второго кружочка
                    addUpdateListener { animator ->
                        secondCircleSizeScale = animator.animatedValue as Float
                        invalidate()
                    }
                },
                ValueAnimator.ofFloat(1f, 0f, 1f).apply {//Анимируем прозрачность
                    addUpdateListener { animator ->
                        paintSecond.alpha =  ((animator.animatedValue as Float) * 255).toInt()
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
            interpolator = this.interpolator
            startDelay = 200
            duration = 1000
        }.start()
    }

    private fun startSecondAnimation() {
        AnimatorSet().apply {
            playTogether(
                ValueAnimator.ofFloat(secondCircleXPos - firstCircleXPos, 0f).apply { //Анимируем положение по X первого кружочка
                    addUpdateListener { animator ->
                        firstXPos = animator.animatedValue as Float
                        invalidate()
                    }
                },
                ValueAnimator.ofFloat(-(secondCircleXPos - firstCircleXPos), 0f).apply { //Анимируем положение по X второго кружочка
                    addUpdateListener { animator ->
                        secondXPos = animator.animatedValue as Float
                        invalidate()
                    }
                },
            )
            addListener(object : AnimatorListener {
                override fun onAnimationStart(animation: Animator) {

                }

                override fun onAnimationEnd(animation: Animator) {
                    alphaCoeff = 1f
                    startFirstAnimation()
                }

                override fun onAnimationCancel(animation: Animator) {

                }

                override fun onAnimationRepeat(animation: Animator) {

                }
            })
            interpolator = this.interpolator
            startDelay = 200
            duration = 700
        }.start()
    }

    override fun onDraw(canvas: Canvas?) {
        canvas ?: return

        super.onDraw(canvas)

        val firstCircleCx = this.firstCircleXPos + firstXPos
        val secondCircleCx = this.secondCircleXPos + secondXPos

        canvas.drawCircle(firstCircleCx, cirlesYPos, radius * firstCircleSizeScale, paintFirst)
        canvas.drawCircle(secondCircleCx, cirlesYPos, radius * secondCircleSizeScale, paintSecond)
    }
}