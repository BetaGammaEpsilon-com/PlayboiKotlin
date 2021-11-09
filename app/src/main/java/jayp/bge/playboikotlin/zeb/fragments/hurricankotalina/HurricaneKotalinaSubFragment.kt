package jayp.bge.playboikotlin.zeb.fragments.hurricankotalina

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import jayp.bge.playboikotlin.databinding.FragmentHurricaneKotalinaSubBinding

class HurricaneKotalinaSubFragment : Fragment() {

    private val viewModel: ZebWebRequestViewModel by viewModels()
    private lateinit var binding: FragmentHurricaneKotalinaSubBinding
    private val args: HurricaneKotalinaSubFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHurricaneKotalinaSubBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.kotalinaText.text = args.kotalinaText
        setClickListeners()
        viewModel.viewStateLiveData.observe(viewLifecycleOwner) { observeViewState(it) }
    }

    private fun setClickListeners() {
        binding.kotalinaRequest.setOnClickListener {
            viewModel.webRequestAddFortune("yoyoyooy", 852, "she called me a kot", 9)
        }
    }

    private fun observeViewState(viewState: ZebWebRequestViewState) {
        when (viewState.webResponse) {
            is ZebFailure -> onWebFailure(viewState.webResponse.errorMessage)
            ZebLoading -> showLoading(true)
            is ZebSuccess -> onWebSuccess(viewState.webResponse.data)
        }
    }

    private fun onWebSuccess(data: List<EightBallJson>?) {
        with(binding) {
            data?.let { nonNullData ->
                kotalinaResponse.text = data.toString()
            } ?: kotlin.run {
                kotalinaResponse.text = "Oh no! Web response was null :("
            }
            showLoading(false)
            kotalinaScrolling.visibility = View.VISIBLE
            kotalinaText.text = data.toString()
        }
    }

    private fun showLoading(shouldShow: Boolean) {

        with(binding.kotalinaProgress) {
            if (shouldShow) {
                visibility = android.view.View.VISIBLE
                animate()
            } else {
                visibility = android.view.View.GONE
                clearAnimation()
            }
        }
    }

    private fun onWebFailure(errorMessage: String) {

        with(binding) {
            showLoading(false)
            kotalinaScrolling.visibility = View.GONE
            kotalinaText.text = errorMessage
        }
    }
}