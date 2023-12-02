package jayp.bge.playboikotlin.robbie.fragments.webloader

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import jayp.bge.playboikotlin.databinding.FragmentRobbieWebLoaderBinding

class RobbieWebLoaderFragment : Fragment() {

    private lateinit var binding: FragmentRobbieWebLoaderBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRobbieWebLoaderBinding.inflate(layoutInflater, container, false)
        // where the fcuck arer my hooks??
        return binding.root
    }

}