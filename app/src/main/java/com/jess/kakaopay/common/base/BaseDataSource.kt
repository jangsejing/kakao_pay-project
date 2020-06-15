package com.jess.kakaopay.common.base

import com.jess.kakaopay.di.provider.DispatcherProvider
import kotlinx.coroutines.cancel

abstract interface BaseDataSource {

    val dispatcher: DispatcherProvider

    fun onCleared() {
        dispatcher.run {
            io().cancel()
            main().cancel()
        }
    }
}