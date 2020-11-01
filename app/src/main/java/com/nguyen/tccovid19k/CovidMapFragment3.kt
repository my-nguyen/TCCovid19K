package com.nguyen.tccovid19k

import android.content.Context
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class CovidMapFragment3 : CovidMapFragment() {
    lateinit var viewModel: CovidViewModel3

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = ViewModelProvider(this)[CovidViewModel3::class.java]
    }

    override fun getAllCountries() {
        binding.progressBar.visibility = View.VISIBLE
        // binding.covidMap.visibility = View.GONE

        if (adapter.totalPages != 0 && adapter.currentPage == adapter.totalPages) {
            loadMap()
        } else {
            viewModel.fetchCountries(adapter.currentPage+1).observe(this, object : Observer<Data> {
                override fun onChanged(data: Data?) {
                    data?.let {
                        adapter.update(it)
                        val pagination = it.pagination
                        if (pagination.currentPage < pagination.totalPages) {
                            getAllCountries()
                        } else {
                            loadMap()
                        }
                    }
                }
            })
        }
    }
}