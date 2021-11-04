package jayp.bge.playboikotlin.robbie.fragments

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import jayp.bge.playboikotlin.R
import jayp.bge.playboikotlin.databinding.ActivityRobbieFragmentsBinding

class RobbieFragmentsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRobbieFragmentsBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRobbieFragmentsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(
            R.id.robbie_nav_host_container
        ) as NavHostFragment
        navController = navHostFragment.navController

        // Setup the bottom navigation view with navController
        binding.robbieBottomNav.setupWithNavController(navController)

//        setupActionBarWithNavController(navController, appBarConfiguration)

    }
}