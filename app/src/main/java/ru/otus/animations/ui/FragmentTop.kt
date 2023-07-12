package ru.otus.animations.ui

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.CycleInterpolator
import androidx.fragment.app.Fragment
import ru.otus.animations.R

class FragmentTop: Fragment(R.layout.fragment_top) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val animatorSet = AnimatorSet()
        var letsStart = true

        Log.d("app", "fragment_top on view")

        val figureLeft = view.findViewById<ru.otus.animations.figure.TiktokLoadingProgressBar>(R.id.figure_left)
        figureLeft.setPaint(R.color.tiktok_loading_progressbar_first_figure)

        val animatorLeft = ObjectAnimator.ofFloat(figureLeft,View.TRANSLATION_X, figureLeft.width / 2F, figureLeft.width / 2F + 340).apply {
            interpolator = CycleInterpolator(0.5F)
            repeatCount = ObjectAnimator.INFINITE
            duration = 3000
        }

        val figureRight = view.findViewById<ru.otus.animations.figure.TiktokLoadingProgressBar>(R.id.figure_right)
        figureLeft.setPaint(R.color.tiktok_loading_progressbar_second_figure)

        val animatorRight = ObjectAnimator.ofFloat(figureRight,View.TRANSLATION_X, figureRight.width / 2F, figureRight.width / 2F - 340).apply {
            interpolator = CycleInterpolator(0.5F)
            repeatCount = ObjectAnimator.INFINITE
            duration = 3000
        }
        val animatorRightAlpha = ObjectAnimator.ofFloat(figureRight, View.ALPHA, 1F, 0F, 0F, 1F).apply {
            interpolator = CycleInterpolator(0.6F)
            repeatCount = ObjectAnimator.INFINITE
            duration = 6000
        }

        val scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, 0.9F, 0.8F)
        val scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 0.9F, 0.8F)
        val animatorRightScale = ObjectAnimator.ofPropertyValuesHolder(figureRight, scaleX, scaleY).apply {
            interpolator = CycleInterpolator(1F)
            repeatCount = ObjectAnimator.INFINITE
            duration = 3000
        }

        animatorSet.playTogether(animatorRight, animatorRightAlpha, animatorRightScale)

        figureLeft.setOnClickListener() {
            if (letsStart) {
                animatorLeft.start()
                animatorSet.start()
                letsStart = false
            }

        }
    }
}