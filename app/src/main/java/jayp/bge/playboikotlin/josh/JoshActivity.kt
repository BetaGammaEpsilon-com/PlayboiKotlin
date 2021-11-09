package jayp.bge.playboikotlin.josh

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import jayp.bge.playboikotlin.databinding.ActivityJoshBinding
import jayp.bge.playboikotlin.josh.fragments.JoshFragmentsActivity

class JoshActivity : AppCompatActivity() {

    private val viewModel: JoshViewModel by viewModels()

    private lateinit var binding: ActivityJoshBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJoshBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setOnClickListeners()
        viewModel.joshLiveData.observe(this) {
            observeViewState(it)
        }
    }

    private fun observeViewState(viewState: JoshViewState) {
        binding.textView.text =
            "I have clicked " + viewState.clicks + " time and the current name is " + viewState.name
        if (viewState.isLoading) {
            binding.progRockBar.isVisible = true
            binding.progRockBar.animate()
        } else {
            binding.progRockBar.isVisible = false
        }
    }


    private fun setOnClickListeners() {

        with(binding) {
            joshButton.setOnClickListener {
                viewModel.onJButtonPressed()
            }
            newButton.setOnClickListener {
                viewModel.onNButtonPressed()
            }
            goToFragmentsButton.setOnClickListener {
                val intent = Intent(this@JoshActivity, JoshFragmentsActivity::class.java)
                startActivity(intent)
            }
        }
    }
}