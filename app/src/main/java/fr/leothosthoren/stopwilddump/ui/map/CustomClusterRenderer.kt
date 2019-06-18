package fr.leothosthoren.stopwilddump.ui.map

import android.content.Context
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
    val clusterManager: ClusterManager<ClusterUtils>
) : DefaultClusterRenderer<ClusterUtils>(context, googleMap, clusterManager) {

    override fun onBeforeClusterItemRendered(item: ClusterUtils?, markerOptions: MarkerOptions?) {
        val markerDescriptor = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)
        markerOptions?.icon(markerDescriptor)?.snippet(item?.title)
    }

    override fun onBeforeClusterRendered(cluster: Cluster<ClusterUtils>?, markerOptions: MarkerOptions?) {
        val clusterIconGenerator = IconGenerator(context?.applicationContext)
        val clusterBackground = inflate(context, R.layout.custom_cluster_marker, null)
        clusterIconGenerator.setBackground(null)
        clusterIconGenerator.setContentView(clusterBackground)

        val icon = clusterIconGenerator.makeIcon(cluster?.size.toString())
        markerOptions?.icon(BitmapDescriptorFactory.fromBitmap(icon))
    }

    override fun shouldRenderAsCluster(cluster: Cluster<ClusterUtils>): Boolean {
        return cluster.size > 1
    }
}