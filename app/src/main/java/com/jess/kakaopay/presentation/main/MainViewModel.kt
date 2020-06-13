package com.jess.kakaopay.presentation.main

import androidx.lifecycle.viewModelScope
import com.jess.kakaopay.common.base.BaseViewModel
import com.jess.kakaopay.repository.datasource.MainDataSource
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author jess
 * @since 2020.06.12
 */
class MainViewModel @Inject constructor(
    private val dataSource: MainDataSource
) : BaseViewModel(dataSource) {

    val moveItems = dataSource.movieItems

    fun getMovie(query: String?) {

        if (query.isNullOrEmpty()) {
            return
        }

        viewModelScope.launch {
            dataSource.getMovieData(query)
        }
    }
}
