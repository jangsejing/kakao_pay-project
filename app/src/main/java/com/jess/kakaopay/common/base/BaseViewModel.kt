package com.jess.kakaopay.common.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

/**
 * @author jess
 * @since 2020.06.12
 */
abstract class BaseViewModel : ViewModel() {

    protected val _status = MutableLiveData<BaseStatus>()
    val status: LiveData<BaseStatus> = _status

    internal val mainScope = viewModelScope.coroutineContext + Dispatchers.Main
    internal val ioScope = viewModelScope.coroutineContext + Dispatchers.IO

    override fun onCleared() {
        super.onCleared()
    }

    fun onProgress(isShow: Boolean) {
        _status.value = BaseStatus.Progress(isShow)
    }

    fun onToast(message: String) {
        _status.value = BaseStatus.Toast(message)
    }
}