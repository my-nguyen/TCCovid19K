package com.nguyen.tccovid19k

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nguyen.tccovid19k.databinding.ItemCountryBinding
import com.squareup.picasso.Picasso

class CountriesAdapter(val context: Context) : RecyclerView.Adapter<CountriesAdapter.ViewHolder>() {
    inner class ViewHolder(private val binding: ItemCountryBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        init {
            binding.root.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            val position = adapterPosition
            val country = countries[position]
            BottomSheetFragment.show(context, country)
        }

        fun bind(country: Country) {
            Picasso.get().load(country.flag).into(binding.countryFlag)
            binding.countryName.text = country.country
            val text = context.resources.getString(R.string.label_last_update, lastUpdate)
            binding.countryLastUpdate.text = text
            binding.countryCount.text = country.totalCases
        }
    }

    var currentPage = 0
    var totalPages = 0
    lateinit var lastUpdate: String
    val countries = mutableListOf<Country>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = ItemCountryBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val country = countries[position]
        holder.bind(country)
    }

    override fun getItemCount(): Int {
        return countries.size
    }

    fun update(data: Data) {
        if (data.pagination.currentPage == 1) {
            totalPages = data.pagination.totalPages
            lastUpdate = data.lastUpdate
        }
        currentPage = data.pagination.currentPage
        val size = itemCount
        countries.addAll(data.countries)
        notifyItemRangeInserted(size, data.countries.size)
    }
}