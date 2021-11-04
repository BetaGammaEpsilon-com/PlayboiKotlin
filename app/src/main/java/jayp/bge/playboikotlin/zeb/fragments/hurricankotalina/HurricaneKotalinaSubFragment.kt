package jayp.bge.playboikotlin.zeb.fragments.hurricankotalina

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import jayp.bge.playboikotlin.databinding.FragmentHurricaneKotalinaSubBinding

class HurricaneKotalinaSubFragment: Fragment() {

    private lateinit var binding: FragmentHurricaneKotalinaSubBinding
    private val args: HurricaneKotalinaSubFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHurricaneKotalinaSubBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.kotalinaText.text = args.kotalinaText
    }
}