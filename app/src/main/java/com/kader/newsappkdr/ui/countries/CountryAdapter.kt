package com.kader.newsappkdr.ui.countries

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kader.newsappkdr.data.model.Country
import com.kader.newsappkdr.databinding.CountriesItemLayoutBinding
import com.kader.newsappkdr.ui.topheadline.TopHeadlineActivity

class CountryAdapter(
    private val countryList: ArrayList<Country>

) : RecyclerView.Adapter<CountryAdapter.DataViewHolder>() {

    class DataViewHolder(private val binding: CountriesItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(language: Country) {
            binding.textViewTitle.text = language.country
            binding.textViewTitle.setOnClickListener {
                it.context.startActivity(
                    TopHeadlineActivity.getStartIntent(
                        it.context,
                        language.id
                    )
                )
            }
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

    fun addData(list: List<Country>) {
        countryList.addAll(list)
    }

}