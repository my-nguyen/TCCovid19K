package com.nguyen.tccovid19k

import android.content.Context
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class RegionStatsFragment3 : RegionStatsFragment() {
    lateinit var viewModel: CovidViewModel3

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = ViewModelProvider(this)[CovidViewModel3::class.java]
    }

    override fun fetchPage(page: Int) {
        binding.progressBar.visibility = View.VISIBLE
        binding.regionStats.visibility = View.GONE

        viewModel.fetchCountries(page).observe(this, object : Observer<Data> {
            override fun onChanged(data: Data?) {
                binding.progressBar.visibility = View.GONE
                binding.regionStats.visibility = View.VISIBLE

                adapter.update(data!!)
            }
        })
    }
}