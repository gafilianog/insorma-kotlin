package dev.gafilianog.insorma.ui.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import dev.gafilianog.insorma.R

class AboutFragment : Fragment() {

    private val locationLat = 6.171296
    private val locationLong = 106.753855

    private val callback = OnMapReadyCallback { googleMap ->
        val insormaOutlet = LatLng(locationLat, locationLong)
        googleMap.addMarker(MarkerOptions().position(insormaOutlet).title("Marker in inSOrma"))
        val camPos = CameraPosition.Builder().target(insormaOutlet).zoom(5f).build()
        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(camPos))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_about, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
        // TODO: Check internet needed?
    }
}