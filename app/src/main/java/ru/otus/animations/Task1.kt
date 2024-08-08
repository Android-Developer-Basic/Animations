package ru.otus.animations

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import androidx.fragment.app.Fragment
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.core.animation.doOnEnd

class Task1: Fragment(R.layout.task1) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

      val circle1 = view.findViewById<CircleDraw>(R.id.circle).apply {
          setColor(Color.parseColor("#5533FF"))
      }

      val animator1 = ObjectAnimator.ofFloat(circle1,View.X,0F, 350F)

      val animator11 = ObjectAnimator.ofFloat(circle1,View.X, 350F, 0F)

      val circle2 = view.findViewById<CircleDraw>(R.id.circle2).apply {
          setColor(Color.parseColor("#FF66CC"))
      }

      val animator2 = ObjectAnimator.ofFloat(circle2,View.X,  350F, 0F)

      val animator22 = ObjectAnimator.ofFloat(circle2,View.X,0F, 350F)

      val animatorAlpha = ObjectAnimator.ofFloat(circle2, View.ALPHA, 1F, 0.5F, 0F, 0.5F, 1F)

      val animator2ScaleX = ObjectAnimator.ofFloat(circle2,View.SCALE_X, 1F,0.5F, 0F, 0.5F, 1F)

      val animator2ScaleY = ObjectAnimator.ofFloat(circle2,View.SCALE_Y, 1F,0.5F, 0F, 0.5F, 1F)

      val listAnimators = listOf(
          animator1,
          animator11,
          animator2,
          animator22,
          animatorAlpha,
          animator2ScaleX,
          animator2ScaleY
      )

      listAnimators.forEach{
          it.apply {
              duration = 1000L
              interpolator = AccelerateDecelerateInterpolator()
          }
      }

      val setAnimator = AnimatorSet().apply {
          val first = AnimatorSet().apply {
              playTogether(
                  animator1,
                  animator2,
                  animatorAlpha,
                  animator2ScaleX,
                  animator2ScaleY
              )
          }
          val second = AnimatorSet().apply {
              playTogether(animator11, animator22)
          }
          playSequentially(first,second)
          doOnEnd { start() }
      }

      view.setOnClickListener{
          setAnimator.start()
      }
  }
}