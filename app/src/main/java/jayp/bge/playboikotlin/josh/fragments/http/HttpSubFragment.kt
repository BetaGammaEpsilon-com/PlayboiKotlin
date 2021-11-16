package jayp.bge.playboikotlin.josh.fragments.http

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import jayp.bge.playboikotlin.databinding.FragmentHttpSubBinding

class HttpSubFragment : Fragment() {

    private lateinit var binding: FragmentHttpSubBinding
    private val args: HttpSubFragmentArgs by navArgs()
    private val viewModel: JoshWebRequestViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHttpSubBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            textView.text = args.text
        }
        setClickListeners()
        viewModel.viewStateliveData.observe(viewLifecycleOwner) { observeViewState(it) }
    }

    private fun observeViewState(viewState: JoshWebRequestViewState) {
        when (viewState.webResponse) {
            is JoshFailure -> onWebFailure(viewState.webResponse.errorMessage)
            Loading -> showLoading(true)
            is JoshSuccess -> onWebSuccess(viewState.webResponse.listData)
        }
    }

    private fun onWebFailure(errorMessage: String) {
        with(binding) {
            showLoading(false)
            scrollView.visibility = GONE
            textView.text = errorMessage
        }
    }

    private fun onWebSuccess(data: List<AteBall>?) {
        with(binding) {
            data?.let { nonNullData ->
                requestResponseTextview.text = nonNullData.toString()
            } ?: kotlin.run {
                requestResponseTextview.text = "null web response"
            }
            showLoading(false)
            scrollView.visibility = VISIBLE
        }
    }

    private fun showLoading(shouldShow: Boolean) {
        with(binding.progressBar) {
            if (shouldShow) {
                visibility = VISIBLE
                animate()
            } else {
                visibility = GONE
            }
        }
    }

    private fun setClickListeners() {
        with(binding) {
            webButton.setOnClickListener {
                viewModel.sendShakeRequest()
            }
            addToAteBallButton.setOnClickListener {
                viewModel.sendAddRequest(
                    demandView.text.toString(),
                    idView.text.toString().toInt(),
                    messageView.text.toString(),
                    youWillLiveToView.text.toString().toInt()
                )
            }
        }
    }


}