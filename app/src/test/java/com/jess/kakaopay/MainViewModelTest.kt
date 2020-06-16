package com.jess.kakaopay

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.jess.kakaopay.base.BaseTest
import com.jess.kakaopay.data.MovieData
import com.jess.kakaopay.di.provider.DispatcherProvider
import com.jess.kakaopay.presentation.main.MainViewModel
import com.jess.kakaopay.repository.datasource.MainDataSource
import com.jess.kakaopay.util.DispatcherTestProvider
import com.jess.kakaopay.util.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Test


/**
 * @author jess
 * @since 2020.06.16
 */
class MainViewModelTest : BaseTest() {

    private val dataSource = FakeDataSource()
    private lateinit var viewModel: MainViewModel

    override fun setUp() {
        super.setUp()
        viewModel = MainViewModel(dataSource)
    }

    @Test
    fun `query 입력하면 isClear가 true인가?`() {
        val isClear = viewModel.isClear.getOrAwaitValue(time = 5)
        println(isClear)
        viewModel.isClear.getOrAwaitValue()
    }

}

/**
 * Fake DataSource
 */
@ExperimentalCoroutinesApi
class FakeDataSource : MainDataSource {

    companion object {
        const val QUERY = "mavel"
    }

    override val dispatcher: DispatcherProvider get() = DispatcherTestProvider()

    private val _isClear = MutableLiveData<Boolean>(false)
    override val isClear: LiveData<Boolean> = _isClear

    private val queryLiveData = MutableLiveData<String>()

    private val _movieItems = MutableLiveData<List<MovieData.Item>>()
    override val movieItems: LiveData<List<MovieData.Item>> =
        queryLiveData.switchMap { queryString ->
            liveData(dispatcher.io()) {
                // 영화 데이터 요청
                emitSource(requestMovie(queryString))
            }
        }

    override suspend fun getMovieData(query: String?) {
        _isClear.value = true
        queryLiveData.value = query
    }

    override suspend fun getNextPage() {

    }

    private suspend fun requestMovie(
        query: String?
    ): LiveData<List<MovieData.Item>> {
        println("requestMovie")
        return _movieItems
    }


}