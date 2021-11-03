package jayp.bge.playboikotlin.dan.fragments.chatty

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import jayp.bge.playboikotlin.databinding.FragmentChattyBinding

class ChattyFragment : Fragment() {

    private lateinit var binding: FragmentChattyBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentChattyBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.newScreenButton.setOnClickListener {
            //TODO: open the new fragment

        }
    }

}
