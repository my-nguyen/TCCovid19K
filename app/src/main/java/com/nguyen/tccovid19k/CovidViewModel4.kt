package com.nguyen.tccovid19k

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class CovidViewModel4 @Inject constructor(val repository: Repository4) : ViewModel() {
    fun fetchWorld() : LiveData<World> = repository.fetchWorld()

    fun fetchCountries(page: Int) : LiveData<Data> = repository.fetchCountries(page)
}