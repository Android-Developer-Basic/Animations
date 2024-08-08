package ru.otus.animations

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    private lateinit var circleBlue: ImageView
    private lateinit var circlePink: ImageView

    private lateinit var circle1: CircleView
    private lateinit var circle2: CircleView
    private lateinit var circle3: CircleView
    private lateinit var circle4: CircleView
    private lateinit var circle5: CircleView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        circleBlue = findViewById(R.id.circle_blue)
        circlePink = findViewById(R.id.circle_pink)

        circle1 = findViewById(R.id.circle_1)
        circle2 = findViewById(R.id.circle_2)
        circle3 = findViewById(R.id.circle_3)
        circle4 = findViewById(R.id.circle_4)
        circle5 = findViewById(R.id.circle_5)

        circleBlue.setOnClickListener {
            startTikTokLoadingAnimation(circlePink, circleBlue)
        }

        circle2.postDelayed({ circle2.secondTask() }, 400)
        circle3.postDelayed({ circle3.secondTask() }, 800)
        circle4.postDelayed({ circle4.secondTask() }, 1200)
        circle5.postDelayed({ circle5.secondTask() }, 1600)

        circle1.setOnClickListener {
            circle1.secondTask()
            circle2.secondTask()
            circle3.secondTask()
            circle4.secondTask()
            circle5.secondTask()
        }
    }

    private fun startTikTokLoadingAnimation(circlePink: ImageView, circleBlue: ImageView) {

        val pinkTranslationX = ObjectAnimator.ofFloat(circlePink, "translationX", 0f, 175f)
        val pinkScaleUpX = ObjectAnimator.ofFloat(circlePink, "scaleX", 1f, 1.2f)
        val pinkScaleUpY = ObjectAnimator.ofFloat(circlePink, "scaleY", 1f, 1.2f)
        val pinkFadeOut = ObjectAnimator.ofFloat(circlePink, "alpha", 1f, 0f)
        val pinkResetAlpha = ObjectAnimator.ofFloat(circlePink, "alpha", 0f, 1f)
        val pinkResetPosition = ObjectAnimator.ofFloat(circlePink, "translationX", 175f, 0f)
        val pinkScaleDownX = ObjectAnimator.ofFloat(circlePink, "scaleX", 1.2f, 1f)
        val pinkScaleDownY = ObjectAnimator.ofFloat(circlePink, "scaleY", 1.2f, 1f)

        val pinkAnimatorSet = AnimatorSet().apply {
            duration = 800
            interpolator = AccelerateDecelerateInterpolator()
            playSequentially(
                AnimatorSet().apply {
                    playTogether(pinkTranslationX, pinkScaleUpX, pinkScaleUpY)
                },
                AnimatorSet().apply {
                    playTogether(pinkFadeOut, pinkScaleDownX, pinkScaleDownY, pinkResetPosition)
                },
                pinkResetAlpha
            )
        }

        val blueTranslationX = ObjectAnimator.ofFloat(circleBlue, "translationX", 0f, -150f)
        val blueScaleDownX = ObjectAnimator.ofFloat(circleBlue, "scaleX", 1f, 0.8f)
        val blueScaleDownY = ObjectAnimator.ofFloat(circleBlue, "scaleY", 1f, 0.8f)
        val blueResetPosition = ObjectAnimator.ofFloat(circleBlue, "translationX", -150f, 0f)
        val blueScaleUpX = ObjectAnimator.ofFloat(circleBlue, "scaleX", 0.8f, 1f)
        val blueScaleUpY = ObjectAnimator.ofFloat(circleBlue, "scaleY", 0.8f, 1f)

        val blueAnimatorSet = AnimatorSet().apply {
            duration = 800
            interpolator = AccelerateDecelerateInterpolator()
            playSequentially(
                AnimatorSet().apply {
                    playTogether(blueTranslationX, blueScaleDownX, blueScaleDownY)
                },
                AnimatorSet().apply {
                    playTogether(blueResetPosition, blueScaleUpX, blueScaleUpY)
                }
            )
        }
        pinkAnimatorSet.start()
        blueAnimatorSet.start()
    }
}
