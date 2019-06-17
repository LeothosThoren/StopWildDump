package fr.leothosthoren.stopwilddump.ui.map

import android.Manifest
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import fr.leothosthoren.stopwilddump.R
import fr.leothosthoren.stopwilddump.ui.common.CommonViewModel
import kotlinx.android.synthetic.main.include_map_type_button.*
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.EasyPermissions

const val REQUEST_CODE_LOCATION = 123

class WildDumpMapFragment : Fragment(), OnMapReadyCallback {

    private lateinit var mMapView: MapView
    private lateinit var googleMap: GoogleMap
    private lateinit var sharedViewModel: CommonViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_wild_dump_map, container, false)

        mMapView = rootView.findViewById(R.id.mapView)
        mMapView.onCreate(savedInstanceState)

        mMapView.onResume()

        try {
            MapsInitializer.initialize(activity!!.applicationContext)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        mMapView.getMapAsync(this)

        return rootView
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
        addMarkersOnMap()
    }


    private fun changeMapType() {
        standardType.setOnClickListener { googleMap.mapType = GoogleMap.MAP_TYPE_NORMAL }
        terrainType.setOnClickListener { googleMap.mapType = GoogleMap.MAP_TYPE_TERRAIN }
        hybridType.setOnClickListener { googleMap.mapType = GoogleMap.MAP_TYPE_HYBRID }
        satelliteType.setOnClickListener { googleMap.mapType = GoogleMap.MAP_TYPE_SATELLITE }
    }

    private fun centeringMapWithinAnArea() {
        val france = LatLngBounds(LatLng(47.1932998, 2.4416936), LatLng(47.1932998, 2.4416936))
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(france.center, 5.5f))
    }

    private fun addMarkersOnMap() {
        activity?.let {
            sharedViewModel = ViewModelProviders.of(it).get(CommonViewModel::class.java)
            val listSize = sharedViewModel.wildDumpData.value?.wildDumps
            for (i in 0 until listSize?.size!!) {
                googleMap.addMarker(
                    MarkerOptions().position(
                        LatLng(
                            listSize[i]?.latitude!!,
                            listSize[i]?.longitude!!
                        )
                    ).title(listSize[i]?.name)
                )
            }
        }
    }

    /** Override the onRequestPermissionResult to use EasyPermissions */
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

}
