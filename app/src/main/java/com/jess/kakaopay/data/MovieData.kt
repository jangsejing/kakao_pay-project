package com.jess.kakaopay.data

import java.io.Serializable

/**
 * 영화 Response
 *
 * @author jess
 * @since 2020.03.122020.06.19
 */
data class MovieData(
    val lastBuildDate: String?,
    val total: Int,
    val start: Int,
    val display: Int,
    val items: List<Item>?
) {

    /**
     * 시작 페이지
     *
     * @param displayCount 불러올 페이지 수
     */
    fun getStartNumber(displayCount: Int) = start + displayCount

    /**
     * 다음 페이지 여부
     */
    fun isMorePage() = (start + display) <= total

    data class Item(
        val title: String?,
        val link: String?,
        val image: String?,
        val subtitle: String?,
        val pubDate: String?,
        val director: String?,
        val actor: String?,
        val userRating: String?
    ) : Serializable
}