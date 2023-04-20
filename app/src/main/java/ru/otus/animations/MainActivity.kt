package ru.otus.animations

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.otus.animations.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if(savedInstanceState == null){
            val fragment1 = Fragment1()
            val fragment2 = Fragment2()

            supportFragmentManager
                .beginTransaction()
                .addToBackStack(null)
                .replace(binding.container1.id, fragment1)
                .commit()

            supportFragmentManager
                .beginTransaction()
                .addToBackStack(null)
                .replace(binding.container2.id, fragment2)
                .commit()
        }
    }
}