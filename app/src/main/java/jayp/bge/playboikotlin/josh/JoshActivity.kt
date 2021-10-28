package jayp.bge.playboikotlin.josh

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import androidx.core.view.isVisible
import jayp.bge.playboikotlin.R
import jayp.bge.playboikotlin.databinding.ActivityJoshBinding

class JoshActivity : AppCompatActivity() {

    private val viewModel: JoshViewModel by viewModels()

    private lateinit var binding: ActivityJoshBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJoshBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.joshButton.setOnClickListener {
            viewModel.onJButtonPressed()

        }

        binding.newButton.setOnClickListener {
            viewModel.onNButtonPressed()
        }
        viewModel.joshLiveData.observe(this) {
            binding.textView.text =
                "I have clicked " + it.clicks + " time and the current name is " + it.name
            if (it.isLoading) {
                binding.progRockBar.isVisible = true
                binding.progRockBar.animate()
            } else {
                binding.progRockBar.isVisible = false
            }
        }
    }
}