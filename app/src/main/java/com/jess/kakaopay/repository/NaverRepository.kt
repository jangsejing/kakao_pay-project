package com.jess.kakaopay.repository

import com.jess.kakaopay.data.MovieData
import com.jess.kakaopay.repository.service.NaverService
import retrofit2.Response

/**
 * @author jess
 * @since 2020.06.12
 */
interface NaverRepository {
    suspend fun getMovie(query: String?, start: Int): Response<MovieData?>
}

class NaverRepositoryImpl constructor(
    private val service: NaverService
) : NaverRepository {
    override suspend fun getMovie(query: String?, start: Int): Response<MovieData?> =
        service.getMovies(query, start)
}
