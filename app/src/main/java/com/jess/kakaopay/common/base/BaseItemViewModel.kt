package com.jess.kakaopay.common.base

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

/**
 * @author jess
 * @since 2020.06.12
 */
open class BaseItemViewModel {

    // IO Dispatchers
    val ioDispatchers: CoroutineContext = Dispatchers.IO

    // UI Dispatchers
    val uiDispatchers: CoroutineContext = Dispatchers.Main
}