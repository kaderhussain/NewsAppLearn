package com.kader.newsappkdr.repository_test

import app.cash.turbine.test
import com.kader.newsappkdr.data.api.NetworkServices
import com.kader.newsappkdr.data.local.DatabaseHelperImpl
import com.kader.newsappkdr.data.local.entity.Article
import com.kader.newsappkdr.data.model.ApiArticle
import com.kader.newsappkdr.data.model.TopHeadlinesResponse
import com.kader.newsappkdr.data.repository.TopHeadlineRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class TopHeadlineRepositoryTest {

    @Mock
    private lateinit var networkServices: NetworkServices

    @Mock
    private lateinit var databaseHelperImpl: DatabaseHelperImpl


    private lateinit var topHeadlineRepository: TopHeadlineRepository


    @Test
    fun givenServerResponse200_whenFetch_shouldReturnSuccess() {

        runTest {

            val country = "us"
            topHeadlineRepository = TopHeadlineRepository(networkServices, databaseHelperImpl)
            doReturn(TopHeadlinesResponse("", 0, emptyList<ApiArticle>())).`when`(networkServices)
                .getTopHeadlines(country)
            doReturn(0).`when`(databaseHelperImpl).deleteAll()
            doReturn(flowOf(Unit)).`when`(databaseHelperImpl).insertAll(emptyList<Article>())
            doReturn(flowOf(emptyList<Article>())).`when`(databaseHelperImpl).getTopHeadline()

            topHeadlineRepository.getTopHeadlines(country).test {

                cancelAndIgnoreRemainingEvents() //removing the observer
            }
            verify(networkServices).getTopHeadlines(country)
            verify(databaseHelperImpl).deleteAll()
            verify(databaseHelperImpl).insertAll(emptyList<Article>())
            verify(databaseHelperImpl).getTopHeadline()

        }

    }

}