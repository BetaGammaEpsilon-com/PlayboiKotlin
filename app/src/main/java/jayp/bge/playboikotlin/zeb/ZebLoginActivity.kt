package jayp.bge.playboikotlin.zeb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import jayp.bge.playboikotlin.databinding.ActivityZebLoginBinding
import jayp.bge.playboikotlin.zeb.fragments.ZebFragmentActivity

class ZebLoginActivity : AppCompatActivity() {

    private val viewModel: ZebViewModel by viewModels()
    private lateinit var binding: ActivityZebLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityZebLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.zebViewStateLiveData.observe(this) {

            if(it.validLogin) {

                val intent = Intent(this@ZebLoginActivity, ZebFragmentActivity::class.java)
                startActivity(intent)
            }
        }

        binding.loginButton.setOnClickListener {

            viewModel.onLoginButtonPress(binding.usernameField.text.toString(), binding.passwordField.text.toString())
        }
    }
}