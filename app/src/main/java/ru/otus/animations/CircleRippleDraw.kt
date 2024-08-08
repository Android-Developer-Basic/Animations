package ru.otus.animations

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class CircleRippleDraw @JvmOverloads constructor(
    context: Context,
    attr: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(
context,
attr,
defStyleAttr
) {
    val paint = Paint().apply {
        color = Color.CYAN
        strokeWidth = 2F
    }

    private var startRadius = 100f

    data class CircleParameters(var radius: Float, var alpha: Int)

    val circles = arrayOf(
        CircleParameters(startRadius, 0xFF),
        CircleParameters(0f, 0xFF),
        CircleParameters(0f, 0xFF),
        CircleParameters(0f, 0xFF))

    override fun onDraw(canvas: Canvas) {
        circles.forEach {
            paint.alpha = it.alpha
            canvas.drawCircle(
                (width / 2).toFloat(), (height / 2).toFloat(), it.radius, paint
            )
            super.onDraw(canvas)
        }
    }
}