package com.jess.kakaopay.main

import com.jess.kakaopay.base.BaseTest
import com.jess.kakaopay.data.MovieData
import com.jess.kakaopay.repository.NaverRepository
import com.jess.kakaopay.repository.datasource.MainDataSourceImpl
import com.jess.kakaopay.repository.service.NaverService
import com.jess.kakaopay.util.DispatcherProviderTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.mockito.Mock
import retrofit2.Response
import javax.inject.Inject

/**
 * @author jess
 * @since 2020.06.16
 */
@ExperimentalCoroutinesApi
class MainDataSourceTest : BaseTest() {

    private lateinit var dataSource: MainDataSourceImpl

    @Mock
    private lateinit var repository: FakeNaverRepository

    @Inject
    private lateinit var service: NaverService

    override fun setUp() {
        super.setUp()
        repository = FakeNaverRepository()
        dataSource = MainDataSourceImpl(repository, DispatcherProviderTest())
    }

    /**
     * 데이터 생성
     *
     * @return
     */
    private fun createFakeResponse(): Response<MovieData>? {
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

        return Response.success(
            MovieData(
                "Wed, 17 Jun 2020 13:14:41 +0900",
                10,
                1,
                10,
                list
            )
        )
    }

    @Test
    fun `query 입력 후 페이징 정보 초기화 --- 초기화 여부`() = runBlockingTest {
        dataSource.getMovieData("테스트")
        assertTrue(dataSource.isClear.value ?: false)
        assertTrue(dataSource.isMorePage)
        assertEquals(dataSource.startPage, 1)
    }

    @Test
    fun `queryLiveData 세팅 --- 세팅 여부`() {
        val query = "테스트"
        dataSource.queryData.value = query
        assertEquals(dataSource.queryData.value, query)
    }

    @Test
    fun `다음 페이지 요청 후 페이지 정보 유지 --- 기존 정보 유지`() = runBlockingTest {
        dataSource.run {
            startPage = 2
            isMorePage = true
        }

        dataSource.getNextPage()

        assertEquals(dataSource.startPage, 2)
        assertEquals(dataSource.isMorePage, true)
    }

    inner class FakeNaverRepository : NaverRepository {

        override val displayCount: Int = 20

        override suspend fun getMovie(query: String?, start: Int): Response<MovieData> {
            return service.getMovies(query, start)
        }
    }
}