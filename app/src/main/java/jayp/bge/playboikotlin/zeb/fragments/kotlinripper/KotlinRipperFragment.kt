package jayp.bge.playboikotlin.zeb.fragments.kotlinripper

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import jayp.bge.playboikotlin.databinding.FragmentKotlinRipperBinding

class KotlinRipperFragment : Fragment() {

    private lateinit var binding: FragmentKotlinRipperBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentKotlinRipperBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}