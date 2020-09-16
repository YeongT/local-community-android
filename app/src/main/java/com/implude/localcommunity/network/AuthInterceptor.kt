package com.implude.localcommunity.network

import com.implude.localcommunity.BuildConfig.LOCAL_COMMUNITY_API_KEY
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class AuthInterceptor(private val jwtToken: String) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        var authHeader = "Basic $LOCAL_COMMUNITY_API_KEY"
        if (chain.request().url().toString().contains("/post")) authHeader = jwtToken

        val request = builder
            .addHeader("Authorization", authHeader)
            .addHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8")
            .build()
        return chain.proceed(request)
    }
}
