package com.nguyen.tccovid19k

import android.content.Context
import android.view.View
import androidx.lifecycle.Observer
import javax.inject.Inject

class TotalStatsFragment4 : TotalStatsFragment() {
    @Inject
    lateinit var viewModel: CovidViewModel4

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity?.application as MyApplication).appComponent.inject(this)
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