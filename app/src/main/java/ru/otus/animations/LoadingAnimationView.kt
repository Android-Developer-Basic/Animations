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

class LoadingAnimationView : View {
    private var autoStartOfAnimation = DEFAULT_AUTO_START_OF_ANIMATION;
    private var spaceBetween = DEFAULT_SPACE_BETWEEN
    private var durationAnimation: Long = DEFAULT_DURATION_ANIMATION.toLong()
    private var delayAnimation: Long = DEFAULT_START_DELAY_ANIMATION.toLong()
    private var leftCircleColor = DEFAULT_LEFT_CIRCLE_COLOR.toInt()
    private var rightCircleColor = DEFAULT_RIGHT_CIRCLE_COLOR.toInt()

    private val centerX get() = width / 2f;
    private val centerY get() = height / 2f
    private val radius get() = minOf(width, height) / 4f - spaceBetween

    private val leftCircleOriginalPositionX: Float get() = centerX - radius - (spaceBetween / 2)
    private var leftDx = 0f
    private var leftScale = 1f
    private val rightCircleOriginalPositionX: Float get() = centerX + radius + (spaceBetween / 2)
    private var rightDx = 0f
    private var rightScale = 1f
    private var rightAlpha = 255

    private val paint = Paint()
    private var isRunningAnimation = false

    constructor(context: Context?) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        if (isInEditMode) return
        requireNotNull(attrs) { "Attributes should be provided to this view," }

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.LoadingAnimationView)

        autoStartOfAnimation = typedArray.getBoolean(
            R.styleable.LoadingAnimationView_autoStartOfAnimation,
            DEFAULT_AUTO_START_OF_ANIMATION
        )

        leftCircleColor = typedArray.getColor(
            R.styleable.LoadingAnimationView_leftCircleColor,
            DEFAULT_LEFT_CIRCLE_COLOR.toInt()
        )

        rightCircleColor = typedArray.getColor(
            R.styleable.LoadingAnimationView_rightCircleColor,
            DEFAULT_RIGHT_CIRCLE_COLOR.toInt()
        )

        spaceBetween = typedArray.getInteger(
            R.styleable.LoadingAnimationView_spaceBetween,
            DEFAULT_SPACE_BETWEEN
        )

        durationAnimation = typedArray.getInt(
            R.styleable.LoadingAnimationView_durationAnimation,
            DEFAULT_DURATION_ANIMATION
        ).toLong()

        delayAnimation = typedArray.getInt(
            R.styleable.LoadingAnimationView_startDelayAnimation,
            DEFAULT_START_DELAY_ANIMATION
        ).toLong()
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)

        leftDx = leftCircleOriginalPositionX
        rightDx = rightCircleOriginalPositionX

        if (autoStartOfAnimation)
            startFirstAnimation()
    }

    fun startFirstAnimation(){
        if (isRunningAnimation) return

        isRunningAnimation = true
        val animatorSet = AnimatorSet()
        animatorSet.apply {
            duration = durationAnimation
            startDelay = delayAnimation

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
            duration = durationAnimation
            startDelay = delayAnimation

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
                color = leftCircleColor
            })
        canvas?.drawCircle(rightDx, centerY, radius * rightScale, paint.apply {
                color = rightCircleColor
                alpha = rightAlpha
            })
    }

    companion object {
        private const val DEFAULT_AUTO_START_OF_ANIMATION = false
        private const val DEFAULT_LEFT_CIRCLE_COLOR: Long = 0xFF7c4fff
        private const val DEFAULT_RIGHT_CIRCLE_COLOR: Long = 0xFFff39d3
        private const val DEFAULT_SPACE_BETWEEN = 100
        private const val DEFAULT_DURATION_ANIMATION = 1000
        private const val DEFAULT_START_DELAY_ANIMATION = 0
    }
}