package fr.leothosthoren.stopwilddump.ui.home


import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation
import fr.leothosthoren.stopwilddump.R
import fr.leothosthoren.stopwilddump.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment() {

    override fun getLayoutId(): Int = R.layout.fragment_home

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Action
        goToMap.setOnClickListener {
            if (isGooglePlayServiceOk()) {
                val nextAction =
                    HomeFragmentDirections.actionHomeFragmentToDestinationMap()
                Navigation.findNavController(it).navigate(nextAction)
            }
        }

        goToReportWildDump.setOnClickListener {
            //findNavController().navigate(R.id.)
            // TODO : define the next screen to report the wild dump
        }
    }

}
