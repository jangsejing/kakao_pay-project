package com.jess.kakaopay.util

import com.jess.kakaopay.di.provider.DispatcherProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher

/**
 * Base Test
 *
 * @author jess
 * @since 2020.03.05
 */
@ExperimentalCoroutinesApi
class DispatcherTestProvider : DispatcherProvider {
    override fun main() = TestCoroutineDispatcher()
    override fun io() = TestCoroutineDispatcher()
}