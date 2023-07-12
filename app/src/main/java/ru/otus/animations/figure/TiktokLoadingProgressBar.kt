package ru.otus.animations.figure

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import ru.otus.animations.R

class TiktokLoadingProgressBar
@JvmOverloads
constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val paint = Paint().apply {
        color = resources.getColor(R.color.tiktok_loading_progressbar_first_figure, null)
        strokeWidth = 2F
    }
    private var radius = 150F

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.drawCircle((width / 2).toFloat(), (height / 2).toFloat(), radius, paint)
    }

    fun setPaint(color: Int) {
        paint.color = color
    }
}