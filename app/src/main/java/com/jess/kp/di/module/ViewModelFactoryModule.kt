package com.jess.kp.di.module

import androidx.lifecycle.ViewModelProvider
import com.jess.kp.di.ViewModelFactory
import dagger.Binds
import dagger.Module

/**
 * @author jess
 * @since 2020.06.12
 */
@Module
abstract class ViewModelFactoryModule {
    @Binds
    abstract fun bindViewModelFactory(
        factory: ViewModelFactory
    ): ViewModelProvider.Factory
}
