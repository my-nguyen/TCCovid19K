package com.nguyen.tccovid19k

import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkClient2 {
    lateinit var service: CovidService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://corona-virus-stats.herokuapp.com/api/v1/cases/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create(CovidService::class.java)
    }

    fun fetchWorld(callback: Callback<WorldGson>) {
        service.fetchWorld().enqueue(callback)
    }

    fun fetchCountries(page: Int, callback: Callback<Countries>) {
        service.fetchCountries("total_cases", page).enqueue(callback)
    }
}