package ru.otus.animations

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val loadingAnimationView = findViewById<AnimatedView>(R.id.loading)
        loadingAnimationView.setOnClickListener {
            loadingAnimationView.startAnimation()
        }
        val rippleAnimationView = findViewById<AnimatedView>(R.id.ripple)
        rippleAnimationView.setOnClickListener {
            rippleAnimationView.startAnimation()
        }
    }
}