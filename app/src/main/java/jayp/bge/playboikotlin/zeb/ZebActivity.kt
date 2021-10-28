package jayp.bge.playboikotlin.zeb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import androidx.core.view.isVisible
import jayp.bge.playboikotlin.R
import jayp.bge.playboikotlin.databinding.ActivityZebBinding

class ZebActivity : AppCompatActivity() {

    private val viewModel: ZebViewModel by viewModels()
    private lateinit var binding: ActivityZebBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityZebBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomerButton.setOnClickListener {
            viewModel.onButtonPress()
        }

        viewModel.zebViewStateLiveData.observe(this) {
            binding.bottomText.text = "clicked: ${it.clicks} times!"
        }

        binding.topperButton.setOnClickListener {
            viewModel.onLongButtonPress()
        }

        viewModel.zebViewStateLiveData.observe(this) {
            binding.topText.text = "${it.his}"
            binding.middleText.text = "${it.tester}"
//            it.tester?.let {
//                binding.middleText.text = it
//            }

            if (it.loading) {
                binding.progressThat.isVisible = true
                binding.progressThat.animate()
            } else {
                binding.progressThat.isVisible = false
                binding.progressThat.clearAnimation()
            }
        }
    }
}