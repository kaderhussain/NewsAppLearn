package com.kader.newsappkdr.ui.news_sources

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kader.newsappkdr.data.model.Article
import com.kader.newsappkdr.data.model.NewsSources
import com.kader.newsappkdr.databinding.NewsSourcesItemLayoutBinding
import com.kader.newsappkdr.ui.topheadline.TopHeadlineAdapter

class NewsSourcesAdapter (
    private val newSourceList: ArrayList<NewsSources>
) : RecyclerView.Adapter<NewsSourcesAdapter.DataViewHolder>() {

    class DataViewHolder(private val binding: NewsSourcesItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(article: NewsSources) {
            binding.textViewTitle.text = article.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            NewsSourcesItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = newSourceList.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(newSourceList[position])

    fun addData(list: List<NewsSources>) {
        newSourceList.addAll(list)
    }

}