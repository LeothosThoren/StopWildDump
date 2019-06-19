package fr.leothosthoren.stopwilddump.ui.map.map_utils

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.clustering.Cluster
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.clustering.view.DefaultClusterRenderer
import com.google.maps.android.ui.IconGenerator
import fr.leothosthoren.stopwilddump.R
import kotlinx.android.synthetic.*


data class CustomClusterRenderer(
    val context: Context?,
    val googleMap: GoogleMap,
    val clusterManager: ClusterManager<ClusterUtils>
) : DefaultClusterRenderer<ClusterUtils>(context, googleMap, clusterManager) {

    override fun onBeforeClusterItemRendered(item: ClusterUtils, markerOptions: MarkerOptions?) {

        val iconGenerator = IconGenerator(context?.applicationContext)

        val view: View = if (item.getStatus()) {
            inflate(context, R.layout.custom_clean_marker_layout, null)
        } else {
            inflate(context, R.layout.custom_trash_marker_layout, null)
        }

        iconGenerator.setContentView(view)

        val bitmap = iconGenerator.makeIcon()
        markerOptions?.icon(BitmapDescriptorFactory.fromBitmap(bitmap))?.snippet(item.title)
    }

    override fun onBeforeClusterRendered(cluster: Cluster<ClusterUtils>?, markerOptions: MarkerOptions?) {

        val clusterIconGenerator = IconGenerator(context?.applicationContext)
        val clusterBackground = inflate(context, R.layout.custom_cluster_marker_layout, null)
        clusterIconGenerator.setBackground(null)
        clusterIconGenerator.setContentView(clusterBackground)

        val bitmap = clusterIconGenerator.makeIcon(cluster?.size.toString())
        markerOptions?.icon(BitmapDescriptorFactory.fromBitmap(bitmap))
    }

    override fun shouldRenderAsCluster(cluster: Cluster<ClusterUtils>): Boolean {
        return cluster.size > 1
    }

    fun createCustomMarker(context: Context, @DrawableRes resource: Int): Bitmap {

        val marker = (context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(
            R.layout.custom_trash_marker_layout, null
        )

        val markerImage = marker.findViewById(R.id.trashIcon) as ImageView
        markerImage.setImageResource(resource)
        //val txt_name = marker.findViewById(R.id.name) as TextView
        //txt_name.text = _name

        val displayMetrics = DisplayMetrics()
        (context as Activity).windowManager.defaultDisplay.getMetrics(displayMetrics)
        marker.layoutParams = ViewGroup.LayoutParams(52, ViewGroup.LayoutParams.WRAP_CONTENT)
        marker.measure(displayMetrics.widthPixels, displayMetrics.heightPixels)
        marker.layout(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels)
        marker.clearFindViewByIdCache()
        val bitmap = Bitmap.createBitmap(marker.measuredWidth, marker.measuredHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        marker.draw(canvas)

        return bitmap
    }
}