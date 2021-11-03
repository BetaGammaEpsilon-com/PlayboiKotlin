package jayp.bge.playboikotlin.dan.fragments.webloader

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import jayp.bge.playboikotlin.databinding.FragmentWebLoaderSubBinding

class WebLoaderSubFragment : Fragment() {

    private lateinit var binding: FragmentWebLoaderSubBinding
    private  val args: WebLoaderSubFragmentArgs by navArgs()

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
        binding.subTopText.text = args.topText
        binding.subBottomText.text = args.bottomText
    }
}
