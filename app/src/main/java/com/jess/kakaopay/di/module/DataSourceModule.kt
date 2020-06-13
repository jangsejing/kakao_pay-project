package com.jess.kakaopay.di.module

import com.jess.kakaopay.repository.datasource.MainDataSource
import com.jess.kakaopay.repository.datasource.MainDataSourceImpl
import dagger.Binds
import dagger.Module

/**
 * @author jess
 * @since 2020.06.13
 */
@Module
abstract class DataSourceModule {

    @Binds
    abstract fun bindMainDataSource(dataSource: MainDataSourceImpl): MainDataSource

}

