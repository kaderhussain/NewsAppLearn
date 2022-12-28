package com.kader.newsappkdr.ui.search

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

class SearchViewAdapter(
) : PagingDataAdapter<ApiArticle, SearchViewAdapter.DataViewHolder>(differCallback) {

    companion object {
        private val differCallback = object : DiffUtil.ItemCallback<ApiArticle>() {
            override fun areItemsTheSame(oldItem: ApiArticle, newItem: ApiArticle): Boolean {
                return oldItem.url == newItem.url
            }

            override fun areContentsTheSame(oldItem: ApiArticle, newItem: ApiArticle): Boolean {
                return oldItem == newItem
            }
        }
    }

    class DataViewHolder(private val binding: TopHeadlineItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(apiArticle: ApiArticle?) {
            apiArticle?.let {
                binding.textViewTitle.text = it.title
                binding.textViewDescription.text = it.description
                binding.textViewSource.text = it.apiSource.name
                Glide.with(binding.imageViewBanner.context)
                    .load(it.imageUrl)
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