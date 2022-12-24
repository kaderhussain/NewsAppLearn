package com.kader.newsappkdr.viewmodel_test

import app.cash.turbine.test
import com.kader.newsappkdr.data.api.NetworkHelper
import com.kader.newsappkdr.data.local.entity.Article
import com.kader.newsappkdr.data.repository.TopHeadlineRepository
import com.kader.newsappkdr.ui.topheadline.TopHeadlineViewModel
import com.kader.newsappkdr.utils.DispatcherProvider
import com.kader.newsappkdr.utils.Resource
import com.kader.newsappkdr.utils.TestDispatcherProvider
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class TopHeadLineViewModelTest {

    @Mock
    private lateinit var topHeadlineRepository: TopHeadlineRepository

    @Mock
    private lateinit var networkHelper: NetworkHelper

    private lateinit var dispatcherProvider: DispatcherProvider

    @Before
    fun setUp() {
        dispatcherProvider = TestDispatcherProvider()

    }




    @Test
    fun givenServerResponse200_whenFetch_shouldReturnSuccess() {
        runTest {
            val country="us"
            doReturn(flowOf(emptyList<Article>())).`when`(topHeadlineRepository).getTopHeadlines(country)
            doReturn(true).`when`(networkHelper).isNetworkConnected()
            val viewModel = TopHeadlineViewModel(topHeadlineRepository, networkHelper, dispatcherProvider)

            viewModel.fetchNews(country)

            viewModel.apiArticleList.test{
                assertEquals(Resource.Success(emptyList<List<Article>>()), awaitItem())
                cancelAndIgnoreRemainingEvents() //removing the observer
            }

            //
            verify(topHeadlineRepository).getTopHeadlines(country)
            verify(networkHelper).isNetworkConnected()

        }
    }

    @Test
    fun givenDBData_whenFetch_shouldReturnSuccess() {
        runTest {
            val country="us"
            doReturn(flowOf(emptyList<Article>())).`when`(topHeadlineRepository).getTopHeadlineDirectlyFromDB()
            doReturn(false).`when`(networkHelper).isNetworkConnected()
            val viewModel = TopHeadlineViewModel(topHeadlineRepository, networkHelper, dispatcherProvider)

            viewModel.fetchNews(country)

            viewModel.apiArticleList.test{
                assertEquals(Resource.Success(emptyList<List<Article>>()), awaitItem())
                cancelAndIgnoreRemainingEvents()
            }

            //
            verify(topHeadlineRepository).getTopHeadlineDirectlyFromDB()
            verify(networkHelper).isNetworkConnected()

        }
    }



    @Test
    fun givenServerResponseError_whenFetch_shouldReturnError() {
        runTest {
            val country="us"

            val errorMessage = "Error Message For You"
            doReturn(flow<List<Article>> {
                throw IllegalStateException(errorMessage)
            }).`when`(topHeadlineRepository).getTopHeadlines(country)

            doReturn(true).`when`(networkHelper).isNetworkConnected()

            val viewModel = TopHeadlineViewModel(topHeadlineRepository, networkHelper, dispatcherProvider)
            viewModel.fetchNews(country)

            viewModel.apiArticleList.test {
                assertEquals(
                    Resource.Error<List<Article>>(IllegalStateException(errorMessage).toString()),
                    awaitItem()
                )
                cancelAndIgnoreRemainingEvents()
            }
            verify(topHeadlineRepository).getTopHeadlines(country)
            verify(networkHelper).isNetworkConnected()

        }
    }

    @After
    fun tearDown() {
        // do something if required
    }


}