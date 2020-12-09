package com.implude.localcommunity.network

import android.content.Context
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val BASE_URL = "https://api.hakbong.me/"

object Network {

    private lateinit var retrofit: Retrofit

    fun getRetrofit(context: Context): Retrofit {
        if (!::retrofit.isInitialized) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(httpClient(context))
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit
    }

    private fun httpClient(context: Context): OkHttpClient = OkHttpClient.Builder().run {
        addInterceptor(AuthInterceptor(context))
        readTimeout(15, TimeUnit.SECONDS)
        writeTimeout(15, TimeUnit.SECONDS)
        build()
    }
}


