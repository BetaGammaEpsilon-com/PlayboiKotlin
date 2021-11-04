package jayp.bge.playboikotlin.zeb.fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import jayp.bge.playboikotlin.R
import jayp.bge.playboikotlin.databinding.ActivityZebFragmentBinding

class ZebFragmentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityZebFragmentBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityZebFragmentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(
            R.id.zeb_nav_container
        ) as NavHostFragment
        navController = navHostFragment.navController

        // Setup the bottom navigation view with navController
        binding.zebNav.setupWithNavController(navController)
    }
}