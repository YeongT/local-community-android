package com.implude.localcommunity

import com.implude.localcommunity.util.toBase64
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class AuthInterceptor : Interceptor {
    private val userCredential = "account-basic:${BuildConfig.LOCAL_COMMUNITY_API_KEY}"
    private val authHeader = "Basic ${userCredential.toBase64()}"

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        val request = builder
            .addHeader("Authorization", authHeader)
            .addHeader("accept", "application/json; charset=utf-8")
            .addHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8")
            .build()

        return chain.proceed(request)
    }
}
