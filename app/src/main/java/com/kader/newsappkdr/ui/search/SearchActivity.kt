package com.kader.newsappkdr.ui.search

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.kader.newsappkdr.NewsApplication
import com.kader.newsappkdr.R
import com.kader.newsappkdr.data.model.Article
import com.kader.newsappkdr.databinding.ActivitySearchBinding
import com.kader.newsappkdr.databinding.ActivityTopHeadlineBinding
import com.kader.newsappkdr.di.component.DaggerActivityComponent
import com.kader.newsappkdr.di.module.ActivityModule
import com.kader.newsappkdr.ui.topheadline.TopHeadlineActivity
import com.kader.newsappkdr.ui.topheadline.TopHeadlineAdapter
import com.kader.newsappkdr.ui.topheadline.TopHeadlineViewModel
import com.kader.newsappkdr.utils.Status
import com.kader.newsappkdr.utils.getQueryTextChangeStateFlow
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class SearchActivity : AppCompatActivity() {

    companion object{
        private const val EXTRA_COUNTRY="EXTRA_COUNTRY"

        fun getStartIntent(context: Context): Intent {
            return Intent(context, SearchActivity::class.java)
        }
        fun getStartIntent(context: Context, country:String): Intent {
            return Intent(context, SearchActivity::class.java)
                .apply {
                    putExtra(EXTRA_COUNTRY,country)
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
        setupObserver()


    }



    private fun setupUI(){
        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = adapter

        val searchView = binding.searchView
        lifecycleScope.launch {
            searchView.getQueryTextChangeStateFlow()
                .debounce(300)
                .distinctUntilChanged()
                .flatMapLatest { query ->
                    dataFromNetwork(query)
                        .catch {
                            emitAll(flowOf(""))
                        }
                }
                .flowOn(Dispatchers.Default)
                .collect { result ->
//                    textViewResult.text = result
                    Log.e("result ",result)
                    if(result.isNotEmpty()) {
                        searchViewModel.fetchNews(result)
                    }else{
                        searchViewModel.fetchDefault("us")
                    }
                }
        }

    }

    private fun dataFromNetwork(query: String): Flow<String> {
        return flow {
            delay(1000)
            emit(query)
        }
    }


    private fun setupObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                searchViewModel.articleList.collect {
                    when (it.status) {
                        Status.SUCCESS -> {
                            binding.progressBar.visibility = View.GONE
                            Log.e("newsList ","--${it.data}")
                            it.data?.let { newsList -> renderList(newsList) }
                            binding.recyclerView.visibility = View.VISIBLE
                        }
                        Status.LOADING -> {
                            binding.progressBar.visibility = View.VISIBLE
                            binding.recyclerView.visibility = View.GONE
                        }
                        Status.ERROR -> {
                            //Handle Error
                            binding.progressBar.visibility = View.GONE
                            Toast.makeText(this@SearchActivity, it.message, Toast.LENGTH_LONG)
                                .show()
                        }
                    }
                }
            }
        }
    }

    private fun renderList(articleList: List<Article>) {
        adapter.addData(articleList)
        adapter.notifyDataSetChanged()
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