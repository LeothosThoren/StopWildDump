package fr.leothosthoren.stopwilddump.ui.infos

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import fr.leothosthoren.stopwilddump.R
import fr.leothosthoren.stopwilddump.ui.common.CommonViewModel
import kotlinx.android.synthetic.main.activity_info.*

class InfoActivity : AppCompatActivity() {


    private val navController by lazy {
        Navigation.findNavController(this, R.id.infoHostFragment)
    }
    private val viewModel by lazy {
        ViewModelProviders.of(this).get(CommonViewModel::class.java)
    }
    private val appBarConfiguration by lazy {
        AppBarConfiguration(setOf(R.id.infoHostFragment))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)
        setUpBottomNavMenu()
        Log.d("DEBUG", "${viewModel.wildDumpData.value?.informations}")
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun setUpBottomNavMenu() {
        navInfoView.setupWithNavController(navController)
        navInfoView.setOnNavigationItemReselectedListener { }
    }
}
