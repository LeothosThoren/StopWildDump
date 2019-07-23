package fr.leothosthoren.stopwilddump.ui.common

import android.os.Bundle
import android.util.Log
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

        setupAppBar()
        setUpBottomNavMenu()
        Log.d("DEBUG", "${viewModel.wildDumpData.value?.informations}")
    }

    private fun setUpBottomNavMenu() {
        nav_view.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { nav, destination, _ ->
            when (destination.id) {
                R.id.destination_map -> nav_view.visibility = View.VISIBLE
                R.id.destination_list -> nav_view.visibility = View.VISIBLE
                else -> nav_view.visibility = View.GONE
            }
        }
    }

    private fun setupAppBar() {
        toolbar.setupWithNavController(navController, appBarConfiguration)
    }
}
