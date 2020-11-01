package com.nguyen.tccovid19k

import android.util.Log
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.JsonHttpResponseHandler

object NetworkClient1 {
    private val client = AsyncHttpClient()

    fun fetchWorld(handler: JsonHttpResponseHandler) {
        Log.d(TAG, "fetchWorld")
        val url = "${COVID_BASE_URL}general-stats"
        client.get(url, handler)
    }

    fun fetchCountries(page: Int, handler: JsonHttpResponseHandler) {
        Log.d(TAG, "fetchCountries")
        val url = "${COVID_BASE_URL}countries-search?order=total_cases&page=${page}"
        client.get(url, handler)
    }

    const val TAG = "NetworkClient1"
    const val COVID_BASE_URL = "https://corona-virus-stats.herokuapp.com/api/v1/cases/"
}