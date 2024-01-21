package ru.otus.animations

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val rippleLayout = findViewById<RippleLayout>(R.id.rippleLayout)
        rippleLayout.setOnClickListener {
            rippleLayout.startStopRippleAnimation()
        }

    }
}