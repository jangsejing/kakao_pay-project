package com.jess.kakaopay.repository.datasource

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jess.kakaopay.common.base.BaseDataSource
import com.jess.kakaopay.data.MovieData
import com.jess.kakaopay.di.DispatcherProvider
import com.jess.kakaopay.repository.NaverRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * @author jess
 * @since 2020.06.12
 */
interface MainDataSource : BaseDataSource {
    // variables
    val movieItems: LiveData<List<MovieData.Item>>
    // functions
    suspend fun getMovieData(query: String?)
}

class MainDataSourceImpl @Inject constructor(
    private val repository: NaverRepository,
    override val dispatcher: DispatcherProvider
) : MainDataSource {

    private val _movieItems = MutableLiveData<List<MovieData.Item>>()
    override val movieItems: LiveData<List<MovieData.Item>> get() = _movieItems

    override suspend fun getMovieData(query: String?) {
        _movieItems.value = reqMovieData(query)
    }

    private suspend fun reqMovieData(query: String?) = withContext(dispatcher.io()) {
        repository.getMovie(query).body()?.items
    }
}
