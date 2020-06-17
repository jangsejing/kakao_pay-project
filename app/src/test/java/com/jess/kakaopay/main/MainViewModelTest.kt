package com.jess.kakaopay.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jess.kakaopay.base.BaseTest
import com.jess.kakaopay.data.MovieData
import com.jess.kakaopay.di.provider.DispatcherProvider
import com.jess.kakaopay.presentation.main.MainViewModel
import com.jess.kakaopay.repository.datasource.MainDataSource
import com.jess.kakaopay.util.DispatcherProviderTest
import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

/**
 * @author jess
 * @since 2020.06.17
 */
@ExperimentalCoroutinesApi
class MainViewModelTest : BaseTest() {

    private lateinit var viewModel: MainViewModel

    override fun setUp() {
        super.setUp()
        viewModel = MainViewModel(FakeMainDataSource())
    }

    @Test
    fun `query 입력 후 isClear가 true인가?`() = runBlockingTest {
        viewModel.getMovie("테스트")
        coroutineRule.advanceTimeBy(500)
        assertTrue(viewModel.isClear.value ?: false)
    }

    @Test
    fun `query 입력 후 moveItems의 데이터가 존재하는가?`() = runBlockingTest {
        viewModel.getMovie("테스트")
        coroutineRule.advanceTimeBy(500)
        assertTrue(viewModel.moveItems.value?.isNotEmpty() ?: false)
    }

    @Test
    fun `다음 페이지를 호출할 때 isClear가 false인가?`() {
        viewModel.getNextPage()
        assertFalse(viewModel.isClear.value ?: false)
    }

    @Test
    fun `다음 페이지 호출할 때 moveItems의 데이터가 존재하는가?`() {
        viewModel.getNextPage()
        assertTrue(viewModel.moveItems.value?.isNotEmpty() ?: false)
    }
}

/**
 * Fake DataSource
 */
@ExperimentalCoroutinesApi
class FakeMainDataSource : MainDataSource {

    override val dispatcher: DispatcherProvider get() = DispatcherProviderTest()

    private val _isClear = MutableLiveData(false)
    override val isClear: LiveData<Boolean> get() = _isClear

    private val _movieItems = MutableLiveData<List<MovieData.Item>>()
    override val movieItems: LiveData<List<MovieData.Item>> get() = _movieItems

    override suspend fun getMovieData(query: String?) {
        _isClear.value = true
        _movieItems.value = getFakeResponse().items
    }

    override suspend fun getNextPage() {
        _movieItems.value = getFakeResponse().items
    }

    /**
     * 데이터 생성
     *
     * @return
     */
    private fun getFakeResponse(): MovieData {
        val list = arrayListOf<MovieData.Item>()
        for (i in 0..10) {
            list.add(
                MovieData.Item(
                    "title $i",
                    "link $i",
                    "image $i",
                    "subtitle $i",
                    "pubDate $i",
                    "director $i",
                    "actor: String $i",
                    "userRating $i"
                )
            )
        }

        return MovieData(
            "Wed, 17 Jun 2020 13:14:41 +0900",
            10,
            1,
            10,
            list
        )
    }
}