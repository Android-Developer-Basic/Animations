package ru.otus.animations.ui

import android.animation.ValueAnimator
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.animation.doOnStart
import androidx.fragment.app.Fragment
import ru.otus.animations.R

class FragmentBottom: Fragment(R.layout.fragment_bottom) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("app", "fragment_bottom on view")

        val animator = ValueAnimator.ofFloat(0f, 1f)
        val animator2 = ValueAnimator.ofFloat(0f, 1f)
        val animator3 = ValueAnimator.ofFloat(0f, 1f)

        view.findViewById<ru.otus.animations.figure.Ripple>(R.id.ripple).apply {
            setOnClickListener() {
                animator.duration = 2000
                animator.repeatCount = ValueAnimator.INFINITE
                animator.addUpdateListener { valueAnimator ->
                    val animatedValue = valueAnimator.animatedValue as Float
                    scaleX = animatedValue * 6
                    scaleY = animatedValue * 6
                    alpha = 1 - animatedValue
                }
                animator.start()
            }
        }
        view.findViewById<ru.otus.animations.figure.Ripple>(R.id.ripple2).apply {
            animator2.duration = 2000
            animator2.repeatCount = ValueAnimator.INFINITE
            animator2.addUpdateListener { valueAnimator ->
                val animatedValue = valueAnimator.animatedValue as Float
                scaleX = animatedValue * 6
                scaleY = animatedValue * 6
                alpha = 1 - animatedValue
            }

            animator.doOnStart {
                animator2.startDelay = 500
                animator2.start()
            }
            animator2.doOnStart {
                visibility = View.VISIBLE
            }
        }
        view.findViewById<ru.otus.animations.figure.Ripple>(R.id.ripple3).apply {
            animator3.duration = 2100
            animator3.repeatCount = ValueAnimator.INFINITE
            animator3.addUpdateListener { valueAnimator ->
                val animatedValue = valueAnimator.animatedValue as Float
                scaleX = animatedValue * 6
                scaleY = animatedValue * 6
                alpha = 1 - animatedValue
            }

            animator.doOnStart {
                animator3.startDelay = 1000
                animator3.start()
            }
            animator3.doOnStart {
                visibility = View.VISIBLE
            }
        }
    }
}