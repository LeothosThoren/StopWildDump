package fr.leothosthoren.stopwilddump.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability

const val ERROR_DIALOG_REQUEST = 69 //ASCII 'E'


abstract class BaseFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutId(), container, false)
    }

    abstract fun getLayoutId(): Int


    fun isGooglePlayServiceOk(): Boolean {
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