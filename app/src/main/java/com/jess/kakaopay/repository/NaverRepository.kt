package com.jess.kakaopay.repository

import com.jess.kakaopay.data.MovieData
import com.jess.kakaopay.repository.service.NaverService


/**
 * @author jess
 * @since 2020.06.12
 */
interface NaverRepository {
    suspend fun getMovie(query: String): List<MovieData>?
}

class NaverRepositoryImpl constructor(
    private val service: NaverService
) : NaverRepository {

    override suspend fun getMovie(query: String): List<MovieData>? = service.getMovies(query).body()

}
