package jayp.bge.playboikotlin.dan

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import jayp.bge.playboikotlin.databinding.ActivityDanBinding

class DanActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDanBinding
    private val viewModel: DanMainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDanBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setOnClickListeners()
        viewModel.viewStateLiveData.observe(this) {
            observeViewState(it)
        }
    }

    private fun observeViewState(viewState: DanMainViewState) {
        binding.danTextview.text = "Press count: ${viewState.buttonCount}"
    }

    private fun setOnClickListeners() {
        with(binding) {
            incrementButton.setOnClickListener {
                viewModel.onButtonPressed()
            }
            incrementButton.setOnLongClickListener {
                viewModel.onButtonHeld()
                true
            }
        }
    }
}