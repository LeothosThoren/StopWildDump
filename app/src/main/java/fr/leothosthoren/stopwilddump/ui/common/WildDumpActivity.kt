package fr.leothosthoren.stopwilddump.ui.common

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import fr.leothosthoren.stopwilddump.R
import kotlinx.android.synthetic.main.activity_wild_dump.*

class WildDumpActivity : AppCompatActivity() {

    private val navController by lazy {
        Navigation.findNavController(this, R.id.nav_host_fragment)
    }
    private val viewModel by lazy {
        ViewModelProviders.of(this).get(CommonViewModel::class.java)
    }
    private val appBarConfiguration by lazy {
        AppBarConfiguration(navController.graph)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wild_dump)

        setupToolbar()
        setUpBottomNavMenu()
        Log.d("DEBUG", "${viewModel.wildDumpData.value?.informations}")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    private fun setupToolbar() {
        toolbar.setupWithNavController(navController, appBarConfiguration)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.homeFragment)
                toolbar.visibility = View.GONE
            else if (destination.id == R.id.destination_map)
                toolbar.visibility = View.VISIBLE

            //toolbar.menu.findItem(R.id.destination_list).isVisible = destination.id == R.id.destination_list

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
