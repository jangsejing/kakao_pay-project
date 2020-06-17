package com.jess.kakaopay.common.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * @author jess
 * @since 2020.06.12
 */
abstract class BaseViewModel() : ViewModel() {

    private val _status = MutableLiveData<BaseState>()
    val status: LiveData<BaseState> = _status

    fun onProgress(isShow: Boolean) {
        _status.value = BaseState.Progress(isShow)
    }

    fun onToast(message: String) {
        _status.value = BaseState.Toast(message)
    }

}