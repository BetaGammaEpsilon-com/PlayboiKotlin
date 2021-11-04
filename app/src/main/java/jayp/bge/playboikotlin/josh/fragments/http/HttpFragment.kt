package jayp.bge.playboikotlin.josh.fragments.http

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import jayp.bge.playboikotlin.databinding.FragmentHttpBinding


class HttpFragment : Fragment() {

    private lateinit var binding: FragmentHttpBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHttpBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.joshButton.setOnClickListener {
            findNavController()
                .navigate(
                    HttpFragmentDirections
                        .actionFragmentHttpToFragmentHttpSub(
                            binding.editText.text.toString()
                        )
                )
        }
    }

}