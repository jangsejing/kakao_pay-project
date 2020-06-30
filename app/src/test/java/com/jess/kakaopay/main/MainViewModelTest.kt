package com.jess.kakaopay.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jess.kakaopay.base.BaseTest
import com.jess.kakaopay.data.MovieData
import com.jess.kakaopay.di.provider.DispatcherProvider
import com.jess.kakaopay.presentation.main.MainViewModel
import com.jess.kakaopay.domain.datasource.MainDataSource
import com.jess.kakaopay.util.DispatcherProviderTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
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
    fun `query 입력 후 isClear 확인 --- true 여부`() = runBlockingTest {
        viewModel.getMovie("테스트")
        coroutineRule.advanceTimeBy(500)
        assertTrue(viewModel.isClear.value ?: false)
    }

    @Test
    fun `query 입력 후 moveItems 데이이터 확인 --- 존재 여부`() = runBlockingTest {
        viewModel.getMovie("테스트")
        coroutineRule.advanceTimeBy(500)
        assertTrue(viewModel.moveItems.value?.isNotEmpty() ?: false)
    }

    @Test
    fun `다음 페이지를 호출할 때 isClear 확인 --- false 여부`() {
        viewModel.getNextPage()
        assertFalse(viewModel.isClear.value ?: false)
    }

    @Test
    fun `다음 페이지 호출할 때 moveItems의 데이터 확인 --- 존재 여부`() {
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

    private val _isRequest = MutableLiveData<Boolean>()
    override val isRequest: LiveData<Boolean> get() = _isRequest

    private val _isClear = MutableLiveData(false)
    override val isClear: LiveData<Boolean> get() = _isClear

    override val queryData = MutableLiveData<String>()

    override var isMorePage: Boolean = true
    override var startPage: Int = 1

    override fun reset() {

    }

    private val _movieItems = MutableLiveData<List<MovieData.Item>>()
    override val movieItems: LiveData<List<MovieData.Item>> get() = _movieItems

    override suspend fun getMovieData(query: String?) {
        _isClear.value = true
        _isRequest.value = true
        _movieItems.value = createFakeResponse().items
        _isRequest.value = false
    }

    override suspend fun getNextPage() {
        _movieItems.value = createFakeResponse().items
    }

    /**
     * 데이터 생성
     *
     * @return
     */
    private fun createFakeResponse(): MovieData {
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