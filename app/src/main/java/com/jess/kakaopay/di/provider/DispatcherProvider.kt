package com.jess.kakaopay.di.provider

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

/**
 * @author jess
 * @since 2020.06.12
 */
interface DispatcherProvider {
    val job: Job
    fun main(): CoroutineContext
    fun io(): CoroutineContext
}

class DispatcherProviderImpl @Inject constructor() : DispatcherProvider {
    override val job: Job = Job()
    override fun main(): CoroutineContext = Dispatchers.Main + job
    override fun io(): CoroutineContext = Dispatchers.IO+ job
}