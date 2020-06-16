package com.jess.kakaopay.common.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jess.kakaopay.repository.datasource.MainDataSource

/**
 * @author jess
 * @since 2020.06.12
 */
abstract class BaseViewModel() : ViewModel() {

    private val _status = MutableLiveData<BaseStatus>()
    val status: LiveData<BaseStatus> = _status

    fun onProgress(isShow: Boolean) {
        _status.value = BaseStatus.Progress(isShow)
    }

    fun onToast(message: String) {
        _status.value = BaseStatus.Toast(message)
    }

}