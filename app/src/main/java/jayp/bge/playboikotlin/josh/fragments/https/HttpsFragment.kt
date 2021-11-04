package jayp.bge.playboikotlin.josh.fragments.https

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import jayp.bge.playboikotlin.databinding.FragmentHttpsBinding

class HttpsFragment : Fragment() {

    private lateinit var binding: FragmentHttpsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHttpsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.joshButton.setOnClickListener {
            //TODO: open the new fragment
        }
    }

}