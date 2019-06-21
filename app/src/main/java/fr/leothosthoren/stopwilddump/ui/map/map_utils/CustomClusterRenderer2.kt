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


data class CustomClusterRenderer2(
    val context: Context?,
    val googleMap: GoogleMap,
    val clusterManager: ClusterManager<ClusterUtils>
) : DefaultClusterRenderer<ClusterUtils>(context, googleMap, clusterManager) {

    override fun onBeforeClusterItemRendered(item: ClusterUtils, markerOptions: MarkerOptions?) {

        val iconGenerator = IconGenerator(context?.applicationContext)
        val view: View = inflate(context, R.layout.custom_clean_marker_layout, null)
        iconGenerator.setBackground(null)
        iconGenerator.setContentView(view)

        val bitmap = iconGenerator.makeIcon()
        markerOptions?.icon(BitmapDescriptorFactory.fromBitmap(bitmap))
    }

    override fun onBeforeClusterRendered(cluster: Cluster<ClusterUtils>?, markerOptions: MarkerOptions?) {

        val clusterIconGenerator = IconGenerator(context?.applicationContext)
        val clusterBackground = inflate(context, R.layout.custom_cluster_green_marker_layout, null)
        clusterIconGenerator.setBackground(null)
        clusterIconGenerator.setContentView(clusterBackground)

        val bitmap = clusterIconGenerator.makeIcon(cluster?.size.toString())
        markerOptions?.icon(BitmapDescriptorFactory.fromBitmap(bitmap))
    }

    override fun shouldRenderAsCluster(cluster: Cluster<ClusterUtils>): Boolean {
        return cluster.size > 1
    }
}