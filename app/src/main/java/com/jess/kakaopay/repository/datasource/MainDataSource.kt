package com.jess.kakaopay.repository.datasource

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.jess.kakaopay.common.base.BaseDataSource
import com.jess.kakaopay.common.base.BaseDataSourceImpl
import com.jess.kakaopay.common.interfaces.OnResponseListener
import com.jess.kakaopay.common.manager.request
import com.jess.kakaopay.data.MovieData
import com.jess.kakaopay.di.provider.DispatcherProvider
import com.jess.kakaopay.repository.NaverRepository
import timber.log.Timber
import javax.inject.Inject

/**
 * @author jess
 * @since 2020.06.12
 */
interface MainDataSource : BaseDataSource {

    // variables
    var isMorePage: Boolean
    var startPage: Int
    val movieItems: LiveData<List<MovieData.Item>>
    val isClear: LiveData<Boolean>
    val queryData: MutableLiveData<String>

    // functions
    fun reset()
    suspend fun getMovieData(query: String?)
    suspend fun getNextPage()

}

class MainDataSourceImpl @Inject constructor(
    private val repository: NaverRepository,
    override val dispatcher: DispatcherProvider
) : BaseDataSourceImpl(), MainDataSource {

    override var isMorePage = true
    override var startPage = 1

    private val _isClear = MutableLiveData<Boolean>()
    override val isClear: LiveData<Boolean> get() = _isClear

    override val queryData = MutableLiveData<String>()

    private val _movieItems = MutableLiveData<List<MovieData.Item>>()
    override val movieItems = queryData.switchMap { queryString ->
        Timber.d(">> queryString $queryString")
        liveData(dispatcher.io()) {
            // 영화 데이터 요청
            emitSource(requestMovie(queryString))
        }
    }

    /**
     * 영화 검색
     */
    override suspend fun getMovieData(query: String?) {
        if (query.isNullOrEmpty()) return
        reset()
        queryData.value = query
    }

    /**
     * 다음 페이지
     */
    override suspend fun getNextPage() {
        if (isRequest.value == true) return
        Timber.d(">> getNextPage")
        queryData.value?.let {
            _isClear.postValue(false)
            requestMovie(it)
        }
    }

    /**
     * 페이징 정보 초기화
     */
    override fun reset() {
        startPage = 1
        isMorePage = true
        _isClear.postValue(true)
    }

    /**
     * API 통신
     */
    private suspend fun requestMovie(
        query: String?
    ): LiveData<List<MovieData.Item>> {
        if (isMorePage) {
            _isRequest.postValue(true)
            repository.getMovie(query, startPage).request(object : OnResponseListener<MovieData> {

                override fun onSuccess(response: MovieData?) {
                    response?.let {
                        isMorePage = it.isMorePage()
                        if (isMorePage) startPage = it.getStartNumber(repository.displayCount)
                        _movieItems.postValue(it.items)
                    }
                    _isRequest.postValue(false)
                }
            })
        }
        return _movieItems
    }
}
