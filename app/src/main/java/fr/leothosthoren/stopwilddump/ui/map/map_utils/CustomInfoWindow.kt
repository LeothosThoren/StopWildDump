package fr.leothosthoren.stopwilddump.ui.map.map_utils

import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import fr.leothosthoren.stopwilddump.R

data class CustomInfoWindowAdapter(private val inflater: LayoutInflater) : GoogleMap.InfoWindowAdapter {

    override fun getInfoWindow(marker: Marker): View? = null

    override fun getInfoContents(marker: Marker): View? {
        val window: View = inflater.inflate(R.layout.custom_info_window, null)
        window.findViewById<TextView>(R.id.infoWindowTitle).text = marker.title
        window.findViewById<TextView>(R.id.infoWindowSnippet).text = marker.snippet
        return window
    }
}