package com.nguyen.tccovid19k

import android.content.Context
import android.view.View
import androidx.lifecycle.Observer
import javax.inject.Inject

class RegionStatsFragment4 : RegionStatsFragment() {
    @Inject
    lateinit var viewModel: CovidViewModel4

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity?.application as MyApplication).appComponent.inject(this)
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