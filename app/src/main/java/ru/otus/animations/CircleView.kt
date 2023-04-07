package ru.otus.animations

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class CircleView @JvmOverloads constructor(
    context: Context,
    attr: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(
    context,
    attr,
    defStyleAttr
) {
    private val paint = Paint().apply {
        strokeWidth = 2F
    }

    private var radius = 90F

    override fun onFinishInflate() {
        super.onFinishInflate()
        setOnClickListener {
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawCircle((width / 2).toFloat(), (height / 2).toFloat(), radius, paint)
    }

    fun setColor(color: Int) {
        paint.color = color
    }

    fun setRadius(newRadius: Float) {
        radius = newRadius
    }

}
