package com.nguyen.tccovid19k

import android.view.View
import com.loopj.android.http.JsonHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONObject

class RegionStatsFragment1 : RegionStatsFragment() {
    override fun fetchPage(page: Int) {
        binding.progressBar.visibility = View.VISIBLE
        binding.regionStats.visibility = View.GONE

        NetworkClient1.fetchCountries(page, object : JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Array<out Header>?, response: JSONObject?) {
                binding.progressBar.visibility = View.GONE
                binding.regionStats.visibility = View.VISIBLE

                response?.let {
                    val countries = Countries(it)
                    adapter.update(countries.data)
                }
            }
        })
    }
}