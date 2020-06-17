package com.jess.kakaopay.main

import com.jess.kakaopay.base.BaseTest
import com.jess.kakaopay.data.MovieData
import com.jess.kakaopay.di.module.NetworkModule
import com.jess.kakaopay.di.module.NetworkModule_ProvideNaverServiceFactory
import com.jess.kakaopay.repository.NaverRepository
import com.jess.kakaopay.repository.datasource.MainDataSourceImpl
import com.jess.kakaopay.repository.service.NaverService
import com.jess.kakaopay.util.DispatcherProviderTest
import com.jess.kakaopay.util.getOrAwaitValue
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import retrofit2.Response

/**
 * @author jess
 * @since 2020.06.16
 */
@ExperimentalCoroutinesApi
class MainDataSourceTest : BaseTest() {

    private lateinit var dataSource: MainDataSourceImpl

    @Mock
    private lateinit var service: NaverService

    override fun setUp() {
        super.setUp()
        dataSource = MainDataSourceImpl(FakeNaverRepository(), DispatcherProviderTest())
    }

    @Test
    fun `영화를 새로 검색할 경우 페이징 정보가 초기화 됐는가?`() = runBlockingTest {
        dataSource.getMovieData("테스트")
        assertTrue(dataSource.isClear.value ?: false)
        assertTrue(dataSource.isMorePage)
        assertEquals(dataSource.startPage, 1)
    }

    @Test
    fun `영화 검색`() = runBlockingTest {
        dataSource.getMovieData("테스트")
    }

    inner class FakeNaverRepository : NaverRepository {

        override val displayCount: Int = 20

        override suspend fun getMovie(query: String?, start: Int): Response<MovieData> {
            return service.getMovies(query, start)
        }

        /**
         * 데이터 생성
         *
         * @return
         */
        private fun getFakeResponse(): MovieData {
            val list = arrayListOf<MovieData.Item>()
            for (i in 0..10) {
                list.add(
                    MovieData.Item(
                        "title $i",
                        "link $i",
                        "image $i",
                        "subtitle $i",
                        "pubDate $i",
                        "director $i",
                        "actor: String $i",
                        "userRating $i"
                    )
                )
            }

            return MovieData(
                "Wed, 17 Jun 2020 13:14:41 +0900",
                10,
                1,
                10,
                list
            )
        }
    }
}