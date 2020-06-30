package com.jess.kakaopay.domain.repository.service

import com.jess.kakaopay.data.MovieData
import com.jess.kakaopay.domain.repository.NaverRepositoryImpl.Companion.DISPLAY_COUNT
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author jess
 * @since 2020.06.12
 */
interface NaverService {

    /**
     * 영화 조회
     */
    @GET("/v1/search/movie.json")
    suspend fun getMovies(
        @Query("query") query: String?,
        @Query("start") start: Int = 1,
        @Query("display") display: Int = DISPLAY_COUNT
    ): Response<MovieData>

}