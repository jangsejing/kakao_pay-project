package com.jess.kakaopay.di.module

import com.jess.kakaopay.BuildConfig
import com.jess.kakaopay.common.constant.Network
import com.jess.kakaopay.repository.service.NaverService
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * @author jess
 * @since 2020.06.12
 */
@Module
class NetworkModule {

    companion object {
        const val NETWORK_TIME_OUT: Long = 5
    }

    @Provides
    @Singleton
    fun provideInterceptor(): Interceptor {
        return Interceptor { chain ->
            val original = chain.request()
            val request = original.newBuilder().apply {
                header("X-Naver-Client-Id", Network.naverClientId)
                header("X-Naver-Client-Secret", Network.naverClientSecret)
            }.build()
            chain.proceed(request)
        }
    }

    @Provides
    @Singleton
    fun createClient(interceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder().apply {
            connectTimeout(NETWORK_TIME_OUT, TimeUnit.SECONDS)
            readTimeout(NETWORK_TIME_OUT, TimeUnit.SECONDS)
            if (BuildConfig.DEBUG) {
                addInterceptor(
                    HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
                )
            }
            addInterceptor(interceptor)
        }.build()
    }

    @Singleton
    @Provides
    fun provideNaverService(
        okHttpClient: OkHttpClient
    ): NaverService {
        return Retrofit.Builder()
            .baseUrl(Network.naverUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NaverService::class.java)
    }
}