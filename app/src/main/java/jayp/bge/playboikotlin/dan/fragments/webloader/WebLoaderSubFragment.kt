package jayp.bge.playboikotlin.dan.fragments.webloader

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import jayp.bge.playboikotlin.databinding.FragmentWebLoaderSubBinding

class WebLoaderSubFragment : Fragment() {

    private lateinit var binding: FragmentWebLoaderSubBinding
    private  val args: WebLoaderSubFragmentArgs by navArgs()
    private val viewModel: DanWebRequestViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWebLoaderSubBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            subTopText.text = args.topText
            subBottomText.text = args.bottomText
        }
        setClickListeners()
        viewModel.viewStateLiveData.observe(viewLifecycleOwner){ observeViewState(it)}
    }

    private fun observeViewState(viewState: DanWebRequestViewState) {
        when(viewState.webResponse){
            is Failure -> onWebFailure(viewState.webResponse.errorMessage)
            Loading -> showLoading(true)
            is Success -> onWebSuccess(viewState.webResponse.data)
        }
    }

    private fun onWebFailure(errorMessage: String) {
        with(binding) {
            showLoading(false)
            webContentLayout.visibility = GONE
            subBottomText.text = errorMessage
        }
    }

    private fun onWebSuccess(data: List<User>?) {
        with(binding){
            data?.let { nonNullData ->
                requestResponseTextview.text =
                    nonNullData.map { it.toString() }.reduce { acc, s -> acc + "\n" + s + "\n" }
            } ?: kotlin.run {
                requestResponseTextview.text = "Oh no! Web response was null :("
            }
            showLoading(false)
            webContentLayout.visibility = VISIBLE
        }
    }

    private fun showLoading(shouldShow: Boolean) {
        with(binding.progressBar){
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
        binding.requestButton.setOnClickListener {
            viewModel.sendWebRequest(3)
        }
    }
}
