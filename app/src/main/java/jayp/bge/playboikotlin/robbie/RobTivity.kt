package jayp.bge.playboikotlin.robbie

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import jayp.bge.playboikotlin.databinding.ActivityRobTivityBinding
import jayp.bge.playboikotlin.robbie.fragments.RobbieFragmentsActivity

class RobTivity : AppCompatActivity() {

    private val viewModel: RobbieViewModel by viewModels()
    private lateinit var binding: ActivityRobTivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRobTivityBinding.inflate(layoutInflater) // View Bindings!
        setContentView(binding.root)

        // call my button press
        binding.newRobbieButton.setOnClickListener {
            viewModel.onButtonClick()
        }

        binding.robbieFragmentButton.setOnClickListener {
            val fragmentIntent = Intent(this, RobbieFragmentsActivity::class.java)
            startActivity(fragmentIntent)
        }

        // on long press remove a button press
        binding.newRobbieButton.setOnLongClickListener {
            viewModel.onLongButtonClick()
            true // something like the length of the button press idk
        }

        // grab live data from the view model and post to view
        viewModel.robbieViewStateLiveData.observe(this) {
            binding.newRobbieButton.text = "clicked ${it.clicks} times"
            it.robbieString?.let {
                binding.textView2.text = it
            } ?: run {
                binding.textView2.text = "robbie text set to null!"
            }

            if (it.loading) {
                binding.loader.isVisible = true
                binding.loader.animate()
            } else {
                binding.loader.isVisible = false
            }
        }


    }
}