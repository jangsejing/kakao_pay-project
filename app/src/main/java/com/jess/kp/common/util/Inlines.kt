package com.jess.kp.common.util

import timber.log.Timber

/**
 * 예외처리
 *
 * @param action
 */
inline fun tryCatch(action: () -> Unit) {
    try {
        action()
    } catch (e: Exception) {
        e.printStackTrace()
        Timber.e(e)
    }
}