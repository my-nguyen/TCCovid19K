package com.nguyen.tccovid19k

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {
    @Provides
    fun provideGsonConverterFactory() = GsonConverterFactory.create()

    @Provides
    fun provideRetrofit(factory: GsonConverterFactory) : Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://corona-virus-stats.herokuapp.com/api/v1/cases/")
            .addConverterFactory(factory)
            .build()
    }

    @Provides
    fun provideCovidService(retrofit: Retrofit) : CovidService = retrofit.create(CovidService::class.java)
}