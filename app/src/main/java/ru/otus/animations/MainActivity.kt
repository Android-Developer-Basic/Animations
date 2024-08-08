package ru.otus.animations

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val topFragment = Task1()
        val bottomFragment = Task2()
        supportFragmentManager.beginTransaction()
            .replace(R.id.task1, topFragment)
            .replace(R.id.task2, bottomFragment)
            .commit()
    }
}