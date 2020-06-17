package com.jess.kakaopay.common.`interface`

import com.jess.kakaopay.data.ErrorData

interface OnResponseListener<T> {
    fun onSuccess(response: T)

    fun onError(
        error: ErrorData
    )
}