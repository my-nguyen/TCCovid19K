package com.nguyen.tccovid19k

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository3() {
    fun fetchWorld() : LiveData<World> {
        val world = MutableLiveData<World>()
        NetworkClient2.fetchWorld(object : Callback<WorldGson> {
            override fun onResponse(call: Call<WorldGson>, response: Response<WorldGson>) {
                world.value = response.body()?.data
            }

            override fun onFailure(call: Call<WorldGson>, t: Throwable) {
                world.value = null
            }
        })
        return world
    }

    fun fetchCountries(page: Int) : LiveData<Data> {
        val data = MutableLiveData<Data>()
        NetworkClient2.fetchCountries(page, object : Callback<Countries> {
            override fun onResponse(call: Call<Countries>, response: Response<Countries>) {
                data.value = response.body()?.data
            }

            override fun onFailure(call: Call<Countries>, t: Throwable) {
                data.value = null
            }
        })
        return data
    }
}