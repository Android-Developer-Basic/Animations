package ru.otus.animations

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.otus.animations.ui.FragmentBottom
import ru.otus.animations.ui.FragmentTop

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.top_fragment_frame, FragmentTop())
                .replace(R.id.bottom_fragment_frame, FragmentBottom())
                .commit()
        }
    }
}