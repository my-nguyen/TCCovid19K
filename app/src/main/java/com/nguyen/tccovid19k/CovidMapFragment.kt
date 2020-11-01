package com.nguyen.tccovid19k

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.nguyen.tccovid19k.databinding.FragmentCovidMapBinding

abstract class CovidMapFragment : Fragment(), OnMapReadyCallback {
    lateinit var map: GoogleMap
    lateinit var binding: FragmentCovidMapBinding
    var adapter = CountriesAdapter(requireContext())
    var firstLatLng: LatLng? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentCovidMapBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getAllCountries()
    }

    override fun onMapReady(gmap: GoogleMap?) {
        gmap?.let {
            map = gmap

            val coordinates = Coordinates(requireContext(), "countries.csv")
            for (i in 0 until adapter.countries.size) {
                val country = adapter.countries[i]
                val latLng = coordinates.toLatLng(country.countryAbbreviation)
                latLng?.let {
                    val options = MarkerOptions().position(it).title(country.country)
                    val marker = map.addMarker(options)
                    marker.tag = i
                    if (firstLatLng == null) {
                        firstLatLng = it
                    }
                }
            }

            val cameraUpdate = CameraUpdateFactory.newLatLng(firstLatLng)
            map.moveCamera(cameraUpdate)

            map.setOnMarkerClickListener(object : GoogleMap.OnMarkerClickListener {
                override fun onMarkerClick(marker: Marker?): Boolean {
                    marker?.let {
                        val index = it.tag as Int
                        BottomSheetFragment.show(requireContext(), adapter.countries[index])
                    }
                    return false
                }
            })
        }

        binding.progressBar.visibility = View.GONE
        // binding.covidMap.visibility = View.VISIBLE
    }

    fun loadMap() {
        val mapFragment = childFragmentManager.findFragmentById(R.id.google_map) as SupportMapFragment
        mapFragment?.let {
            it.getMapAsync(this@CovidMapFragment)
        }
    }

    abstract fun getAllCountries()
}