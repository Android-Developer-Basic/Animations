package ru.otus.animations

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.otus.animations.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private lateinit var fragment1:Fragment1
    private lateinit var fragment2: Fragment2


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if(savedInstanceState == null){
            fragment1 = Fragment1()
            fragment2 = Fragment2()

            supportFragmentManager
                .beginTransaction()
                .addToBackStack(null)
                .replace(binding.container1.id, fragment1, "f1")
                .commit()

            supportFragmentManager
                .beginTransaction()
                .addToBackStack(null)
                .replace(binding.container2.id, fragment2, "f2")
                .commit()
        }
        else {
            fragment1 = supportFragmentManager.findFragmentByTag("f1") as Fragment1
            fragment2 = supportFragmentManager.findFragmentByTag("f2") as Fragment2
        }
    }
}