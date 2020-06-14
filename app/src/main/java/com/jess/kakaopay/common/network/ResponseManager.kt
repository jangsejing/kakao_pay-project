package com.jess.kakaopay.common.network

import com.google.gson.Gson
import com.jess.kakaopay.common.network.RequestManager.parseErrorBody
import com.jess.kakaopay.data.ErrorData
import okhttp3.ResponseBody
import retrofit2.Response
import timber.log.Timber

/**
 * @author jess
 * @since 2020.06.14
 */
object RequestManager {

    /**
     * 에러 바디 처리
     */
    fun parseErrorBody(errorBody: ResponseBody?): ErrorData {

        // 에러파싱 실패
        val error = ErrorData(
            status = 400,
            errorMessage = "네트워크 통신을 실패했어요",
            errorCode = "error"
        )

        errorBody?.let {
            try {
                val adapter = Gson().getAdapter(ErrorData::class.java)
                return adapter.fromJson(it.string())
            } catch (e: Exception) {
                Timber.e(e)
            }
        }

        return error
    }
}

/**
 * 네트워크 통
 */
suspend fun <T> Response<T>?.request(
    errorListener: ((ErrorData?) -> Unit)? = null
): T? {

    this?.let { response ->
        if (!response.isSuccessful) {
            errorListener?.invoke(parseErrorBody(response.errorBody()))
            return null
        }
    }

    return this?.body()
}
