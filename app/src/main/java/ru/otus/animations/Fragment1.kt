package ru.otus.animations

import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import android.widget.FrameLayout
import androidx.core.animation.doOnEnd
import androidx.core.animation.doOnPause
import androidx.fragment.app.Fragment
import ru.otus.animations.databinding.FragmentLayoutBinding

class Fragment1:Fragment() {
    private var isPlayAnimation = false
    private lateinit var binding: FragmentLayoutBinding
    private val positionAnimator = ValueAnimator()
    private var circle1X = 0f
    private var circle2X = 0f
    private var currentPosCircle1X = 0f
    private var currentPosCircle2X = 0f


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLayoutBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val screenPixelDensity = resources.displayMetrics.density
        binding.circle1.apply {
            x-=(55*screenPixelDensity)
            circle1X = x

        }
        binding.circle2.apply {
            x+=(55*screenPixelDensity)
            circle2X = x

        }
        val animatorHolder1 = PropertyValuesHolder.ofFloat("v1Pos",circle1X, circle2X, circle1X)
        val animatorHolder2 = PropertyValuesHolder.ofFloat("v2Pos",circle2X, circle1X, circle2X)
        val animatorHolder3 = PropertyValuesHolder.ofFloat("v1PivotZ", 1f,-1f)
        val animatorHolder4 = PropertyValuesHolder.ofFloat("v1Opacity", 1f, 1f, 1f, 0f, 1f)
        val animatorHolder5 = PropertyValuesHolder.ofInt("v1Size",100, 100, 100, 50, 100)
        positionAnimator.apply {
            doOnEnd { playAnimation() }
            doOnPause { binding.button.setImageResource(R.drawable.baseline_play_arrow_24) }
            duration = 2000
            interpolator = LinearInterpolator()
            setValues(animatorHolder1, animatorHolder2, animatorHolder3, animatorHolder4, animatorHolder5)
        }
        positionAnimator.addUpdateListener {
            currentPosCircle1X = it.getAnimatedValue("v1Pos") as Float + (binding.circleContainer.width/2 + circle1X)
            currentPosCircle2X = it.getAnimatedValue("v2Pos") as Float

            binding.circle1.apply {
                val v1Width = ((it.getAnimatedValue("v1Size") as Int)*screenPixelDensity).toInt()
                layoutParams = FrameLayout.LayoutParams(v1Width, v1Width)
                translationX = currentPosCircle1X
                z = it.getAnimatedValue("v1PivotZ") as Float
                alpha = it.getAnimatedValue("v1Opacity") as Float
                invalidate()
            }
            binding.circle2.apply {
                translationX = currentPosCircle2X
                invalidate()
            }
        }
        binding.button.setOnClickListener {
            if(!isPlayAnimation){
                isPlayAnimation = true
                binding.button.setImageResource(R.drawable.baseline_pause_24)
                playAnimation()
            }
            else{
                isPlayAnimation = false
                binding.button.setImageResource(R.drawable.baseline_play_arrow_24)
                playAnimation()
            }
        }
    }
    private fun playAnimation(){

        if(isPlayAnimation){
            if(positionAnimator.isPaused) positionAnimator.resume()
            else positionAnimator.start()
        }

        else positionAnimator.pause()
    }
}