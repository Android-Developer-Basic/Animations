package ru.otus.animations

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.PathInterpolator
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val metrics = ScreenMetricsCompat.getScreenSize(this)

        val circleA = findViewById<CircleTaskA>(R.id.circle_a).apply {
            setColor(getColor(R.color.purple))
        }
        val circleB = findViewById<CircleTaskA>(R.id.circle_b).apply {
            setColor(getColor(R.color.pink))
        }

        val commonInterpolator = PathInterpolator(0.5f, 1f, 0.35f, 0.95f)
        val durationTaskA = 1200L


        val listAnimators = mapOf(
            "animatorA" to
                    ObjectAnimator.ofFloat(circleA, View.X, 0F, metrics.width * 0.35F),
            "animatorAA" to
                    ObjectAnimator.ofFloat(circleA, View.X, metrics.width * 0.35F, 0F),
            "animatorB" to
                    ObjectAnimator.ofFloat(
                        circleB,
                        View.X,
                        metrics.width * 0.5F,
                        metrics.width * 0.15F
                    ),
            "animatorBB" to
                    ObjectAnimator.ofFloat(
                        circleB,
                        View.X,
                        metrics.width * 0.15F,
                        metrics.width * 0.5F
                    ),
            "animatorBAlpha" to
                    ObjectAnimator.ofFloat(circleB, View.ALPHA, 1F, 0.5F, 0F, 0F, 0f, 0f, 1F),
            "animatorBScaleX" to
                    ObjectAnimator.ofFloat(circleB, View.SCALE_X, 1F, 0.4f, 0.5F, 1F),
            "animatorBScaleY" to
                    ObjectAnimator.ofFloat(circleB, View.SCALE_Y, 1F, 0.4f, 0.5F, 1F)

        )

         listAnimators.forEach {
            it.value.apply {
                interpolator = commonInterpolator
                duration = durationTaskA
                repeatCount = Animator.DURATION_INFINITE.toInt()
            }
        }

        val setAnimator = AnimatorSet()
        setAnimator.playSequentially(listAnimators["animatorA"], listAnimators["animatorAA"])
        setAnimator.playSequentially(listAnimators["animatorB"], listAnimators["animatorBB"])
        setAnimator.playTogether(
            listAnimators["animatorBAlpha"],
            listAnimators["animatorBScaleX"],
            listAnimators["animatorBScaleY"]
        )

        var flagPause = 0

        circleA.setOnClickListener {
            if (flagPause == 0) {
                flagPause = 1
                setAnimator.start()
            } else {
                flagPause = 0
                setAnimator.pause()
            }
        }
    }
}