package jayp.bge.playboikotlin.dan.fragments.webloader

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import jayp.bge.playboikotlin.databinding.FragmentWebLoaderTopBinding


class WebLoaderTopFragment : Fragment() {

    private lateinit var binding: FragmentWebLoaderTopBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWebLoaderTopBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.webLoaderButton.setOnClickListener {
            findNavController()
                .navigate(WebLoaderTopFragmentDirections
                    .actionFragmentWebLoaderTopToFragmentWebLoaderSub(
                        binding.topTextArgumentEdittext.text.toString(),
                        binding.bottomTextArgumentEdittext.text.toString()
                    )
                )
        }

    }
}
