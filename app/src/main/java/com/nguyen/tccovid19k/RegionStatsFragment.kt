package com.nguyen.tccovid19k

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nguyen.tccovid19k.databinding.FragmentRegionStatsBinding

abstract class RegionStatsFragment : Fragment() {
    lateinit var binding: FragmentRegionStatsBinding
    lateinit var adapter: CountriesAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentRegionStatsBinding.inflate(inflater, container, false)
        adapter = CountriesAdapter(requireContext())
        binding.recyclerView.adapter = adapter
        val layoutManager = LinearLayoutManager(activity)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.addOnScrollListener(object : EndlessRecyclerViewScrollListener(layoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                fetchPage(page + 1)
            }
        })
        // if switching to this screen (Region Stats) for the first time, fetch first page and display it
        // otherwise, the fetch has been done, so do nothing and the pages will be displayed
        if (adapter.countries.size == 0) {
            fetchPage(1)
        }

        return binding.root
    }

    abstract fun fetchPage(page: Int)
}