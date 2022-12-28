package com.kader.newsappkdr.ui.topheadline

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kader.newsappkdr.data.local.entity.Article
import com.kader.newsappkdr.data.model.ApiArticle
import com.kader.newsappkdr.databinding.TopHeadlineItemLayoutBinding
import com.kader.newsappkdr.ui.search.SearchViewAdapter

class TopHeadlineAdapter() : PagingDataAdapter<Article, TopHeadlineAdapter.DataViewHolder>(differCallback) {

    companion object {
        private val differCallback = object : DiffUtil.ItemCallback<Article>() {
            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem.url == newItem.url
            }

            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem == newItem
            }
        }
    }

    private val differCallback = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }


    class DataViewHolder(private val binding: TopHeadlineItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(apiArticle: Article?) {
            apiArticle?.let {
                binding.textViewTitle.text = apiArticle.title
                binding.textViewDescription.text = apiArticle.description
                binding.textViewSource.text = apiArticle.sourceName
                Glide.with(binding.imageViewBanner.context)
                    .load(apiArticle.imageUrl)
//                .placeholder(com.google.android.material.R.drawable.ic_mtrl_chip_close_circle)
                    .into(binding.imageViewBanner)
                itemView.setOnClickListener {
                    val builder = CustomTabsIntent.Builder()
                    val customTabsIntent = builder.build()
                    customTabsIntent.launchUrl(it.context, Uri.parse(apiArticle.url))
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            TopHeadlineItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )


    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(getItem(position))


}