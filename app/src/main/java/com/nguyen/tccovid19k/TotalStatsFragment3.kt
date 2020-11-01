package com.nguyen.tccovid19k

import android.content.Context
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider




class TotalStatsFragment3 : TotalStatsFragment() {
    lateinit var viewModel: CovidViewModel3

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = ViewModelProvider(this)[CovidViewModel3::class.java]
    }

    override fun fetchWorld() {
        binding.progressBar.visibility = View.VISIBLE
        binding.totalStats.visibility = View.GONE

        viewModel.fetchWorld().observe(this, object : Observer<World> {
            override fun onChanged(world: World?) {
                binding.progressBar.visibility = View.GONE
                binding.totalStats.visibility = View.VISIBLE
                bind(world!!)
            }
        })
    }
}