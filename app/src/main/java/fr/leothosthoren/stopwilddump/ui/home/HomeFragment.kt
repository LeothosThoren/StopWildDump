package fr.leothosthoren.stopwilddump.ui.home


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import fr.leothosthoren.stopwilddump.R
import kotlinx.android.synthetic.main.fragment_home.*

const val ERROR_DIALOG_REQUEST = 69 //ASCII 'E'

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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

    private fun isGooglePlayServiceOk(): Boolean {
        val availability = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context)
        if (availability == ConnectionResult.SUCCESS) {
            //We check that the google services is fine and user can make request
            return true
        } else if (GoogleApiAvailability.getInstance().isUserResolvableError(availability)) {
            //We have to handle the error status
            val dialog = GoogleApiAvailability.getInstance().getErrorDialog(
                activity, availability,
                ERROR_DIALOG_REQUEST
            )
            dialog.show()
        }
        return false
    }
}
