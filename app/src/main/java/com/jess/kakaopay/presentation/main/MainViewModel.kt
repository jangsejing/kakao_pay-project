package com.jess.kakaopay.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.jess.kakaopay.common.base.BaseStatus
import com.jess.kakaopay.common.base.BaseViewModel
import com.jess.kakaopay.common.extension.safeScope
import com.jess.kakaopay.data.MovieData
import com.jess.kakaopay.repository.NaverRepository
import com.jess.kakaopay.repository.datasource.MainDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

/**
 * @author jess
 * @since 2020.06.12
 */
class MainViewModel @Inject constructor(
    private val repository: NaverRepository
) : BaseViewModel() {

    fun getMovie() = liveData {
        onProgress(true)
        withContext(ioScope) {
            repository.getMovie("마블").body()?.let {
                emit(it.items)
            }
        }
        onProgress(false)
    }
}
