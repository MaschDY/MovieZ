package br.com.maschdy.moviez.data.network

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class NetworkInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request().newBuilder()
            .addContentType()
            .addAuthorization()
            .build()

        return chain.proceed(request)
    }

    private fun Request.Builder.addContentType(): Request.Builder {
        addHeader("accept", "application/json")
        return this
    }

    private fun Request.Builder.addAuthorization(): Request.Builder {
        addHeader(
            "Authorization",
            "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI0MmJhM2UzN2NiMTAyNThjNmZkMzNlY2MzOTQxN2U0NSIsInN1YiI6IjY0NDlkNzU4YjExMzFmMDUxMjFhNDQ1MyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.KkIY2jvWUkL_Mg-sEACEv-QRreYX3BL-LJTKWzd-QlA"
        )
        return this
    }
}
