package com.kader.newsappkdr.ui.countries

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kader.newsappkdr.data.model.Countries
import com.kader.newsappkdr.databinding.CountriesItemLayoutBinding
import com.kader.newsappkdr.databinding.NewsSourcesItemLayoutBinding
import com.kader.newsappkdr.ui.news_sources.NewsSourcesAdapter

class CountryAdapter(
    private val countryList: ArrayList<Countries>
) : RecyclerView.Adapter<CountryAdapter.DataViewHolder>() {

    class DataViewHolder(private val binding: CountriesItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Countries) {
            binding.textViewTitle.text = article.country
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            CountriesItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = countryList.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(countryList[position])

    fun addData(list: List<Countries>) {
        countryList.addAll(list)
    }

}