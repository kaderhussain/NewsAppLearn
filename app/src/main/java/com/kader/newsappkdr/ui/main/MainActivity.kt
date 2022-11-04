package com.kader.newsappkdr.ui.main

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.kader.newsappkdr.NewsApplication
import com.kader.newsappkdr.databinding.ActivityMainBinding
import com.kader.newsappkdr.di.component.DaggerActivityComponent
import com.kader.newsappkdr.di.module.ActivityModule
import com.kader.newsappkdr.ui.countries.CountryActivity
import com.kader.newsappkdr.ui.languages.LanguageActivity
import com.kader.newsappkdr.ui.news_sources.NewsSourcesActivity
import com.kader.newsappkdr.ui.topheadline.TopHeadlineActivity
import com.kader.newsappkdr.utils.AppConstant
import javax.inject.Inject

class MainActivity: AppCompatActivity() {

    private val Tag="MainActivity"

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        super.onCreate(savedInstanceState)
        Log.e(Tag,"Started");
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setupUI()
//        setupObserver()
    }

    private fun setupUI(){
        val btnTopHeadline = binding.btnTopHeadline
        val btnNewsSources = binding.btnNewsSources
        val btnLanguages = binding.btnLanguages
        val btnCountries = binding.btnCountries
        val btnSearch = binding.btnSearch



        btnTopHeadline.setOnClickListener {
            startActivity(TopHeadlineActivity.getStartIntent(this))
        }

        btnNewsSources.setOnClickListener {
            startActivity(NewsSourcesActivity.getStartIntent(this))
        }

        btnCountries.setOnClickListener {
            startActivity(CountryActivity.getStartIntent(this))
        }

        btnLanguages.setOnClickListener {
            startActivity(LanguageActivity.getStartIntent(this))
        }
    }

    private fun injectDependencies() {
        DaggerActivityComponent
            .builder()
            .applicationComponent((application as NewsApplication).applicationComponent)
            .activityModule(ActivityModule(this))
            .build()
            .injectMain(this)
    }


}