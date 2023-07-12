package ru.otus.animations

import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.LinearInterpolator
import android.view.animation.PathInterpolator

class MainActivity : AppCompatActivity() {
    private lateinit var  magentaCircle : CircleView
    private lateinit var  blueCircle : CircleView
    private lateinit var  rippleCircle : List<CircleView>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        blueCircle = findViewById(R.id.blue)
        blueCircle.setColor(Color.BLUE)
        blueCircle.setOnClickListener { ticTocAnimateCircle() }

        magentaCircle = findViewById(R.id.magenta)
        magentaCircle.setColor(Color.MAGENTA)
        magentaCircle.setOnClickListener { ticTocAnimateCircle() }

        rippleCircle = arrayListOf(findViewById(R.id.bottomCircle1),findViewById(R.id.bottomCircle2),findViewById(R.id.bottomCircle3),findViewById(R.id.bottomCircle4))
        rippleCircle.forEach{ circle-> circle.setColor(Color.MAGENTA) }
        rippleCircle.last().setOnClickListener{ rippleAnimateCircle() }
    }

    private fun ticTocAnimateCircle() {
        val blueXHolder = PropertyValuesHolder.ofFloat("blueX",blueCircle.x, magentaCircle.x, blueCircle.x)
        val blueRadiusHolder = PropertyValuesHolder.ofFloat("blueRadius",120F, 130F, 120F, 90F, 120F)
        val magentaXHolder = PropertyValuesHolder.ofFloat("magentaX",magentaCircle.x, blueCircle.x, magentaCircle.x)
        val magentaRadiusHolder = PropertyValuesHolder.ofFloat("magentaRadius",120F, 90F, 120F, 130F, 120F)
        val magentaAlphaHolder = PropertyValuesHolder.ofFloat("blueAlpha",1F, 0F, 0.75F, 1F, 1F)
        ValueAnimator.ofPropertyValuesHolder(blueXHolder, blueRadiusHolder, magentaXHolder, magentaAlphaHolder, magentaRadiusHolder).apply {
            interpolator = PathInterpolator(0.42F,0F,0.58F,1F)
            duration = 2000
            addUpdateListener {
                val blueAlphaAnimateValue = it.getAnimatedValue("blueAlpha") as Float
                val blueXAnimateValue = it.getAnimatedValue("blueX") as Float
                val magentaXAnimateValue = it.getAnimatedValue("magentaX") as Float
                val blueRadiusAnimateValue = it.getAnimatedValue("blueRadius") as Float
                val magentaRadiusAnimateValue = it.getAnimatedValue("magentaRadius") as Float

                blueCircle.x = blueXAnimateValue
                blueCircle.setRadius(blueRadiusAnimateValue)

                magentaCircle.x = magentaXAnimateValue
                magentaCircle.setRadius(magentaRadiusAnimateValue)
                magentaCircle.alpha = blueAlphaAnimateValue

                blueCircle.invalidate()
                magentaCircle.invalidate()
            }
            repeatCount = 500
            start()
        }
    }

    private fun rippleAnimateCircle() {
        val firstHolder = PropertyValuesHolder.ofFloat("radius1",0F, 90F)
        val secondHolder = PropertyValuesHolder.ofFloat("radius2",90F, 180F)
        val thirdHolder = PropertyValuesHolder.ofFloat("radius3",180F, 270F)
        val fourthHolder = PropertyValuesHolder.ofFloat("radius4",270F, 360F)

        val alphaFirstHolder = PropertyValuesHolder.ofFloat("alpha1",1F, 0.75F)
        val alphaSecondHolder = PropertyValuesHolder.ofFloat("alpha2",0.75F, 0.5F)
        val alphaThirdHolder = PropertyValuesHolder.ofFloat("alpha3",0.5F, 0.25F)
        val alphaFourthHolder = PropertyValuesHolder.ofFloat("alpha4",0.25F, 0F)
        ValueAnimator.ofPropertyValuesHolder(firstHolder, secondHolder, thirdHolder, fourthHolder, alphaFirstHolder, alphaSecondHolder, alphaThirdHolder, alphaFourthHolder).apply {
            interpolator = LinearInterpolator()
            duration = 1000
            addUpdateListener {
                val firstRadiusAnimateValue = it.getAnimatedValue("radius1") as Float
                val secondRadiusAnimateValue = it.getAnimatedValue("radius2") as Float
                val thirdRadiusAnimateValue = it.getAnimatedValue("radius3") as Float
                val fourthRadiusAnimateValue = it.getAnimatedValue("radius4") as Float

                val firstAlphaAnimateValue = it.getAnimatedValue("alpha1") as Float
                val secondAlphaAnimateValue = it.getAnimatedValue("alpha2") as Float
                val thirdAlphaAnimateValue = it.getAnimatedValue("alpha3") as Float
                val fourthAlphaAnimateValue = it.getAnimatedValue("alpha4") as Float

                rippleCircle[0].setRadius(firstRadiusAnimateValue)
                rippleCircle[0].alpha = firstAlphaAnimateValue

                rippleCircle[1].setRadius(secondRadiusAnimateValue)
                rippleCircle[1].alpha = secondAlphaAnimateValue

                rippleCircle[2].setRadius(thirdRadiusAnimateValue)
                rippleCircle[2].alpha = thirdAlphaAnimateValue

                rippleCircle[3].setRadius(fourthRadiusAnimateValue)
                rippleCircle[3].alpha = fourthAlphaAnimateValue

                rippleCircle.forEach{ circle -> circle.invalidate() }
            }
            repeatCount = 500
            start()
        }
    }
}