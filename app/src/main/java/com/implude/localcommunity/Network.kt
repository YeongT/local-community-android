package com.implude.localcommunity

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object Network {
    val retrofit = retrofitClient(BASE_URL, httpClient())
}

fun httpClient(interceptor: Interceptor = AuthInterceptor()): OkHttpClient {
    val clientBuilder = OkHttpClient.Builder()

    clientBuilder.addInterceptor(interceptor)
    clientBuilder.readTimeout(15, TimeUnit.SECONDS)
    clientBuilder.writeTimeout(15, TimeUnit.SECONDS)
    return clientBuilder.build()
}

fun retrofitClient(baseUrl: String, client: OkHttpClient): Retrofit =
    Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
