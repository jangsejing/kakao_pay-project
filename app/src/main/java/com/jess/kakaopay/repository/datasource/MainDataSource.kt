package com.jess.kakaopay.repository.datasource

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import androidx.recyclerview.widget.DiffUtil
import com.jess.kakaopay.common.base.BaseDataSource
import com.jess.kakaopay.data.MovieData
import com.jess.kakaopay.di.DispatcherProvider
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

    private var isRequestInProgress = false
    private var lastRequestedPage = 1

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
        query: String?,
        isNext: Boolean = false
    ): LiveData<List<MovieData.Item>> {
        isRequestInProgress = true

        val response = repository.getMovie(query, lastRequestedPage)
        val items = if (response.isSuccessful) {
            response.body()?.items ?: listOf()
        } else {
            listOf()
        }

        if (items.isNotEmpty()) {
            _isClear.postValue(isNext)
            lastRequestedPage++
        }

        isRequestInProgress = false

        _movieItems.postValue(items)
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
        lastRequestedPage = 1
    }

    /**
     * 다음 페이지
     */
    override suspend fun getNextPage() {
        if (isRequestInProgress) return
        queryLiveData.value?.let {
            requestMovie(it)
        }
    }

    /**
     * Diff Callback
     */
    override val diffCallback = object : DiffUtil.ItemCallback<MovieData.Item>() {
        override fun areItemsTheSame(
            oldItem: MovieData.Item,
            newItem: MovieData.Item
        ): Boolean {
            return oldItem.link == newItem.link
                    && oldItem.title == oldItem.title
        }

        override fun areContentsTheSame(
            oldItem: MovieData.Item,
            newItem: MovieData.Item
        ): Boolean {
            return oldItem == newItem
        }
    }
}
