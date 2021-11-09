package jayp.bge.playboikotlin.robbie.fragments.webloader

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import jayp.bge.playboikotlin.databinding.FragmentRobbieWebLoaderBinding

class RobbieWebLoaderFragment : Fragment() {

    private lateinit var binding: FragmentRobbieWebLoaderBinding
    private val viewModel: RobbieWebLoaderViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentRobbieWebLoaderBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setClickListeners()
        viewModel.viewStateLiveData.observe(viewLifecycleOwner) {
            observeViewState(it)
        }
    }

    private fun observeViewState(viewState: RobbieWebLoaderViewState) {
        when (viewState.webResponse) {
            is Failure -> onWebFailure(viewState.webResponse.errorMessage)
            Loading -> showLoading(true)
            is Success -> onWebSuccess(viewState.webResponse.data)
        }
    }

    private fun onWebFailure(errorMessage: String) {
        with(binding) {
            showLoading(false)
            robbieWlLayout.visibility = GONE
            robbieWlBottomText.text = errorMessage
        }
    }

    private fun onWebSuccess(data: List<EightBall>?) {
        with(binding) {
            data?.let { nonNullData ->
                robbieWlBottomText.text =
                    nonNullData.map { it.toString() }.reduce { acc, s -> acc + "\n" + s + "\n" }
            } ?: kotlin.run {
                robbieWlBottomText.text = "rob response was null"
            }
            showLoading(false)
            robbieWlLayout.visibility = VISIBLE
        }
    }

    private fun showLoading(shouldShow: Boolean) {
        with(binding.robbieWlProgressBar) {
            if (shouldShow) {
                visibility = VISIBLE
                animate()
            } else {
                visibility = GONE
                clearAnimation()
            }
        }
    }

    private fun setClickListeners() {
        binding.webLoaderButton.setOnClickListener {
            viewModel.sendWebRequest(1)
        }
    }
}