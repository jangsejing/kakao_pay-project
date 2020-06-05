package com.jess.kp.di.module

import com.jess.kp.repository.network.TempRepository
import com.jess.kp.repository.network.TempRepositoryImpl
import com.jess.kp.repository.network.service.TempService
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