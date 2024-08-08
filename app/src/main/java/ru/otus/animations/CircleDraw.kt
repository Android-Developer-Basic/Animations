package ru.otus.animations

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

open class CircleDraw @JvmOverloads constructor(
    context: Context,
    attr: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(
context,
attr,
defStyleAttr
) {
    private val paint = Paint().apply {
        color = Color.BLACK
        strokeWidth = 2F
    }

    private var radius = 100F

    fun setColor(color: Int) {
        paint.color = color
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawCircle((width / 2).toFloat(), (height / 2).toFloat(), radius, paint)
    }
}