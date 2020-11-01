package com.nguyen.tccovid19k

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.nguyen.tccovid19k.databinding.FragmentBottomSheetBinding

class BottomSheetFragment() : BottomSheetDialogFragment() {
    lateinit var binding: FragmentBottomSheetBinding
    lateinit var country: Country

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        country = arguments?.getSerializable(EXTRA_COUNTRY_OBJECT) as Country
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentBottomSheetBinding.inflate(inflater, container, false)
        val pieDataSet = PieDataSet(getData(), "")
        val colors = listOf(
            getColor(R.color.color_infected),
            getColor(R.color.color_recovered),
            getColor(R.color.color_dead)
        )
        pieDataSet.colors = colors
        val pieData = PieData(pieDataSet)
        binding.pieChart.description.isEnabled = false
        binding.pieChart.legend.isEnabled = false
        binding.pieChart.data = pieData
        binding.pieChart.invalidate()

        binding.bottomTitleCountry.text = country.country

        binding.bottomInfoTotal.text = country.totalCases
        binding.bottomInfoInfected.text = country.activeCases
        binding.bottomInfoRecovered.text = country.totalRecovered
        binding.bottomInfoDead.text = country.totalDeaths

        return binding.root
    }

    private fun getData() : List<PieEntry> {
        val entries = mutableListOf<PieEntry>()
        entries.add(PieEntry(toFloat(country.activeCases), "Infected"))
        entries.add(PieEntry(toFloat(country.totalRecovered), "Recovered"))
        entries.add(PieEntry(toFloat(country.totalDeaths), "Dead"))
        return entries
    }

    private fun toFloat(string: String) : Float {
        return if (string == "N/A") {
            0f
        } else {
            string.toFloat()
        }
    }

    private fun getColor(resource: Int) : Int {
        return ContextCompat.getColor(requireContext(), resource)
    }

    companion object {
        const val EXTRA_COUNTRY_OBJECT = "COUNTRY_OBJECT"

        fun show(context: Context, country: Country) {
            val fragment = BottomSheetFragment()
            val args = Bundle()
            args.putSerializable(EXTRA_COUNTRY_OBJECT, country)
            fragment.arguments = args
            fragment.show((context as MainActivity1).supportFragmentManager, fragment.tag)
        }
    }
}