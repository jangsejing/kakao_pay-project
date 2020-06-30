package com.jess.kakaopay.common.base

import androidx.lifecycle.ViewModel

/**
 * @author jess
 * @since 2020.06.12
 */
abstract class BaseViewModel : ViewModel() {

    open var baseDataSource: BaseDataSource? = null
    val onProgress by lazy { baseDataSource?.isRequest }

    override fun onCleared() {
        baseDataSource?.onCleared()
        super.onCleared()
    }
}