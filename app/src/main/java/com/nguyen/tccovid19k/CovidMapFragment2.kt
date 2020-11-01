package com.nguyen.tccovid19k

import android.view.View
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CovidMapFragment2 : CovidMapFragment() {
    override fun getAllCountries() {
        binding.progressBar.visibility = View.VISIBLE
        // binding.covidMap.visibility = View.GONE

        if (adapter.totalPages != 0 && adapter.currentPage == adapter.totalPages) {
            loadMap()
        } else {
            NetworkClient2.fetchCountries(adapter.currentPage+1, object : Callback<Countries> {
                override fun onResponse(call: Call<Countries>, response: Response<Countries>) {
                    response.body()?.let {
                        val countries = it
                        adapter.update(countries.data)
                        val pagination = it.data.pagination
                        if (pagination.currentPage < pagination.totalPages) {
                            getAllCountries()
                        } else {
                            loadMap()
                        }
                    }
                }

                override fun onFailure(call: Call<Countries>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
        }
    }
}