package fr.leothosthoren.stopwilddump.ui.map.map_utils

import android.content.Context
import android.view.View
import android.view.View.inflate
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.clustering.Cluster
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.clustering.view.DefaultClusterRenderer
import com.google.maps.android.ui.IconGenerator
import fr.leothosthoren.stopwilddump.R

data class CustomClusterRenderer(
    val context: Context?,
    val googleMap: GoogleMap,
    val clusterManager: ClusterManager<ClusterItem>
) : DefaultClusterRenderer<ClusterItem>(context, googleMap, clusterManager) {

    override fun onBeforeClusterItemRendered(item: ClusterItem, markerOptions: MarkerOptions?) {

        val iconGenerator = IconGenerator(context?.applicationContext)

        val view: View = if (item.getStatus()) {
            inflate(context, R.layout.custom_clean_marker_layout, null)
        } else {
            inflate(context, R.layout.custom_trash_marker_layout, null)
        }
        iconGenerator.setBackground(null)
        iconGenerator.setContentView(view)

        val bitmap = iconGenerator.makeIcon()
        markerOptions?.icon(BitmapDescriptorFactory.fromBitmap(bitmap))?.snippet(item.title)
    }

    override fun onBeforeClusterRendered(cluster: Cluster<ClusterItem>?, markerOptions: MarkerOptions?) {
        var clusterBackground: View? = null
        val clusterIconGenerator = IconGenerator(context?.applicationContext)

        cluster?.items?.forEach { cl ->
            clusterBackground = if (cl.getType() == 2) {
                inflate(context, R.layout.custom_cluster_green_marker_layout, null)
            } else {
                inflate(context, R.layout.custom_cluster_purple_marker_layout, null)
            }
        }
        clusterIconGenerator.setBackground(null)
        clusterIconGenerator.setContentView(clusterBackground)

        val bitmap = clusterIconGenerator.makeIcon(cluster?.size.toString())
        markerOptions?.icon(BitmapDescriptorFactory.fromBitmap(bitmap))
    }

    override fun shouldRenderAsCluster(cluster: Cluster<ClusterItem>): Boolean {
        return cluster.size > 1
    }
}