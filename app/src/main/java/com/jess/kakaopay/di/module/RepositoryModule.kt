package com.jess.kakaopay.di.module

import com.jess.kakaopay.repository.NaverRepository
import com.jess.kakaopay.repository.NaverRepositoryImpl
import com.jess.kakaopay.repository.service.NaverService
import dagger.Module
import dagger.Provides

/**
 * @author jess
 * @since 2020.06.12
 */
@Module(includes = [NetworkModule::class])
class RepositoryModule {

    @Provides
    fun provideNaverRepository(
        service: NaverService
    ): NaverRepository {
        return NaverRepositoryImpl(service)
    }
}