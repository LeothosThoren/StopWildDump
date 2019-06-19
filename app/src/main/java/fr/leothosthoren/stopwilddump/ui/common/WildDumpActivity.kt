package fr.leothosthoren.stopwilddump.ui.common

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import fr.leothosthoren.stopwilddump.R
import kotlinx.android.synthetic.main.activity_wild_dump.*


class WildDumpActivity : AppCompatActivity() {

    private val navController by lazy {
        Navigation.findNavController(this, R.id.nav_host_fragment)
    }

    private val viewModel by lazy {
        ViewModelProviders.of(this).get(CommonViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wild_dump)
        setSupportActionBar(toolbar)

        setUpBottomNavMenu()
        setupAppBar()
        Log.d("DEBUG", "${viewModel.wildDumpData.value?.informations}")

    }


    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    private fun setUpBottomNavMenu() {
        nav_view.let { NavigationUI.setupWithNavController(it, navController) }
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.homeFragment -> nav_view.visibility = View.GONE
                R.id.detailFragment -> nav_view.visibility = View.GONE
                else -> nav_view.visibility = View.VISIBLE
            }
        }
    }

    private fun setupAppBar() {
        NavigationUI.setupActionBarWithNavController(this, navController)
    }


}
