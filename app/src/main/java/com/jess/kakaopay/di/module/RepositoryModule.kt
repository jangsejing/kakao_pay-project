package com.jess.kakaopay.di.module

import com.jess.kakaopay.domain.repository.NaverRepository
import com.jess.kakaopay.domain.repository.NaverRepositoryImpl
import dagger.Binds
import dagger.Module

/**
 * @author jess
 * @since 2020.06.12
 */
@Module(includes = [NetworkModule::class])
abstract class RepositoryModule {

    @Binds
    abstract fun bindNaverRepository(impl: NaverRepositoryImpl): NaverRepository

}