package ru.otus.animations

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.RelativeLayout
import kotlin.math.min

class RippleLayout : RelativeLayout {
    private var rippleAutomaticStartOfAnimation = false
    private var rippleColor = 0
    private var rippleStrokeWidth = 0f
    private var rippleRadius = 0f
    private var rippleDurationTime = 0
    private var rippleAmount = 0
    private var rippleDelay = 0
    private var rippleScale = 0f
    private var rippleType = 0
    private var paint: Paint? = null

    private var isRippleAnimationRunning = false
    private var animatorSet: AnimatorSet? = null

    private var rippleParams: LayoutParams? = null
    private var animatorList =  mutableListOf<Animator>()
    private val rippleViewList = mutableListOf<RippleView>()

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
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.RippleLayout)

        rippleAutomaticStartOfAnimation = typedArray.getBoolean(
            R.styleable.RippleLayout_rippleAutomaticStartOfAnimation,
            DEFAULT_AUTOMATIC_START_OF_ANIMATION)

        rippleColor = typedArray.getColor(
            R.styleable.RippleLayout_rippleColor,
            resources.getColor(R.color.rippleColor)
        )
        rippleStrokeWidth = typedArray.getDimension(
            R.styleable.RippleLayout_rippleStrokeWidth,
            resources.getDimension(R.dimen.rippleStrokeWidth)
        )
        rippleRadius = typedArray.getDimension(
            R.styleable.RippleLayout_rippleRadius,
            resources.getDimension(R.dimen.rippleRadius)
        )
        rippleDurationTime =
            typedArray.getInt(R.styleable.RippleLayout_rippleDuration, DEFAULT_DURATION_TIME)
        rippleAmount =
            typedArray.getInt(R.styleable.RippleLayout_rippleAmount, DEFAULT_RIPPLE_COUNT)
        rippleScale = typedArray.getFloat(R.styleable.RippleLayout_rippleScale, DEFAULT_SCALE)
        rippleType = typedArray.getInt(R.styleable.RippleLayout_rippleType, DEFAULT_FILL_TYPE)
        typedArray.recycle()
        rippleDelay = rippleDurationTime / rippleAmount

        paint = Paint()
        paint?.isAntiAlias = true
        if (rippleType == DEFAULT_FILL_TYPE) {
            rippleStrokeWidth = 0f
            paint?.style = Paint.Style.FILL
        } else
            paint?.style = Paint.Style.STROKE
        paint?.setColor(rippleColor)

        rippleParams = LayoutParams(
            (2 * (rippleRadius + rippleStrokeWidth)).toInt(),
            (2 * (rippleRadius + rippleStrokeWidth)).toInt()
        )
        rippleParams?.addRule(CENTER_IN_PARENT, TRUE)

        animatorSet = AnimatorSet()
        animatorSet?.interpolator = AccelerateDecelerateInterpolator()

        for (i in 0 until rippleAmount) {
            val rippleView = RippleView(getContext())
            addView(rippleView, rippleParams)

            rippleViewList.add(rippleView)

            val scaleXAnimator = ObjectAnimator.ofFloat(
                rippleView, "ScaleX", 1.0f, rippleScale
            ).apply {
                    repeatCount = ObjectAnimator.INFINITE
                    repeatMode = ObjectAnimator.RESTART
                    setStartDelay((i * rippleDelay).toLong())
                    setDuration(rippleDurationTime.toLong())
            }
            animatorList.add(scaleXAnimator)

            val scaleYAnimator = ObjectAnimator.ofFloat(
                rippleView, "ScaleY", 1.0f, rippleScale
            ).apply {
                    repeatCount = ObjectAnimator.INFINITE
                    repeatMode = ObjectAnimator.RESTART
                    setStartDelay((i * rippleDelay).toLong())
                    setDuration(rippleDurationTime.toLong())
            }
            animatorList.add(scaleYAnimator)

            val alphaAnimator = ObjectAnimator.ofFloat(
                rippleView, "Alpha", 1.0f, 0f
            ).apply {
                repeatCount = ObjectAnimator.INFINITE
                repeatMode = ObjectAnimator.RESTART
                setStartDelay((i * rippleDelay).toLong())
                setDuration(rippleDurationTime.toLong())
            }
            animatorList.add(alphaAnimator)
        }
        animatorSet?.playTogether(animatorList)

        if (rippleAutomaticStartOfAnimation)
            startStopRippleAnimation()
    }

    private inner class RippleView(context: Context?) : View(context) {
        init {
            this.visibility = INVISIBLE
        }

        override fun onDraw(canvas: Canvas) {
            val radius = min(width, height) / 2
            paint?.let {
                canvas.drawCircle(
                    radius.toFloat(),
                    radius.toFloat(),
                    radius - rippleStrokeWidth,
                    it
                )
            }
        }
    }

    fun startStopRippleAnimation(){
        if (isRippleAnimationRunning) stopRippleAnimation()
        else startRippleAnimation()
    }

    private fun startRippleAnimation() {
        if (!isRippleAnimationRunning) {
            for (rippleView in rippleViewList) {
                rippleView.visibility = VISIBLE
            }
            animatorSet?.start()
            isRippleAnimationRunning = true
        }
    }

    private fun stopRippleAnimation() {
        if (isRippleAnimationRunning) {
            animatorSet?.end()
            isRippleAnimationRunning = false
        }
    }

    companion object {
        private const val TAG = "RippleLayout"
        private const val DEFAULT_AUTOMATIC_START_OF_ANIMATION = false
        private const val DEFAULT_RIPPLE_COUNT = 6
        private const val DEFAULT_DURATION_TIME = 3000
        private const val DEFAULT_SCALE = 3.0f
        private const val DEFAULT_FILL_TYPE = 0
    }
}