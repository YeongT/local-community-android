package com.implude.localcommunity.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val BASE_URL = "https://api.hakbong.me/"

object Network {
    var jwtToken: String = ""

    val retrofit: Retrofit
        get() = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    private fun httpClient(): OkHttpClient = OkHttpClient.Builder().run {
        addInterceptor(AuthInterceptor(jwtToken))
        readTimeout(15, TimeUnit.SECONDS)
        writeTimeout(15, TimeUnit.SECONDS)
        build()
    }
}


