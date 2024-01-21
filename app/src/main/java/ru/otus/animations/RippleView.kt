/*
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
import kotlin.math.min

class RippleView : View {

    private var rippleColor = 0
    private var rippleStrokeWidth = 0f
    private var rippleRadius = 0f
    private var rippleDurationTime = 0
    private var rippleAmount = 0
    private var rippleDelay = 0
    private var rippleScale = 0f
    private var rippleType = 0

    private var paintList = mutableListOf<Paint>()
    private var isRippleAnimationRunning = false
    private var animatorSet: AnimatorSet? = null
    private var animatorList = mutableListOf<Animator>()

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
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.RippleView)

        rippleColor = typedArray.getColor(
            R.styleable.RippleView_rippleColor,
            resources.getColor(R.color.rippleColor)
        )
        rippleStrokeWidth = typedArray.getDimension(
            R.styleable.RippleView_rippleStrokeWidth,
            resources.getDimension(R.dimen.rippleStrokeWidth)
        )
        rippleRadius = typedArray.getDimension(
            R.styleable.RippleView_rippleRadius,
            resources.getDimension(R.dimen.rippleRadius)
        )
        rippleDurationTime =
            typedArray.getInt(R.styleable.RippleView_rippleDuration, DEFAULT_DURATION_TIME)
        rippleAmount =
            typedArray.getInt(R.styleable.RippleView_rippleAmount, DEFAULT_RIPPLE_COUNT)
        rippleScale = typedArray.getFloat(R.styleable.RippleView_rippleScale, DEFAULT_SCALE)
        rippleType = typedArray.getInt(R.styleable.RippleView_rippleType, DEFAULT_FILL_TYPE)
        typedArray.recycle()
        rippleDelay = rippleDurationTime / rippleAmount

        animatorSet = AnimatorSet()
        animatorSet?.interpolator = AccelerateDecelerateInterpolator()

        for (i in 0 until rippleAmount) {
            val paint = getPaint()
            paintList.add(i, paint)
            val scaleXAnimator = ObjectAnimator.ofFloat(paint, "ScaleX", 1.0f, rippleScale)
            scaleXAnimator.repeatCount = ObjectAnimator.INFINITE
            scaleXAnimator.repeatMode = ObjectAnimator.RESTART
            scaleXAnimator.setStartDelay((i * rippleDelay).toLong())
            scaleXAnimator.setDuration(rippleDurationTime.toLong())
            animatorList.add(scaleXAnimator)

            val scaleYAnimator = ObjectAnimator.ofFloat(paint, "ScaleY", 1.0f, rippleScale)
            scaleYAnimator.repeatCount = ObjectAnimator.INFINITE
            scaleYAnimator.repeatMode = ObjectAnimator.RESTART
            scaleYAnimator.setStartDelay((i * rippleDelay).toLong())
            scaleYAnimator.setDuration(rippleDurationTime.toLong())
            animatorList.add(scaleYAnimator)

            val alphaAnimator = ObjectAnimator.ofFloat(paint, "Alpha", 1.0f, 0f)
            alphaAnimator.repeatCount = ObjectAnimator.INFINITE
            alphaAnimator.repeatMode = ObjectAnimator.RESTART
            alphaAnimator.setStartDelay((i * rippleDelay).toLong())
            alphaAnimator.setDuration(rippleDurationTime.toLong())
            animatorList.add(alphaAnimator)
        }

        animatorSet?.playTogether(animatorList)
    }

    private fun getPaint() : Paint{
        val paint = Paint()
        paint.isAntiAlias = true
        if (rippleType == DEFAULT_FILL_TYPE) {
            rippleStrokeWidth = 0f
            paint.style = Paint.Style.FILL
        } else
            paint.style = Paint.Style.STROKE
        paint.setColor(rippleColor)

        return paint
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val radius = min(width, height) / 2

        paintList.forEach {
            canvas?.drawCircle(
                radius.toFloat(),
                radius.toFloat(),
                radius - rippleStrokeWidth,
                it)
        }
        invalidate()
    }

    companion object {
        private const val DEFAULT_RIPPLE_COUNT = 6
        private const val DEFAULT_DURATION_TIME = 3000
        private const val DEFAULT_SCALE = 6.0f
        private const val DEFAULT_FILL_TYPE = 0
    }
}*/
