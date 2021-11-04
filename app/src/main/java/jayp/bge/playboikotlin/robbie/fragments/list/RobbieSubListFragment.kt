package jayp.bge.playboikotlin.robbie.fragments.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import jayp.bge.playboikotlin.databinding.FragmentRobbieSubListBinding

class RobbieSubListFragment : Fragment() {
    private lateinit var binding: FragmentRobbieSubListBinding
    private val args: RobbieSubListFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRobbieSubListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.robbieSubListFragmentText.text = args.inputText
    }
}