package br.com.maschdy.moviez.data.network

import android.util.Log
import br.com.maschdy.moviez.data.network.NetworkFactory.provideMoshi
import br.com.maschdy.moviez.data.network.model.ErrorBody
import br.com.maschdy.moviez.domain.model.Error
import br.com.maschdy.moviez.domain.model.Exception
import br.com.maschdy.moviez.domain.model.Result
import br.com.maschdy.moviez.domain.model.Success
import retrofit2.HttpException
import retrofit2.Response

object NetworkHandler {
    private const val TAG = "HandleNetwork"

    suspend fun <T : Any> handle(
        execute: suspend () -> Response<T>
    ): Result<T> {
        return try {
            val response = execute()
            val body = response.body()
            if (response.isSuccessful && body != null) {
                Success(body)
            } else {
                val jsonConverter = provideMoshi().adapter(ErrorBody::class.java)
                val errorBody = jsonConverter.fromJson(response.errorBody()?.source())
                Error(
                    code = errorBody?.statusCode ?: 0,
                    message = errorBody?.statusMessage
                )
            }
        } catch (e: HttpException) {
            Log.e(TAG, e.message())
            Error(code = e.code(), message = e.stackTraceToString())
        } catch (e: Throwable) {
            Log.e(TAG, e.stackTraceToString())
            Exception(e)
        }
    }
}
