package ru.otus.animations

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View

abstract class AnimatedView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    abstract val duration: Long

    protected val circles: List<Circle> by lazy {
        getAnimationCircles()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        circles.forEach { canvas?.drawCircle(it) }
        invalidate()
    }

    abstract fun startAnimation()

    abstract fun getAnimationCircles(): List<Circle>

}