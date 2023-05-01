package ru.otus.animations


import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.core.animation.doOnEnd


class AnimationView
@JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0

) :View(context, attributeSet, defStyleAttr) {

    var r = 0f
    var a = 0

    private val paint = Paint().apply {
        color = context.getColor(R.color.circle3)
        style = Paint.Style.FILL
        strokeWidth = 2f
    }
    private val deltaR = PropertyValuesHolder.ofFloat("r",0f, 80f )
    private val deltaAlpha = PropertyValuesHolder.ofInt("a", 0, 50)
    private val animator = ValueAnimator().apply {
        doOnEnd { playAnimation(true) }
        duration = 1000
        interpolator = LinearInterpolator()
        setValues(deltaR, deltaAlpha)
        addUpdateListener {
            r = it.getAnimatedValue("r") as Float
            a = it.getAnimatedValue("a") as Int
            this@AnimationView.invalidate()
        }
    }

    override fun onDraw(canvas: Canvas){
        super.onDraw(canvas)
        canvas.drawCircle(this.width.toFloat()/2, this.height.toFloat()/2, r+40, paint.apply { alpha = 255 - a})
        canvas.drawCircle(this.width.toFloat()/2, this.height.toFloat()/2, r+120, paint.apply { alpha = 200 - a})
        canvas.drawCircle(this.width.toFloat()/2, this.height.toFloat()/2, r+200, paint.apply { alpha = 130 - a})
        canvas.drawCircle(this.width.toFloat()/2, this.height.toFloat()/2, r+280, paint.apply { alpha = 50 - a})

    }

    fun playAnimation(isStarted:Boolean){
        if(isStarted){
            if(animator.isPaused) animator.resume()
            else animator.start()
        }
        else animator.pause()
    }


}