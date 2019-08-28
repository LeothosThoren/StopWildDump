package fr.leothosthoren.stopwilddump.ui.common

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import fr.leothosthoren.stopwilddump.R
import kotlinx.android.synthetic.main.activity_wild_dump.*

class WildDumpActivity : AppCompatActivity() {

    private val navController by lazy {
        Navigation.findNavController(this, R.id.homeFragment)
    }
    private val viewModel by lazy {
        ViewModelProviders.of(this).get(CommonViewModel::class.java)
    }
    private val appBarConfiguration by lazy {
        AppBarConfiguration(setOf(R.id.homeFragment))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wild_dump)

        setupToolbar()
        setUpBottomNavMenu()
        Log.d("DEBUG", "${viewModel.wildDumpData.value?.informations}")
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun setUpBottomNavMenu() {
        nav_view.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { nav, destination, _ ->
            when (destination.id) {
                R.id.destination_map -> nav_view.visibility = View.VISIBLE
                R.id.destination_wild_dump_list -> nav_view.visibility = View.VISIBLE
                else -> nav_view.visibility = View.GONE
            }
        }
    }

    private fun setupToolbar() {
        toolbar.inflateMenu(R.menu.toolbar_menu)
        toolbar.setupWithNavController(navController, appBarConfiguration)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            // toolbar.isVisible = destination.id == R.id.homeFragment
            toolbar.menu.findItem(R.id.account).isVisible = destination.id == R.id.homeFragment
        }
        toolbar.setOnMenuItemClickListener { item ->
            Log.d("---------------------", "Toolbar Menu item selected !!")
            return@setOnMenuItemClickListener true
        }
    }
}
