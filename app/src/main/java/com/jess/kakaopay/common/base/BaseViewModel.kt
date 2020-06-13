package com.jess.kakaopay.common.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jess.kakaopay.repository.datasource.MainDataSource

/**
 * @author jess
 * @since 2020.06.12
 */
abstract class BaseViewModel(
    private val dataSource: MainDataSource? = null
) : ViewModel() {

    private val _status = MutableLiveData<BaseStatus>()
    val status: LiveData<BaseStatus> = _status

    override fun onCleared() {
        dataSource?.onCleared()
        super.onCleared()
    }

    fun onProgress(isShow: Boolean) {
        _status.value = BaseStatus.Progress(isShow)
    }

    fun onToast(message: String) {
        _status.value = BaseStatus.Toast(message)
    }

}