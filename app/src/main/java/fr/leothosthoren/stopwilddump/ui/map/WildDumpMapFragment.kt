package fr.leothosthoren.stopwilddump.ui.map

import android.Manifest
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.maps.android.clustering.ClusterManager
import fr.leothosthoren.stopwilddump.R
import fr.leothosthoren.stopwilddump.ui.common.CommonViewModel
import fr.leothosthoren.stopwilddump.ui.map.map_utils.ClusterUtils
import fr.leothosthoren.stopwilddump.ui.map.map_utils.CustomClusterRenderer
import kotlinx.android.synthetic.main.include_map_type_button.*
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.EasyPermissions


class WildDumpMapFragment : Fragment(), OnMapReadyCallback {

    private lateinit var mMapView: MapView
    private lateinit var googleMap: GoogleMap
    private lateinit var clusterManager: ClusterManager<ClusterUtils>
    private lateinit var sharedViewModel: CommonViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_wild_dump_map, container, false)
        var mapViewBundle: Bundle? = null
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAPVIEW_BUNDLE_KEY)
        }
        mMapView = rootView.findViewById(R.id.mapView)
        mMapView.onCreate(mapViewBundle)
        mMapView.onResume()

        try {
            MapsInitializer.initialize(activity!!.applicationContext)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        mMapView.getMapAsync(this)

        return rootView
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        var mapViewBundle = outState.getBundle(MAPVIEW_BUNDLE_KEY)
        if (mapViewBundle == null) {
            mapViewBundle = Bundle()
            outState.putBundle(MAPVIEW_BUNDLE_KEY, mapViewBundle)
        }

        mMapView.onSaveInstanceState(mapViewBundle)
    }

    override fun onStart() {
        super.onStart()
        mMapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        mMapView.onResume()
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map

        centeringMapWithinAnArea()
        enableMyLocation()
        changeMapType()
        setUpClusterer()
    }


    /* private fun initGoogleMap() {
         val googleMapUtils = GoogleMapUtils(context!!, googleMap, clusterManager)
         googleMapUtils.centeringMapWithinAnArea()
         activity?.let {
             sharedViewModel = ViewModelProviders.of(it).get(CommonViewModel::class.java)
             val listItem = sharedViewModel.wildDumpData.value?.wildDumps
             googleMapUtils.setUpClusterer(listItem)
         }
     }*/

    private fun changeMapType() {
        standardType.setOnClickListener { googleMap.mapType = GoogleMap.MAP_TYPE_NORMAL }
        terrainType.setOnClickListener { googleMap.mapType = GoogleMap.MAP_TYPE_TERRAIN }
        hybridType.setOnClickListener { googleMap.mapType = GoogleMap.MAP_TYPE_HYBRID }
        satelliteType.setOnClickListener { googleMap.mapType = GoogleMap.MAP_TYPE_SATELLITE }
    }

    private fun centeringMapWithinAnArea() {
        val france = LatLngBounds(FRANCE, FRANCE)
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(france.center, 5.5f))
    }

    private fun setUpClusterer() {
        clusterManager = ClusterManager<ClusterUtils>(context, googleMap)
        googleMap.apply {
            setOnCameraIdleListener(clusterManager)
            setOnMarkerClickListener(clusterManager)
        }

        setUpClusterRenderer()
        addMarkersOnMap()
    }

    private fun setUpClusterRenderer() {
        val renderer =
            CustomClusterRenderer(context, googleMap, clusterManager)
        clusterManager.renderer = renderer
    }


    private fun addMarkersOnMap() {
        activity?.let {
            sharedViewModel = ViewModelProviders.of(it).get(CommonViewModel::class.java)
            val listItem = sharedViewModel.wildDumpData.value?.wildDumps
            for (i in 0 until listItem?.size!!) {
                val offsetItem = ClusterUtils(
                    LatLng(
                        listItem[i]?.latitude!!,
                        listItem[i]?.longitude!!
                    ), listItem[i]?.name!!,
                    listItem[i]?.town!!,
                    listItem[i]?.type?.contains("Ramassage")!!
                )
                Log.d("DEBUG", "wildumptype = ${listItem[i]?.type?.contains("Ramassage")!!} ->  ${listItem[i]?.type}")
                clusterManager.addItem(offsetItem)
            }
        }
    }


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


    override fun onPause() {
        mMapView.onPause()
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
        mMapView.onStop()
    }

    override fun onDestroy() {
        mMapView.onDestroy()
        super.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mMapView.onLowMemory()
    }

    companion object {
        const val REQUEST_CODE_LOCATION = 123
        const val MAPVIEW_BUNDLE_KEY = "MapViewBundleKey"
        val FRANCE = LatLng(47.1932998, 2.4416936)

    }
}
