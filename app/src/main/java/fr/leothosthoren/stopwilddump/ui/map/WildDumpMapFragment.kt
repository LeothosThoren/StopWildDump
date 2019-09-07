package fr.leothosthoren.stopwilddump.ui.map

import android.Manifest
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.maps.android.clustering.Cluster
import com.google.maps.android.clustering.ClusterManager
import fr.leothosthoren.stopwilddump.R
import fr.leothosthoren.stopwilddump.base.BaseMapFragment
import fr.leothosthoren.stopwilddump.ui.common.CommonViewModel
import fr.leothosthoren.stopwilddump.ui.map.map_utils.ClusterItem
import fr.leothosthoren.stopwilddump.ui.map.map_utils.CustomClusterRenderer
import fr.leothosthoren.stopwilddump.ui.map.map_utils.CustomInfoWindowAdapter
import kotlinx.android.synthetic.main.include_map_type_button.*
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.EasyPermissions

class WildDumpMapFragment : BaseMapFragment(),
    ClusterManager.OnClusterClickListener<ClusterItem>,
    ClusterManager.OnClusterInfoWindowClickListener<ClusterItem>,
    ClusterManager.OnClusterItemClickListener<ClusterItem>,
    ClusterManager.OnClusterItemInfoWindowClickListener<ClusterItem> {

    companion object {
        const val REQUEST_CODE_LOCATION = 123
        val FRANCE = LatLng(47.1932998, 2.4416936)
    }

    private lateinit var googleMap: GoogleMap
    private lateinit var clusterManager: ClusterManager<ClusterItem>
    private val sharedViewModel by lazy {
        activity?.let {
            ViewModelProviders.of(it).get(CommonViewModel::class.java)
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_wild_dump_map

    override fun getMapViewId(): Int = R.id.mapView

    override fun onMapReady(map: GoogleMap) {
        googleMap = map
        centeringMapWithinAnArea()
        enableMyLocation()
        changeMapType()
        setUpClusterer()
    }

    //*********
    //  Action
    //*********
    override fun onClusterClick(p0: Cluster<ClusterItem>?): Boolean {
        Toast.makeText(context, "onClusterItemInfoWindowClick + ${p0?.size}", Toast.LENGTH_SHORT)
            .show()
        return true
    }

    override fun onClusterInfoWindowClick(p0: Cluster<ClusterItem>?) {
        Toast.makeText(context, "onClusterItemInfoWindowClick + ${p0?.size}", Toast.LENGTH_SHORT)
            .show()
    }

    override fun onClusterItemClick(p0: ClusterItem?): Boolean {
        Toast.makeText(context, "onClusterItemInfoWindowClick + ${p0?.title}", Toast.LENGTH_SHORT)
            .show()
        return true
    }

    override fun onClusterItemInfoWindowClick(p0: ClusterItem?) {
        Toast.makeText(context, "onClusterItemInfoWindowClick + ${p0?.snippet}", Toast.LENGTH_SHORT)
            .show()
    }

    //*************
    //  Permission
    //*************
    /*** Override the onRequestPermissionResult to use EasyPermissions */
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    /**
     * enableMyLocation() will enable the location of the map if the user has given permission
     * for the app to access their device location.
     * Android Studio requires an explicit check before setting map.isMyLocationEnabled to true
     * but we are using EasyPermissions to handle it so we can suppress the "MissingPermission"
     * check.
     */
    @SuppressLint("MissingPermission")
    @AfterPermissionGranted(REQUEST_CODE_LOCATION)
    private fun enableMyLocation() {
        if (hasLocationPermission()) {
            googleMap.isMyLocationEnabled = true
        } else {
            EasyPermissions.requestPermissions(
                this, getString(R.string.app_name),
                REQUEST_CODE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION
            )
        }
    }

    private fun hasLocationPermission(): Boolean {
        return EasyPermissions.hasPermissions(context!!, Manifest.permission.ACCESS_FINE_LOCATION)
    }

    private fun changeMapType() {
        var selected = false
        standardType.setOnClickListener {
            googleMap.mapType = GoogleMap.MAP_TYPE_NORMAL
            standardType.run {
                setBackgroundResource(R.color.colorPrimaryDark)
                setTextColor(resources.getColor(android.R.color.white, null))
            }
        }
        terrainType.setOnClickListener { googleMap.mapType = GoogleMap.MAP_TYPE_TERRAIN }
        hybridType.setOnClickListener { googleMap.mapType = GoogleMap.MAP_TYPE_HYBRID }
        satelliteType.setOnClickListener { googleMap.mapType = GoogleMap.MAP_TYPE_SATELLITE }
    }

    private fun centeringMapWithinAnArea() {
        val france = LatLngBounds(FRANCE, FRANCE)
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(france.center, 5.5f))
    }

    private fun setUpClusterer() {
        clusterManager = ClusterManager(context, googleMap)

        googleMap.run {
            setOnCameraIdleListener(clusterManager)
            setOnMarkerClickListener(clusterManager)
            setOnInfoWindowClickListener(clusterManager)
            setInfoWindowAdapter(clusterManager.markerManager)
        }
        setUpClusterRenderer()
    }

    private fun setUpClusterRenderer() {
        val renderer =
            CustomClusterRenderer(context, googleMap, clusterManager)
        clusterManager.renderer = renderer

        val listOfClusterItems = listOf(addDumpMarkersOnMap())
        clusterManager.addItems(listOfClusterItems)

        // New
        clusterManager.run {
            markerCollection.setOnInfoWindowAdapter(
                CustomInfoWindowAdapter(
                    LayoutInflater.from(
                        context
                    )
                )
            ) // new
            setOnClusterItemInfoWindowClickListener {
                Toast.makeText(
                    context,
                    "onClusterItemInfoWindowClick + ${it.title}",
                    Toast.LENGTH_SHORT
                ).show()
            } // new
            setOnClusterInfoWindowClickListener {
                Toast.makeText(context, "onClusterInfoWindowClick + ${it.size}", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun addDumpMarkersOnMap(): ClusterItem? {
        var offsetItem: ClusterItem? = null
        sharedViewModel?.wildDumpData?.observe(this, Observer { dumpData ->
            for (i in 0 until dumpData?.wildDumps?.size!!) {
                offsetItem =
                    ClusterItem(
                        position = LatLng(
                            dumpData.wildDumps[i]?.latitude!!,
                            dumpData.wildDumps[i]?.longitude!!
                        ),
                        title = dumpData.wildDumps[i]?.name!!,
                        snippet = dumpData.wildDumps[i]?.town!!,
                        status = dumpData.wildDumps[i]?.type?.contains("Ramassage")!!,
                        type = 1
                    )
                clusterManager.addItem(offsetItem)
            }
        })
        return offsetItem
    }

    private fun addLandfillMarkersOnMap(): ClusterItem? {
        var offsetItem: ClusterItem? = null
        sharedViewModel?.landfills?.observe(this, Observer { landFill ->
            for (features in landFill?.features!!) {
                offsetItem =
                    ClusterItem(
                        position = LatLng(
                            features?.geometry?.coordinates?.get(1)!!,
                            features.geometry.coordinates[0]!!
                        ),
                        title = features.properties?.nomDeLaDecheterie!!,
                        snippet = features.properties.commune!!,
                        status = true,
                        type = 2
                    )
                clusterManager.addItem(offsetItem)
            }
        })
        return offsetItem
    }
}
