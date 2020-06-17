package com.jess.kakaopay.common.manager

import com.google.gson.Gson
import com.jess.kakaopay.common.interfaces.OnResponseListener
import com.jess.kakaopay.common.manager.RequestManager.parseErrorBody
import com.jess.kakaopay.common.util.tryCatch
import com.jess.kakaopay.data.ErrorData
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
        val error = getDefaultErrorBody()
        errorBody?.let {
            tryCatch {
                val adapter = Gson().getAdapter(ErrorData::class.java)
                return adapter.fromJson(it.string())
            }
        }
        return error
    }

    fun getDefaultErrorBody() = ErrorData(
        errorMessage = "Something Went Wrong",
        errorCode = "Error"
    )
}

/**
 * 네트워크 통신
 */
fun <T> Response<T>?.request(
    listener: OnResponseListener<T>? = null
) {
    listener?.let {
        this?.let { response ->
            if (response.isSuccessful) {
                listener.onSuccess(response.body())
            } else {
                listener.onError(parseErrorBody(response.errorBody()))
            }
        } ?: listener.onError(RequestManager.getDefaultErrorBody())
    }
}