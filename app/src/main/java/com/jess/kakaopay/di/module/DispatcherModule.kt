package com.jess.kakaopay.di.module

import com.jess.kakaopay.di.DispatcherProvider
import com.jess.kakaopay.di.DispatcherProviderImpl
import dagger.Binds
import dagger.Module

/**
 * @author jess
 * @since 2020.06.13
 */
@Module
abstract class DispatcherModule {

    @Binds
    abstract fun bindDispatchers(dispatcher: DispatcherProviderImpl): DispatcherProvider

}

