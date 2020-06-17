package com.jess.kakaopay.common.manager

import com.google.gson.Gson
import com.jess.kakaopay.common.manager.RequestManager.parseErrorBody
import com.jess.kakaopay.common.util.tryCatch
import com.jess.kakaopay.data.ErrorData
import com.jess.kakaopay.data.ResponseData
import okhttp3.ResponseBody
import retrofit2.Response

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
            errorMessage = "Something Went Wrong",
            errorCode = "Error"
        )

        errorBody?.let {
            tryCatch {
                val adapter = Gson().getAdapter(ErrorData::class.java)
                return adapter.fromJson(it.string())
            }
        }
        return error
    }
}

/**
 * 네트워크 통신
 */
fun <T> Response<T>?.request(): ResponseData<T>? {
    return this?.let { response ->
        if (response.isSuccessful) {
            ResponseData.success(this.body())
        } else {
            ResponseData.error(null, parseErrorBody(response.errorBody()))
        }
    } ?: run {
        ResponseData.error(null, null)
    }
}