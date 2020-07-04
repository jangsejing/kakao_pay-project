package com.jess.kakaopay.presentation.main

import androidx.lifecycle.viewModelScope
import com.jess.kakaopay.common.base.BaseDataSource
import com.jess.kakaopay.common.base.BaseViewModel
import com.jess.kakaopay.domain.datasource.MainDataSource
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author jess
 * @since 2020.06.12
 */
class MainViewModel @Inject constructor(
    private val dataSource: MainDataSource
) : BaseViewModel() {

    override var baseDataSource: BaseDataSource? = dataSource

    val moveItems = dataSource.movieItems
    val isClear = dataSource.isClear

    /**
     * 영화 검색
     */
    fun getMovie(query: String?) {
        viewModelScope.launch {
            delay(500)
            dataSource.getMovieData(query)
        }
    }

    /**
     * 영화 다음 페이지 검색
     */
    fun getNextPage() {
        viewModelScope.launch {
            dataSource.getNextPage()
        }
    }
}
