package com.kader.newsappkdr.ui.languages

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kader.newsappkdr.data.model.Countries
import com.kader.newsappkdr.data.model.Language
import com.kader.newsappkdr.databinding.CountriesItemLayoutBinding
import com.kader.newsappkdr.ui.countries.CountryAdapter

class LanguageAdapter (
    private val languageList: ArrayList<Language>
) : RecyclerView.Adapter<LanguageAdapter.DataViewHolder>() {

    class DataViewHolder(private val binding: CountriesItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(language: Language) {
            binding.textViewTitle.text = language.Name
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

    override fun getItemCount(): Int = languageList.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(languageList[position])

    fun addData(list: List<Language>) {
        languageList.addAll(list)
    }

}