package com.jess.kakaopay.repository.datasource

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.jess.kakaopay.common.base.BaseDataSource
import com.jess.kakaopay.data.MovieData
import com.jess.kakaopay.di.DispatcherProvider
import com.jess.kakaopay.repository.NaverRepository
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * @author jess
 * @since 2020.06.12
 */
interface MainDataSource : BaseDataSource {
    // variables
    val movieItems: LiveData<List<MovieData.Item>>
    val isClear: LiveData<Boolean>

    // functions
    suspend fun getMovieData(query: String?)
    suspend fun getMovieNextPage()
//    fun isLastPage(): LiveData<Boolean>
}

class MainDataSourceImpl @Inject constructor(
    private val repository: NaverRepository,
    override val dispatcher: DispatcherProvider
) : MainDataSource {

    private val _movieItems = MutableLiveData<List<MovieData.Item>>()
    override val movieItems: LiveData<List<MovieData.Item>> get() = _movieItems

    private val _isClear = MutableLiveData<Boolean>()
    override val isClear: LiveData<Boolean> get() = _isClear

    var query: String? = null
    var start: Int = 1

    /**
     * 영화 검색
     */
    override suspend fun getMovieData(query: String?) {
        this.query = query

        if (query.isNullOrEmpty()) {
            _movieItems.value = emptyList()
            return
        }

        _isClear.value = true
        _movieItems.value = reqMovieData(query, start)

//        isLastPage()
    }

    /**
     * 영화 데이터 요청
     */
    private suspend fun reqMovieData(query: String?, start: Int) = withContext(dispatcher.io()) {
        repository.getMovie(query, start).body()?.items
    }

    /**
     * 영화 다음 페이지 검색
     */
    override suspend fun getMovieNextPage() {
        getMovieData(query)
    }

//    /**
//     * 마지막 페이지 여부 판단
//     */
//    override fun isLastPage() = liveData {
//        _movieItems.value?.let {
//            val flag = if (it.isNullOrEmpty()) {
//                // 마지막페이지
//                true
//            } else {
//                start++
//                false
//            }
//            emit(flag)
//        } ?: run {
//            emit(true)
//        }
//    }
}
