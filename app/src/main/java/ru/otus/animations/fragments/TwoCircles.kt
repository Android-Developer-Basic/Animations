package ru.otus.animations.fragments

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.fragment.app.Fragment
import ru.otus.animations.R
import ru.otus.animations.elements.Circle

class TwoCircles : Fragment(R.layout.two_circles) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val circleBlue = view.findViewById<Circle>(R.id.circle_blue)
        val circlePink = view.findViewById<Circle>(R.id.circle_pink)

        circleBlue.circles.add(Circle.CircleParameters(radius = 200f))
        circlePink.circles.add(Circle.CircleParameters(radius = 200f))

        circleBlue.apply { setColor(Color.BLUE) }
        circlePink.apply { setColor(Color.RED) }


        circleBlue.setOnClickListener {
            startAnimation(circlePink, circleBlue)
        }
        circlePink.setOnClickListener {
            startAnimation(circlePink, circleBlue)
        }
    }

    private fun startAnimation(circlePink: Circle, circleBlue: Circle) {
        val pinkTranslationX =
            ObjectAnimator.ofFloat(circlePink, "translationX", 0f, -circlePink.width.toFloat(), 0f)
        val pinkScaleUpX = ObjectAnimator.ofFloat(circlePink, "scaleX", 1f, 0.5f, 1f, 1f, 1.2f, 1f)
        val pinkScaleUpY = ObjectAnimator.ofFloat(circlePink, "scaleY", 1f, 0.5f, 1f, 1f, 1.2f, 1f)
        val pinkFadeOut = ObjectAnimator.ofFloat(circlePink, "alpha", 1f, 0f, 1f, 1f, 1f, 1f)


        val pinkAnimatorSet = AnimatorSet().apply {
            duration = 2000
            interpolator = AccelerateDecelerateInterpolator()
            playTogether(pinkTranslationX, pinkScaleUpX, pinkScaleUpY, pinkFadeOut)

        }


        val blueTranslationX =
            ObjectAnimator.ofFloat(circleBlue, "translationX", 0f, circleBlue.width.toFloat(), 0f)
        val blueScaleUpX = ObjectAnimator.ofFloat(circleBlue, "scaleX", 1f, 1.1f, 1f, 1f, 1f, 1f)
        val blueScaleUpY = ObjectAnimator.ofFloat(circleBlue, "scaleY", 1f, 1.1f, 1f, 1f, 1f, 1f)

        val blueAnimatorSet = AnimatorSet().apply {
            duration = 2000
            interpolator = AccelerateDecelerateInterpolator()
            playTogether(
                blueTranslationX, blueScaleUpX, blueScaleUpY
            )
        }
        pinkAnimatorSet.start()
        blueAnimatorSet.start()
    }
}
