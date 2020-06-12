package com.jess.kakaopay.common.util

import timber.log.Timber

/**
 * @author jess
 * @since 2020.06.12
 */

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