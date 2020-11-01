package com.nguyen.tccovid19k

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class CovidViewModel3 : ViewModel() {
    private val repository = Repository3()

    fun fetchWorld() : LiveData<World> = repository.fetchWorld()

    fun fetchCountries(page: Int) : LiveData<Data> = repository.fetchCountries(page)
}