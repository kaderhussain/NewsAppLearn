package com.kader.newsappkdr.ui.topheadline

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kader.newsappkdr.data.local.entity.Article
import com.kader.newsappkdr.databinding.TopHeadlineItemLayoutBinding

class TopHeadlineAdapter(
    private val apiArticleList: ArrayList<Article>
) : RecyclerView.Adapter<TopHeadlineAdapter.DataViewHolder>() {

    class DataViewHolder(private val binding: TopHeadlineItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(apiArticle: Article) {
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            TopHeadlineItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = apiArticleList.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(apiArticleList[position])


    fun addData(list: List<Article>) {
        apiArticleList.addAll(list)
    }

}