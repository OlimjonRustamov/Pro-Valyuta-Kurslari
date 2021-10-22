package uz.olimjon_rustamov.provalyutakurslari

import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.view.View
import android.widget.Toast
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
        setSupportActionBar(binding.toolbarMain)
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
        binding.drawerLayout.closeDrawers()
        val shareIntent = Intent()
        shareIntent.action = Intent.ACTION_SEND
        shareIntent.type = "text/plain"
        shareIntent.putExtra(
            Intent.EXTRA_TEXT,
            "https://github.com/OlimjonRustamov/Pro-Valyuta-Kurslari"
        )
        startActivity(Intent.createChooser(shareIntent, "Share to"))
    }

    fun aboutProgram(view: View) {
        Toast.makeText(this@MainActivity, "Info!", Toast.LENGTH_SHORT).show()
        binding.drawerLayout.closeDrawers()
    }
}