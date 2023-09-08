package br.com.maschdy.moviez.api.util

import android.util.Log
import br.com.maschdy.moviez.model.ApiError
import br.com.maschdy.moviez.model.ApiException
import br.com.maschdy.moviez.model.ApiResult
import br.com.maschdy.moviez.model.ApiSuccess
import retrofit2.HttpException
import retrofit2.Response

private const val TAG = "HandleApi"

suspend fun <T : Any> handleApi(
    execute: suspend () -> Response<T>
): ApiResult<T> {
    return try {
        val response = execute()
        val body = response.body()
        if (response.isSuccessful && body != null) {
            ApiSuccess(body)
        } else {
            ApiError(code = response.code(), message = response.message())
        }
    } catch (e: HttpException) {
        Log.e(TAG, Log.getStackTraceString(e))
        ApiError(code = e.code(), message = e.message())
    } catch (e: Throwable) {
        Log.e(TAG, Log.getStackTraceString(e))
        ApiException(e)
    }
}
