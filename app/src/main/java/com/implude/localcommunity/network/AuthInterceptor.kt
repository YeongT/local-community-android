package com.implude.localcommunity.network

import android.util.Base64
import android.util.Log
import com.implude.localcommunity.BuildConfig.LOCAL_COMMUNITY_API_KEY
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import java.io.UnsupportedEncodingException

class AuthInterceptor(private val jwtToken: String) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        var authHeader = "Basic $LOCAL_COMMUNITY_API_KEY"
        val request = builder
            .addHeader("Authorization", authHeader)
            .addHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8")

        if (chain.request().url().toString().contains("/post")) authHeader = jwtToken

        return chain.proceed(request.build())
    }
}

fun String.decode() {
    try {
        val split = this.split("\\.".toRegex()).toTypedArray()
        Log.d("JWT_DECODED", "output: " + getJson(split[2]))
    } catch (e: UnsupportedEncodingException) {
        e.printStackTrace()
    }
}

fun getJson(strEncoded: String): String {
    val decodedBytes: ByteArray = Base64.decode(strEncoded, Base64.URL_SAFE)
    return String(decodedBytes)
}