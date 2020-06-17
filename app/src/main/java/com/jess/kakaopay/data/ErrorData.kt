package com.jess.kakaopay.data

/**
 * 통신 에러
 */
data class ErrorData(
    val errorMessage: String?,
    val errorCode: String?
)