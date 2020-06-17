package com.jess.kakaopay.repository

import com.jess.kakaopay.common.manager.request
import com.jess.kakaopay.data.MovieData
import com.jess.kakaopay.data.ResponseData
import com.jess.kakaopay.repository.service.NaverService

/**
 * @author jess
 * @since 2020.06.12
 */
interface NaverRepository {

    val displayCount: Int

    suspend fun getMovie(query: String?, start: Int): ResponseData<MovieData>?

}

class NaverRepositoryImpl constructor(
    private val service: NaverService
) : NaverRepository {

    companion object {
        const val DISPLAY_COUNT = 20
    }

    override val displayCount: Int get() = DISPLAY_COUNT

    override suspend fun getMovie(query: String?, start: Int) =
        service.getMovies(query, start).request()
}
