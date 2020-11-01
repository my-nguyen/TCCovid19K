package com.nguyen.tccovid19k

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nguyen.tccovid19k.databinding.FragmentTotalStatsBinding

abstract class TotalStatsFragment : Fragment() {

    lateinit var binding: FragmentTotalStatsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentTotalStatsBinding.inflate(inflater, container, false)
        fetchWorld()
        return binding.root
    }

    fun bind(world: World) {
        binding.confirmedLastUpdate.text = world.lastUpdate
        binding.confirmedCount.text = world.totalCases
        binding.infectedLastUpdate.text = world.lastUpdate
        binding.infectedCount.text = world.currentlyInfected
        binding.recoveredLastUpdate.text = world.lastUpdate
        binding.recoveredCount.text = world.recoveryCases
        binding.deadLastUpdate.text = world.lastUpdate
        binding.deadCount.text = world.deathCases
    }

    abstract fun fetchWorld()
}