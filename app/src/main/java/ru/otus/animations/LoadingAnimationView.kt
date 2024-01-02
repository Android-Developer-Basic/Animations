package ru.otus.animations

import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.core.animation.addListener
import kotlin.math.absoluteValue
import kotlin.math.pow

class LoadingAnimationView(context: Context, attrs: AttributeSet) : AnimatedView(context, attrs) {

    override val duration = 1000L

    private val pinkBackwardsAnimation = ValueAnimator.ofFloat(35F, -35F).apply {
        duration = this@LoadingAnimationView.duration
        interpolator = AccelerateDecelerateInterpolator()
        addUpdateListener {
            val value = it.animatedValue as Float
            circles[1].x = width / 2 + toPixels(value)
            circles[1].radius = toPixels(15 + value.absoluteValue * 15 / 35)
            circles[1].paint.alpha = (value.absoluteValue.pow(2) * 255 / 1225).toInt()
        }
    }

    private val purpleBackwardsAnimation = ValueAnimator.ofFloat(35F, -35F).apply {
        duration = this@LoadingAnimationView.duration
        interpolator = AccelerateDecelerateInterpolator()
        addUpdateListener {
            val value = it.animatedValue as Float
            circles[0].x = width / 2 + toPixels(value)
            circles[0].radius = toPixels(30F)
            circles[0].paint.alpha = 255
        }
    }

    private val pinkAnimation = ValueAnimator.ofFloat(-35F, 35F).apply {
        duration = this@LoadingAnimationView.duration
        interpolator = AccelerateDecelerateInterpolator()
        addUpdateListener {
            val value = it.animatedValue as Float
            circles[1].x = width / 2 + toPixels(value)
            circles[1].radius = toPixels(35 - value.pow(4) / 300_125)
            circles[1].paint.alpha = 255
        }
    }

    private val purpleAnimation = ValueAnimator.ofFloat(-35F, 35F).apply {
        duration = this@LoadingAnimationView.duration
        interpolator = AccelerateDecelerateInterpolator()
        addUpdateListener {
            val value = it.animatedValue as Float
            circles[0].x = width / 2 + toPixels(value)
            circles[0].radius = toPixels(35 - value.pow(4) / 300_125)
            circles[0].paint.alpha = 255
        }
    }

    override fun startAnimation() {
        circles = listOf(
            Circle(
                x = width / 2 - toPixels(35F),
                y = height / 2F,
                radius= toPixels(30F),
                paint = Paint().apply {
                    style = Paint.Style.FILL
                    color = Color.rgb(124, 79, 255)
                    alpha = 255
                }
            ),
            Circle(
                x = width / 2 + toPixels(35F),
                y = height / 2F,
                radius = toPixels(30F),
                paint = Paint().apply {
                    style = Paint.Style.FILL
                    color = Color.rgb(255, 57, 212)
                    alpha = 255
                }
            )
        )
        AnimatorSet().apply {
            playSequentially(
                AnimatorSet().apply {
                    playTogether(purpleAnimation, pinkBackwardsAnimation)
                },
                AnimatorSet().apply {
                    playTogether(pinkAnimation, purpleBackwardsAnimation)
                }
            )
            addListener(
                onEnd = { it.start() }
            )
            start()
        }
    }

}