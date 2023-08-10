package ru.otus.animations

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val secondCircle = findViewById<CircleView>(R.id.cyan)
        secondCircle.setColor(Color.CYAN)

        val firstCircle = findViewById<CircleView>(R.id.magenta)
        firstCircle.setColor(Color.MAGENTA)
        val animator1 = ObjectAnimator.ofFloat(
            firstCircle,
            View.X,
            firstCircle.width / 2F + 80,
            firstCircle.width / 2F + 400
        )
            .apply {
                interpolator = LinearInterpolator()
                duration = 500
            }
        val animator12 = ObjectAnimator.ofFloat(
            firstCircle,
            View.X,
            firstCircle.width / 2F + 400,
            firstCircle.width / 2F + 80
        )
            .apply {
                interpolator = LinearInterpolator()
                duration = 500
            }
        val animator2 = ObjectAnimator.ofFloat(
            secondCircle,
            View.X,
            secondCircle.width / 2F + 400,
            secondCircle.width / 2F + 80
        )
            .apply {
                interpolator = LinearInterpolator()
                duration = 500
            }
        val animator3 = ObjectAnimator.ofFloat(secondCircle, View.ALPHA, 1F, 0F, 1F)
            .apply {
                interpolator = LinearInterpolator()
                duration = 500
            }
        val animator4 = ObjectAnimator.ofFloat(secondCircle, View.SCALE_X, 1F, 0F, 1F)
            .apply {
                interpolator = LinearInterpolator()
                duration = 500
            }
        val animator5 = ObjectAnimator.ofFloat(secondCircle, View.SCALE_Y, 1F, 0F, 1F)
            .apply {
                interpolator = LinearInterpolator()
                duration = 500
            }
        val animator6 = ObjectAnimator.ofFloat(
            secondCircle,
            View.X,
            secondCircle.width / 2F + 80,
            secondCircle.width / 2F + 400
        )
            .apply {
                interpolator = LinearInterpolator()
                duration = 500
            }

        val animatorSet = AnimatorSet()
        val animatorSet1 = AnimatorSet()
        val animatorSet2 = AnimatorSet()

        animatorSet1.playTogether(animator1, animator2, animator3, animator4, animator5)
        animatorSet2.playTogether(animator12, animator6)
        animatorSet.playSequentially(animatorSet1, animatorSet2)
        secondCircle.setOnClickListener {animatorSet.start()}

        val bottomCircle1 = findViewById<CircleView>(R.id.bottomCircle1)
        bottomCircle1.setColor(Color.RED)
        bottomCircle1.setRadius(50F)

        val bottomCircle2 = findViewById<CircleView>(R.id.bottomCircle2)
        bottomCircle2.setColor(Color.RED)
        bottomCircle2.setRadius(100F)

        val bottomCircle3 = findViewById<CircleView>(R.id.bottomCircle3)
        bottomCircle3.setColor(Color.RED)
        bottomCircle3.setRadius(150F)

        val bottomCircle4 = findViewById<CircleView>(R.id.bottomCircle4)
        bottomCircle4.setColor(Color.RED)
        bottomCircle4.setRadius(200F)

        val animatorAl1 = ObjectAnimator.ofFloat(bottomCircle1, View.ALPHA, 1F, 0.75F)
            .apply {interpolator = LinearInterpolator()}
        val animatorAl2 = ObjectAnimator.ofFloat(bottomCircle2, View.ALPHA, 0.75F, 0.6F)
            .apply {interpolator = LinearInterpolator()}
        val animatorAl3 = ObjectAnimator.ofFloat(bottomCircle3, View.ALPHA, 0.6F, 0.3F)
            .apply {interpolator = LinearInterpolator()}
        val animatorAl4 = ObjectAnimator.ofFloat(bottomCircle4, View.ALPHA, 0.3F, 0F)
            .apply {interpolator = LinearInterpolator()}
        val animatorb1x = ObjectAnimator.ofFloat(bottomCircle1, View.SCALE_X, 0F, 1F)
            .apply {interpolator = LinearInterpolator()}
        val animatorb1y = ObjectAnimator.ofFloat(bottomCircle1, View.SCALE_Y, 0F, 1F)
            .apply {interpolator = LinearInterpolator()}
        val animatorb2x = ObjectAnimator.ofFloat(bottomCircle2, View.SCALE_X, 0.5F, 1F)
            .apply {interpolator = LinearInterpolator()}
        val animatorb2y = ObjectAnimator.ofFloat(bottomCircle2, View.SCALE_Y, 0.5F, 1F)
            .apply {interpolator = LinearInterpolator()}
        val animatorb3x = ObjectAnimator.ofFloat(bottomCircle3, View.SCALE_X, 0.67F, 1F)
            .apply {interpolator = LinearInterpolator()}
        val animatorb3y = ObjectAnimator.ofFloat(bottomCircle3, View.SCALE_Y, 0.67F, 1F)
            .apply {interpolator = LinearInterpolator()}
        val animatorb4x = ObjectAnimator.ofFloat(bottomCircle4, View.SCALE_X, 0.75F, 1F)
            .apply {interpolator = LinearInterpolator()}
        val animatorb4y = ObjectAnimator.ofFloat(bottomCircle4, View.SCALE_Y, 0.75F, 1F)
            .apply {interpolator = LinearInterpolator() }

        val bottomAnimatorSet = AnimatorSet()
        val bottomAnimatorSet1 = AnimatorSet()
        val bottomAnimatorSet2 = AnimatorSet()
        val bottomAnimatorSet3 = AnimatorSet()
        val bottomAnimatorSet4 = AnimatorSet()

        bottomAnimatorSet1.playTogether(animatorb1x, animatorb1y, animatorAl1)
        bottomAnimatorSet2.playTogether(animatorb2x, animatorb2y, animatorAl2)
        bottomAnimatorSet3.playTogether(animatorb3x, animatorb3y, animatorAl3)
        bottomAnimatorSet4.playTogether(animatorb4x, animatorb4y, animatorAl4)

        bottomAnimatorSet.playTogether(
            bottomAnimatorSet1,
            bottomAnimatorSet2,
            bottomAnimatorSet3,
            bottomAnimatorSet4,
        )
        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {bottomAnimatorSet1.start()}
        animatorSet.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {}
            override fun onAnimationEnd(animation: Animator) {animatorSet.start()}
            override fun onAnimationCancel(animation: Animator) {}
            override fun onAnimationRepeat(animation: Animator) {}
        })
        bottomAnimatorSet1.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {bottomAnimatorSet2.start()}
            override fun onAnimationEnd(animation: Animator) {bottomAnimatorSet2.start()}
            override fun onAnimationCancel(animation: Animator) {}
            override fun onAnimationRepeat(animation: Animator) {}
        })
        bottomAnimatorSet2.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {bottomAnimatorSet3.start() }
            override fun onAnimationEnd(animation: Animator) {bottomAnimatorSet3.start()}
            override fun onAnimationCancel(animation: Animator) {}
            override fun onAnimationRepeat(animation: Animator) {}
        })
        bottomAnimatorSet3.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {bottomAnimatorSet4.start()}
            override fun onAnimationEnd(animation: Animator) {bottomAnimatorSet4.start()}
            override fun onAnimationCancel(animation: Animator) {}
            override fun onAnimationRepeat(animation: Animator) {}
        })
        bottomAnimatorSet4.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {}
            override fun onAnimationEnd(animation: Animator) {bottomAnimatorSet1.start()}
            override fun onAnimationCancel(animation: Animator) {}
            override fun onAnimationRepeat(animation: Animator) {}
        })

    }
}