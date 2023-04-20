package ru.otus.animations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.otus.animations.databinding.FragmentLayoutBinding

class Fragment1:Fragment() {
    private var isPlayAnimation = false
    private lateinit var binding: FragmentLayoutBinding

    private var circle1X = 0f
    private var circle2X = 0f

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLayoutBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val screenPixelDensity = resources.displayMetrics.density
        binding.circle1.apply {
            x-=(75*screenPixelDensity)
            circle1X = x

        }
        binding.circle2.apply {
            x+=(75*screenPixelDensity)
            circle2X = x

        }


        binding.button.setOnClickListener {
            if(!isPlayAnimation){
                isPlayAnimation = true
                binding.button.setImageResource(R.drawable.baseline_pause_24)

            }
            else{
                isPlayAnimation = false
                binding.button.setImageResource(R.drawable.baseline_play_arrow_24)

            }
        }

    }
}