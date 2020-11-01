package com.nguyen.tccovid19k

import android.view.View
import com.loopj.android.http.JsonHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONObject

class CovidMapFragment1 : CovidMapFragment() {
    override fun getAllCountries() {
        binding.progressBar.visibility = View.VISIBLE
        // binding.covidMap.visibility = View.GONE

        if (adapter.totalPages != 0 && adapter.currentPage == adapter.totalPages) {
            loadMap()
        } else {
            NetworkClient1.fetchCountries(adapter.currentPage+1, object : JsonHttpResponseHandler() {
                override fun onSuccess(statusCode: Int, headers: Array<out Header>?, response: JSONObject?) {
                    response?.let {
                        val countries = Countries(it)
                        adapter.update(countries.data)
                        val pagination = countries.data.pagination
                        if (pagination.currentPage < pagination.totalPages) {
                            getAllCountries()
                        } else {
                            loadMap()
                        }
                    }
                }
            })
        }
    }
}