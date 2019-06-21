package fr.leothosthoren.stopwilddump.ui.map.map_utils

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem

data class ClusterUtils(
    private val position: LatLng,
    private val title: String,
    private val snippet: String,
    private val status: Boolean
) : ClusterItem {

    override fun getSnippet(): String {
        return snippet
    }

    override fun getTitle(): String {
        return title
    }

    override fun getPosition(): LatLng {
        return position
    }

    fun getStatus(): Boolean {
        return status
    }

}