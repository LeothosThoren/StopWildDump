package fr.leothosthoren.stopwilddump.ui.infos

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import fr.leothosthoren.stopwilddump.R
import kotlinx.android.synthetic.main.activity_info.*

class InfoActivity : AppCompatActivity() {

    private val navController by lazy {
        Navigation.findNavController(this, R.id.nav_host_info)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)

        val appBarConfiguration = AppBarConfiguration(navController.graph)
        toolbarInfo.setupWithNavController(navController, appBarConfiguration)
        homeNavView.setupWithNavController(navController)
    }
}
