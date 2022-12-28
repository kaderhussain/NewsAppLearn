package com.kader.newsappkdr.ui.search

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.kader.newsappkdr.NewsApplication
import com.kader.newsappkdr.data.model.ApiArticle
import com.kader.newsappkdr.databinding.ActivitySearchBinding
import com.kader.newsappkdr.di.component.DaggerActivityComponent
import com.kader.newsappkdr.di.module.ActivityModule
import com.kader.newsappkdr.utils.getQueryTextChangeStateFlow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchActivity : AppCompatActivity() {

    companion object {
        private const val EXTRA_COUNTRY = "EXTRA_COUNTRY"

        fun getStartIntent(context: Context): Intent {
            return Intent(context, SearchActivity::class.java)
        }

        fun getStartIntent(context: Context, country: String): Intent {
            return Intent(context, SearchActivity::class.java)
                .apply {
                    putExtra(EXTRA_COUNTRY, country)
                }
        }


    }

    @Inject
    lateinit var searchViewModel: SearchViewModel

    @Inject
    lateinit var adapter: SearchViewAdapter

    private lateinit var binding: ActivitySearchBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependencies()
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()

    }


    private fun setupUI() {
        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)

        val searchView = binding.searchView
        lifecycleScope.launch {
            searchView.getQueryTextChangeStateFlow()
                .debounce(300)
                .filter { query ->
                    val validQuery = query.isNotEmpty() && query.length > 3
                    if (!validQuery) {
                        binding.progressBar.visibility = View.GONE
//                        renderList(emptyList())
                        binding.recyclerView.visibility = View.VISIBLE

                    } else {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    return@filter validQuery

                }
                .distinctUntilChanged()
                .flowOn(Dispatchers.Main)
                .flatMapLatest { query ->
                    return@flatMapLatest searchViewModel.fetchSearchNews(query)
                        .catch {
//                            emitAll(flowOf(emptyList()))
                        }
                }
                .flowOn(Dispatchers.IO)
                .collect { result ->
                    binding.progressBar.visibility = View.GONE
                    result?.let { newsList -> renderList(newsList) }
                    binding.recyclerView.visibility = View.VISIBLE
                }
        }

    }


    private fun renderList(apiArticleList: PagingData<ApiArticle>) {
        adapter.submitData(lifecycle,apiArticleList)
    }

    private fun injectDependencies() {
        DaggerActivityComponent
            .builder()
            .applicationComponent((application as NewsApplication).applicationComponent)
            .activityModule(ActivityModule(this))
            .build()
            .injectSearchNews(this)
    }

}