package fr.leothosthoren.stopwilddump.ui.common

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.onNavDestinationSelected
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.moreIcon -> Toast.makeText(this, "Test icon", Toast.LENGTH_SHORT).show()
        }
        return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun setupToolbar() {
        toolbar.inflateMenu(R.menu.toolbar_menu)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id != R.id.homeFragment) {
                // TODO
            }
        }
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
}
