package ru.otus.animations

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class Circle
@JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0,
): View(context, attributeSet, defStyleAttr) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
    }
    private var circleRadius = 0f

    init {
        attributeSet?.let { initAttrs(it) }
    }

    private fun initAttrs(attributeSet: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.Circle)
        try {
            paint.color = typedArray.getColor(
                R.styleable.Circle_circleColor,
                Color.BLUE
            )
            circleRadius = typedArray.getDimension(R.styleable.Circle_circleRadius, 30f)
        } finally {
            typedArray.recycle()
        }
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawCircle(
            width.toFloat() / 2,
            height.toFloat() / 2,
            circleRadius,
            paint
        )
    }
}