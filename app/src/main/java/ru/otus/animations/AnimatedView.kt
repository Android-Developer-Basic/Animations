package ru.otus.animations

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View

abstract class AnimatedView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    abstract val duration: Long

    protected var circles: List<Circle> = emptyList()

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        circles.forEach { canvas?.drawCircle(it) }
        invalidate()
    }

    abstract fun startAnimation()

}