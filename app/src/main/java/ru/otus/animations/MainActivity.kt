package ru.otus.animations

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.otus.animations.fragments.CircleWave
import ru.otus.animations.fragments.TwoCircles

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val twoCircles = TwoCircles()
        val circleWave = CircleWave()

        supportFragmentManager.beginTransaction()
            .add(R.id.two_circles, twoCircles)
            .add(R.id.circle_wave, circleWave)
            .commit()

    }
}