package ru.otus.animations
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.otus.animations.databinding.FragmentLayoutBottomBinding
class Fragment2:Fragment() {
    private lateinit var binding: FragmentLayoutBottomBinding
    private var isPlayAnimation = false
    private  var animationView:AnimationView? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLayoutBottomBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        animationView = AnimationView(binding.canvasLayout.context)
        binding.canvasLayout.addView(animationView)


        binding.button.setOnClickListener {
            if(!isPlayAnimation){
                isPlayAnimation = true
                binding.button.setImageResource(R.drawable.baseline_pause_24)
                animationView?.playAnimation(isPlayAnimation)

            }
            else{
                isPlayAnimation = false
                binding.button.setImageResource(R.drawable.baseline_play_arrow_24)
                animationView?.playAnimation(isPlayAnimation)

            }
        }

    }

}
