package com.implude.localcommunity.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val BASE_URL = "https://api.hakbong.me/"

object Network {
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(httpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

var jwtToken: String = ""

fun httpClient(): OkHttpClient = OkHttpClient.Builder().run {
    addInterceptor(AuthInterceptor(jwtToken))
    readTimeout(15, TimeUnit.SECONDS)
    writeTimeout(15, TimeUnit.SECONDS)
    build()
}
