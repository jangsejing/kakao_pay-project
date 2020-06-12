package com.jess.kakaopay.repository.service

import com.jess.kakaopay.data.MovieData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author jess
 * @since 2020.06.19
 */
interface NaverService {

    /**
     * 영화 조회
     */
    @GET("/v1/search/movie.json")
    suspend fun getMovies(
        @Query("query") query: String
    ): Response<MovieData?>
}