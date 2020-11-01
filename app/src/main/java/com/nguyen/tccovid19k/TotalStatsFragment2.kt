package com.nguyen.tccovid19k

import android.view.View
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TotalStatsFragment2 : TotalStatsFragment() {
    override fun fetchWorld() {
        binding.progressBar.visibility = View.VISIBLE
        binding.totalStats.visibility = View.GONE

        NetworkClient2.fetchWorld(object : Callback<WorldGson> {
            override fun onResponse(call: Call<WorldGson>, response: Response<WorldGson>) {
                binding.progressBar.visibility = View.GONE
                binding.totalStats.visibility = View.VISIBLE

                response.body()?.let {
                    val world = it.data
                    bind(world)
                }
            }

            override fun onFailure(call: Call<WorldGson>, t: Throwable) {
                // Log.d(TAG, "onFailure")
            }
        })
    }
}