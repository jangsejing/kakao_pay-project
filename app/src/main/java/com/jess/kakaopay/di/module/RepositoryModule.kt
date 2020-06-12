package com.jess.kakaopay.di.module

import com.jess.kakaopay.repository.network.TempRepository
import com.jess.kakaopay.repository.network.TempRepositoryImpl
import com.jess.kakaopay.repository.network.service.TempService
import dagger.Module
import dagger.Provides

/**
 * @author jess
 * @since 2020.06.12
 */
@Module
class RepositoryModule {

    @Provides
    fun provideGoogleRepository(
        service: TempService
    ): TempRepository {
        return TempRepositoryImpl(service)
    }
}