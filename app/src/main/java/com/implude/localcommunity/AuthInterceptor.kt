package com.implude.localcommunity

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class AuthInterceptor : Interceptor {
    private val userCredential = "account-basic:"
    private val authHeader = "Basic ${userCredential}"

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
