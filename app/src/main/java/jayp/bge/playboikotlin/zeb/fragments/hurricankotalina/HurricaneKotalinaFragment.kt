package jayp.bge.playboikotlin.zeb.fragments.hurricankotalina

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import jayp.bge.playboikotlin.databinding.FragmentHurricaneKotalinaBinding

class HurricaneKotalinaFragment: Fragment() {

    private lateinit var binding: FragmentHurricaneKotalinaBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHurricaneKotalinaBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.hurricaneButton.setOnClickListener {
            findNavController()
                .navigate(HurricaneKotalinaFragmentDirections
                    .actionFragmentHurricaneKotalinaTopToFragmentHurricaneKotalinaSub(
                        binding.hurricaneText.text.toString()
                    )
                )
        }
    }
}