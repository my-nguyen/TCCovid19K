package com.nguyen.tccovid19k

import android.view.View
import com.loopj.android.http.JsonHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONObject

public class TotalStatsFragment1 : TotalStatsFragment() {
    override fun fetchWorld() {
        binding.progressBar.visibility = View.VISIBLE
        binding.totalStats.visibility = View.GONE

        NetworkClient1.fetchWorld(object : JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Array<out Header>?, response: JSONObject?) {
                binding.progressBar.visibility = View.GONE
                binding.totalStats.visibility = View.VISIBLE
                response?.let {
                    val world = World(it)
                    bind(world)
                }
            }

            override fun onFailure(statusCode: Int, headers: Array<out Header>?, responseString: String?, throwable: Throwable?) {
                super.onFailure(statusCode, headers, responseString, throwable)
            }
        })
    }
}