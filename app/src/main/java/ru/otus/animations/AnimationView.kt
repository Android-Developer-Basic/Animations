package ru.otus.animations


import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.core.animation.doOnEnd


class AnimationView
constructor(
    context: Context

    ) :View(context) {

    private var r = 0f
    private var a = 0

    private val paint = Paint().apply {
        color = context.getColor(R.color.circle3)
        style = Paint.Style.FILL
        strokeWidth = 2f
    }
    private val radiusArray = arrayOf(40,120,200,280)
    private val alphaArray = arrayOf(255, 200, 130, 50)
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
        drawCircle(canvas)
    }

    fun playAnimation(isStarted:Boolean){
        if(isStarted){
            if(animator.isPaused) animator.resume()
            else animator.start()
        }
        else animator.pause()
    }
    private fun drawCircle(canvas:Canvas){
        for(i in radiusArray.indices){
            canvas.drawCircle(
                width.toFloat()/2,
                height.toFloat()/2,
                r+radiusArray[i],
                paint.apply { alpha = alphaArray[i] - a})
        }
    }

}