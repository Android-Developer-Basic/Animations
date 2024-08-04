package ru.otus.animations

import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    private lateinit var circlePink: ImageView
    private lateinit var circleBlue: ImageView

    private lateinit var circleView: CircleView
    private lateinit var circleView2: CircleView
    private lateinit var circleView3: CircleView
    private lateinit var circleView4: CircleView
    private lateinit var circleView5: CircleView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        circlePink = findViewById(R.id.circle_pink)
        circleBlue = findViewById(R.id.circle_blue)
        circleView = findViewById(R.id.circle_view)
        circleView2 = findViewById(R.id.circle_view2)
        circleView3 = findViewById(R.id.circle_view3)
        circleView4 = findViewById(R.id.circle_view4)
        circleView5 = findViewById(R.id.circle_view5)

        circlePink.setOnClickListener(){
                animateCirclePink(circlePink)
                animateCircleBlue(circleBlue)
        }
        circleBlue.setOnClickListener(){
            animateCirclePink(circlePink)
            animateCircleBlue(circleBlue)
        }

        circleView2.startDel = 400
        circleView3.startDel = 800
        circleView4.startDel = 1200
        circleView5.startDel = 1600

        circleView.setOnClickListener() {
                circleView.animateCircle()
                circleView2.animateCircle()
                circleView3.animateCircle()
                circleView4.animateCircle()
                circleView5.animateCircle()
            }
        }
    }

    private fun animateCirclePink(imageView: ImageView){
        val animatorToRightHolder = PropertyValuesHolder.ofFloat("moveToRight", 0f, 181f)
        val animatorScaleFrontHolder = PropertyValuesHolder.ofFloat("scale", 1f, 1.10f, 1f)

        val animatorFirstPart = ValueAnimator.ofPropertyValuesHolder(animatorToRightHolder, animatorScaleFrontHolder).apply {
            duration = 700
            interpolator = AccelerateDecelerateInterpolator()
            addUpdateListener {
                val animated = it.getAnimatedValue("moveToRight") as Float
                imageView.translationX = animated
                val animatedScale = it.getAnimatedValue("scale") as Float
                imageView.scaleX = animatedScale
                imageView.z = animatedScale
                imageView.scaleY = animatedScale
                imageView.scaleType = ImageView.ScaleType.CENTER_CROP
            }
            start()
        }

        val animatorToLeftHolder = PropertyValuesHolder.ofFloat("moveToLeft", 181f, 0f)
        val animatorScaleBackHolder = PropertyValuesHolder.ofFloat("scale", 1f, 0.7f, 1f)
        val animatorAlpha = PropertyValuesHolder.ofFloat("alpha", 1f, 0f, 1f)

        val animatorSecondPart = ValueAnimator.ofPropertyValuesHolder(animatorToLeftHolder, animatorScaleBackHolder, animatorAlpha).apply {
            duration = 800
            interpolator = AccelerateDecelerateInterpolator()
            startDelay = 800
            addUpdateListener {
                val animated = it.getAnimatedValue("moveToLeft") as Float
                imageView.translationX = animated
                val animatedScale = it.getAnimatedValue("scale") as Float
                imageView.scaleX = animatedScale
                imageView.z = animatedScale
                imageView.scaleY = animatedScale
                imageView.scaleType = ImageView.ScaleType.CENTER_CROP
                val animatedAlpha = it.getAnimatedValue("alpha") as Float
                imageView.alpha = animatedAlpha
            }
            start()
        }
    }

    private fun animateCircleBlue(imageView: ImageView){
        val animatorToLeftHolder = PropertyValuesHolder.ofFloat("moveToLeft", 0f, -181f)

        val animatorFirstPart = ValueAnimator.ofPropertyValuesHolder(animatorToLeftHolder).apply {
            duration = 700
            interpolator = AccelerateDecelerateInterpolator()
            addUpdateListener {
                val animated = it.getAnimatedValue("moveToLeft") as Float
                imageView.translationX = animated
            }
            start()
        }

        val animatorToRightHolder = PropertyValuesHolder.ofFloat("moveToRight", -181f, 0f)
        val animatorScaleBackHolder = PropertyValuesHolder.ofFloat("scale", 1f, 1.05f, 1f)

        val animatorSecondPart = ValueAnimator.ofPropertyValuesHolder(animatorToRightHolder, animatorScaleBackHolder).apply {
            duration = 800
            interpolator = AccelerateDecelerateInterpolator()
            startDelay = 800
            addUpdateListener {
                val animated = it.getAnimatedValue("moveToRight") as Float
                imageView.translationX = animated
                val animatedScale = it.getAnimatedValue("scale") as Float
                imageView.scaleX = animatedScale
                imageView.z = animatedScale
                imageView.scaleY = animatedScale
                imageView.scaleType = ImageView.ScaleType.CENTER_CROP
            }
            start()
        }

    }
