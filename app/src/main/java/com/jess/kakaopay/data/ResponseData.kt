package com.jess.kakaopay.data

/**
 * 네트워크 통신
 *
 * @author jess
 * @since 2020.06.19
 */

/**
 * Response Status
 */
public enum class Status {
    SUCCESS,
    ERROR
}

data class ResponseData<out T>(
    val status: Status,
    val data: T? = null,
    val error: ErrorData? = null
) {
    companion object {
        fun <T> success(data: T?): ResponseData<T> {
            return ResponseData(
                Status.SUCCESS,
                data = data
            )
        }

        fun <T> error(data: T? = null, error: ErrorData?): ResponseData<T> {
            return ResponseData(
                Status.ERROR,
                data = data,
                error = error
            )
        }
    }
}
