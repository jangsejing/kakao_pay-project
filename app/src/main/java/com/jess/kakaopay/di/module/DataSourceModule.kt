package com.jess.kakaopay.di.module

import com.jess.kakaopay.di.DispatcherProvider
import com.jess.kakaopay.repository.datasource.main.MainDataSource
import com.jess.kakaopay.repository.datasource.main.MainDataSourceImpl
import dagger.Module
import dagger.Provides

@Module
class DataSourceModule {

    @Provides
    fun provideMainDataSource(
        dispatcher: DispatcherProvider
    ): MainDataSource {
        return MainDataSourceImpl()
    }
}