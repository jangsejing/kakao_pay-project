package com.jess.kakaopay.repository.datasource

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import androidx.recyclerview.widget.DiffUtil
import com.jess.kakaopay.common.base.BaseDataSource
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
    val movieItems: LiveData<List<MovieData.Item>>
    val isClear: LiveData<Boolean>
    val diffCallback: DiffUtil.ItemCallback<MovieData.Item>

    // functions
    suspend fun getMovieData(query: String?)
    suspend fun getNextPage()
}

class MainDataSourceImpl @Inject constructor(
    private val repository: NaverRepository,
    override val dispatcher: DispatcherProvider
) : MainDataSource {

    private var isRequest = false
    private var isMorePage = true
    private var startPage = 1

    private val _isClear = MutableLiveData<Boolean>()
    override val isClear: LiveData<Boolean> get() = _isClear

    private val queryLiveData = MutableLiveData<String>()

    private val _movieItems = MutableLiveData<List<MovieData.Item>>()
    override val movieItems = queryLiveData.switchMap { queryString ->
        Timber.d(">> queryString $queryString")
        liveData(dispatcher.io()) {
            // 영화 데이터 요청
            emitSource(requestMovie(queryString))
        }
    }

    /**
     * API 통신
     */
    private suspend fun requestMovie(
        query: String?
    ): LiveData<List<MovieData.Item>> {
        isRequest = true

        if (isMorePage) {
            val response = repository.getMovie(query, startPage)
            response.body()?.let {
                val items = if (response.isSuccessful) {
                    it.items ?: listOf()
                } else {
                    listOf()
                }
                _movieItems.postValue(items)

                // 다음 시작 페이지
                isMorePage = it.isMorePage().also { isMore ->
                    if (isMore) startPage = it.getStartNumber(repository.displayCount)
                }

                Timber.d(">> query $query / isMorePage $isMorePage / startPage $startPage")
            }
        }

        isRequest = false
        return _movieItems
    }

    /**
     * 영화 검색
     */
    override suspend fun getMovieData(query: String?) {
        if (query.isNullOrEmpty()) return
        reset()
        queryLiveData.postValue(query)
    }

    private fun reset() {
        startPage = 1
        isMorePage = true
        _isClear.postValue(true)
    }

    /**
     * 다음 페이지
     */
    override suspend fun getNextPage() {
        if (isRequest) return
        Timber.d(">> getNextPage")
        queryLiveData.value?.let {
            requestMovie(it)
        }
    }

    /**
     * Diff Callback
     */
    override val diffCallback = object : DiffUtil.ItemCallback<MovieData.Item>() {

        // 고유 식별자 판단
        // areItemsTheSame 이 false 이면 areContentsTheSame 는 호출되지 않
        override fun areItemsTheSame(
            oldItem: MovieData.Item,
            newItem: MovieData.Item
        ): Boolean {
            return oldItem.title == newItem.title
                    && oldItem.subtitle == newItem.subtitle
        }

        // 같은 객체 인지 확
        override fun areContentsTheSame(
            oldItem: MovieData.Item,
            newItem: MovieData.Item
        ): Boolean {
            return oldItem == newItem
        }
    }
}
