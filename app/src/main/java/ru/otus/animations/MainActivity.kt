package ru.otus.animations

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationSet
import android.view.animation.LinearInterpolator
import androidx.core.graphics.scaleMatrix

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private val durationValue = 1000L
    private lateinit var pinkCircle: View
    private lateinit var violetCircle: View
    private lateinit var firstRippleCircle: View
    private lateinit var secondRippleCircle: View
    private lateinit var thirdRippleCircle: View
    private lateinit var fourthRippleCircle: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pinkCircle = findViewById(R.id.firstCircle)
        violetCircle = findViewById(R.id.secondCircle)
        firstRippleCircle = findViewById(R.id.firstRippleCircle)
        secondRippleCircle = findViewById(R.id.secondRippleCircle)
        thirdRippleCircle = findViewById(R.id.thirdRippleCircle)
        fourthRippleCircle = findViewById(R.id.fourthRippleCircle)
        pinkCircle.setOnClickListener {
           set()
        }
        firstRippleCircle.setOnClickListener {
            set2()
        }
    }

    private fun firstRippleSet(): AnimatorSet {
        return AnimatorSet().apply {
            playTogether(firstCircleAlpha(), firstCircleScaleX(), firstCircleScaleY())
        }
    }

    private fun secondRippleSet(): AnimatorSet {
        return AnimatorSet().apply {
            playTogether(secondCircleAlpha(), secondCircleScaleX(), secondCircleScaleY())
        }
    }

    private fun thirdRippleSet(): AnimatorSet {
        return AnimatorSet().apply {
            playTogether(thirdCircleAlpha(), thirdCircleScaleX(), thirdCircleScaleY())
        }
    }

    private fun fourthRippleSet(): AnimatorSet {
        return AnimatorSet().apply {
            playTogether(fourthCircleAlpha(), fourthCircleScaleX(), fourthCircleScaleY())
        }
    }

    private fun set2(): AnimatorSet {
        return AnimatorSet().apply {
            playTogether(firstRippleSet(), secondRippleSet(), thirdRippleSet(), fourthRippleSet())
            start()
        }
    }

    private fun set(): AnimatorSet {
        val first = AnimatorSet().apply {
            playTogether(pinkCircleMoveReverse(), pinkCircleAlpha(), pinkCircleSizeY(), pinkCircleSizeX())
        }
        val second = AnimatorSet().apply {
            playSequentially(first, pinkCircleMove())
        }
        return AnimatorSet().apply {
            playTogether(second, violetCircleAnimation())
            start()
        }
    }


    private fun pinkCircleMoveReverse(): ObjectAnimator {
        return ObjectAnimator.ofFloat(pinkCircle, "translationX", -250f).apply {
            duration = durationValue
            interpolator = LinearInterpolator()
        }
    }

    private fun pinkCircleAlpha(): ObjectAnimator {
         return ObjectAnimator.ofFloat(pinkCircle, "alpha", 1f, 0.5f, 1f).apply {
            duration = durationValue
            interpolator = LinearInterpolator()
        }
    }

    private fun pinkCircleSizeX(): ObjectAnimator {
        return ObjectAnimator.ofFloat(pinkCircle, "scaleX", pinkCircle.scaleX, 0.5f, pinkCircle.scaleX).apply {
            duration = durationValue
            interpolator = LinearInterpolator()
        }
    }

    private fun pinkCircleSizeY(): ObjectAnimator {
        return ObjectAnimator.ofFloat(pinkCircle, "scaleY", pinkCircle.scaleY, 0.5f, pinkCircle.scaleY).apply {
            duration = durationValue
            interpolator = LinearInterpolator()
        }
    }

    private fun pinkCircleMove(): ObjectAnimator {
        return ObjectAnimator.ofFloat(pinkCircle, "translationX", 15f).apply {
            duration = durationValue
            interpolator = LinearInterpolator()
        }
    }

    private fun violetCircleAnimation(): ObjectAnimator {
        return ObjectAnimator.ofFloat(violetCircle, "translationX", 250f).apply {
            duration = durationValue
            repeatMode = ObjectAnimator.REVERSE
            repeatCount = 1
            interpolator = LinearInterpolator()
        }
    }

    private fun fourthCircleAlpha(): ObjectAnimator {
        return ObjectAnimator.ofFloat(fourthRippleCircle, "alpha", 0.3f, 0f).apply {
            duration = durationValue
            repeatCount = 500
            interpolator = LinearInterpolator()
        }
    }

    private fun fourthCircleScaleX(): ObjectAnimator {
        return ObjectAnimator.ofFloat(fourthRippleCircle, "scaleX", 0.75f, 1f).apply {
            duration = durationValue
            repeatCount = 500
            interpolator = LinearInterpolator()
        }
    }

    private fun fourthCircleScaleY(): ObjectAnimator {
        return ObjectAnimator.ofFloat(fourthRippleCircle, "scaleY", 0.75f, 1f).apply {
            duration = durationValue
            repeatCount = 500
            interpolator = LinearInterpolator()
        }
    }

    private fun thirdCircleAlpha(): ObjectAnimator {
        return ObjectAnimator.ofFloat(thirdRippleCircle, "alpha", 0.5f, 0.3f).apply {
            duration = durationValue
            repeatCount = 500
            interpolator = LinearInterpolator()
        }
    }

    private fun thirdCircleScaleX(): ObjectAnimator {
        return ObjectAnimator.ofFloat(thirdRippleCircle, "scaleX", 0.5f, 1f).apply {
            duration = durationValue
            repeatCount = 500
            interpolator = LinearInterpolator()
        }
    }

    private fun thirdCircleScaleY(): ObjectAnimator {
        return ObjectAnimator.ofFloat(thirdRippleCircle, "scaleY", 0.5f, 1f).apply {
            duration = durationValue
            repeatCount = 500
            interpolator = LinearInterpolator()
        }
    }

    private fun secondCircleAlpha(): ObjectAnimator {
        return ObjectAnimator.ofFloat(secondRippleCircle, "alpha", 0.7f, 0.5f).apply {
            duration = durationValue
            repeatCount = 500
            interpolator = LinearInterpolator()
        }
    }

    private fun secondCircleScaleX(): ObjectAnimator {
        return ObjectAnimator.ofFloat(secondRippleCircle, "scaleX", 0.25f, 1f).apply {
            duration = durationValue
            repeatCount = 500
            interpolator = LinearInterpolator()
        }
    }

    private fun secondCircleScaleY(): ObjectAnimator {
        return ObjectAnimator.ofFloat(secondRippleCircle, "scaleY", 0.25f, 1f).apply {
            duration = durationValue
            repeatCount = 500
            interpolator = LinearInterpolator()
        }
    }

    private fun firstCircleAlpha(): ObjectAnimator {
        return ObjectAnimator.ofFloat(firstRippleCircle, "alpha", 1f, 0.3f).apply {
            duration = durationValue
            repeatCount = 500
            interpolator = LinearInterpolator()
        }
    }

    private fun firstCircleScaleX(): ObjectAnimator {
        return ObjectAnimator.ofFloat(firstRippleCircle, "scaleX", 0f, 1f).apply {
            duration = durationValue
            repeatCount = 500
            interpolator = LinearInterpolator()
        }
    }

    private fun firstCircleScaleY(): ObjectAnimator {
        return ObjectAnimator.ofFloat(firstRippleCircle, "scaleY", 0f, 1f).apply {
            duration = durationValue
            repeatCount = 500
            interpolator = LinearInterpolator()
        }
    }
}