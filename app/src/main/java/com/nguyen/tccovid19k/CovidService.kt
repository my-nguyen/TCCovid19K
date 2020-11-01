package com.nguyen.tccovid19k

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CovidService {
    @GET("general-stats")
    fun fetchWorld() : Call<WorldGson>

    @GET("countries-search")
    fun fetchCountries(@Query("order") order: String, @Query("page") page: Int) : Call<Countries>
}