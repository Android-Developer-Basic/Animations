package ru.otus.animations.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import ru.otus.animations.R
import ru.otus.animations.elements.Circle

class CircleWave : Fragment(R.layout.circle_wave) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val circle = view.findViewById<Circle>(R.id.circle_wave_elem)

        for (ii in 0..5) {
            circle.circles.add(Circle.CircleParameters())
        }

        circle.setOnClickListener {
            circle.waveAnimation()
        }
    }


}