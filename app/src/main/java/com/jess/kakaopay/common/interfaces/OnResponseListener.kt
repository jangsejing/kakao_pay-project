package com.jess.kakaopay.common.interfaces

import com.jess.kakaopay.data.ErrorData
import timber.log.Timber

/**
 * @author jess
 * @since 2020.06.17
 */
interface OnResponseListener<T> {
    fun onSuccess(response: T?)

    fun onError(
        error: ErrorData
    ) {
        Timber.d(error.toString())
    }
}