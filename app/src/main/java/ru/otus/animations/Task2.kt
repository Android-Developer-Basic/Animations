package ru.otus.animations

import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import androidx.fragment.app.Fragment

class Task2 : Fragment(R.layout.task2) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val crd = view.findViewById<CircleRippleDraw>(R.id.circleRipple)

        fun startAnimation() {
            val delay = 500L
            (crd.circles.indices).forEach { index ->
                val radiusAnimatedValue = PropertyValuesHolder.ofFloat("radius", 0f, 300f)
                val alphaAnimatedValue = PropertyValuesHolder.ofInt("alpha", 0xFF, 0x0)
                ValueAnimator.ofPropertyValuesHolder(radiusAnimatedValue, alphaAnimatedValue)
                    .apply {
                        duration = crd.circles.size * delay
                        interpolator = LinearInterpolator()
                        repeatCount = Animation.INFINITE
                        startDelay = index * delay
                        addUpdateListener {
                            crd.circles[index].radius = it.getAnimatedValue("radius") as Float
                            crd.circles[index].alpha = it.getAnimatedValue("alpha") as Int
                            crd.invalidate()
                        }
                        start()
                    }
            }
        }
        crd.setOnClickListener{
            startAnimation()
        }
    }
}