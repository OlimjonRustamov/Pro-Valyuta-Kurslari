package uz.olimjon_rustamov.provalyutakurslari

import android.content.res.Resources
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.marginTop
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import uz.olimjon_rustamov.provalyutakurslari.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //disable night mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        //*********

        setupNavController()
    }

    private fun setupNavController() {
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        navView.setupWithNavController(navController)
        binding.btmNavView.setupWithNavController(navController)
        binding.toolbarMain.setNavigationOnClickListener {
            if (!binding.drawerLayout.isOpen) {
                binding.drawerLayout.open()
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp()
    }
    fun shareProgram(view: View) {
        //share code here
        Snackbar.make(view,"Share", Snackbar.LENGTH_LONG).show()
    }

    fun aboutProgram(view: View) {
        //share code here
        Snackbar.make(view,"About", Snackbar.LENGTH_LONG).show()
    }
}