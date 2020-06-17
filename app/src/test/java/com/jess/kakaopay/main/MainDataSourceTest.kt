package com.jess.kakaopay.main

import com.jess.kakaopay.base.BaseTest
import com.jess.kakaopay.data.MovieData
import com.jess.kakaopay.repository.NaverRepository
import com.jess.kakaopay.repository.datasource.MainDataSourceImpl
import com.jess.kakaopay.repository.service.NaverService
import com.jess.kakaopay.util.DispatcherProviderTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import retrofit2.Response

/**
 * @author jess
 * @since 2020.06.16
 */
@ExperimentalCoroutinesApi
class MainDataSourceTest : BaseTest() {

    private lateinit var dataSource: MainDataSourceImpl

    override fun setUp() {
        super.setUp()
//        dataSource = MainDataSourceImpl(FakeNaverRepository(), DispatcherProviderTest())
    }

//    inner class FakeNaverRepository  {
//
//        override val displayCount: Int = 20
//
//        override suspend fun getMovie(query: String?, start: Int): Response<MovieData> {
//
//        }
//    }
}