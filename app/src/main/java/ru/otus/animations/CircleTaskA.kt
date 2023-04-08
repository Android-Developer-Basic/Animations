package ru.otus.animations

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class CircleTaskA @JvmOverloads constructor(
    context: Context,
    attr: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attr, defStyleAttr) {

    private var paint = Paint()

    private var radiusCircle = 150F

    fun setColor (color: Int){
        paint.color = color
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.drawCircle(this.width * 0.65f, this.height / 2f, radiusCircle, paint)
        super.onDraw(canvas)
    }
}