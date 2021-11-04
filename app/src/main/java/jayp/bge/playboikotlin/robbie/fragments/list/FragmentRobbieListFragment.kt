package jayp.bge.playboikotlin.robbie.fragments.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import jayp.bge.playboikotlin.databinding.FragmentRobbieListBinding

class FragmentRobbieListFragment : Fragment() {

    private lateinit var binding: FragmentRobbieListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRobbieListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.robbieListButton.setOnClickListener {
            findNavController()
                .navigate(
                    FragmentRobbieListFragmentDirections
                        .actionFragmentRobbieListToFragmentRobbieSubList(
                            binding.robbieList.text.toString()
                        )
                )
        }
    }

}