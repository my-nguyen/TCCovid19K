package com.nguyen.tccovid19k

import android.view.View
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegionStatsFragment2 : RegionStatsFragment() {
    override fun fetchPage(page: Int) {
        binding.progressBar.visibility = View.VISIBLE
        binding.regionStats.visibility = View.GONE

        NetworkClient2.fetchCountries(page, object : Callback<Countries> {
            override fun onResponse(call: Call<Countries>, response: Response<Countries>) {
                binding.progressBar.visibility = View.GONE
                binding.regionStats.visibility = View.VISIBLE

                response.body()?.let {
                    val countries = it
                    adapter.update(countries.data)
                }
            }

            override fun onFailure(call: Call<Countries>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}