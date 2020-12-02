package com.implude.localcommunity.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val BASE_URL = "https://api.hakbong.me/"

object Network {
    var jwtToken: String = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhY2NvdW50Ijp7InN0YXR1cyI6InZlcmlmaWVkIiwiZW1haWwiOiJieXNzdWRvNjJAZGltaWdvaC5nb2UuZ28ua3IiLCJqb2luZWQiOiIyMDIwLTA5LTI0IDAwOjI0OjIzIn0sInNlcnZpY2UiOnsiY29tbXVuaXR5IjpbeyJjb21tdW5pdHkiOiI1ZjhlY2E0ZmZhNjdiZjdlMTFmMTA0ZmEiLCJqb2luZWQiOiIyMDIwLTEwLTIwIDIwOjMwOjIzIiwicHJpdmlsZWdlcyI6IjVmOGVjYTRmZmE2N2JmN2UxMWYxMDRmYiJ9XX0sInByb2ZpbGUiOnsibmFtZSI6IuycpOyYgeywvSIsInBob25lIjoiOTY3ZWNlZDM5NmNkYzg3M2EyMzFmMzdmZWExOTE3MTQ6MjA1MTcwZTk2YzNkMzBiNTMzZmI0ZmQzM2Y1MjRmOTQiLCJnZW5kZXIiOjEsImFyZWFTdHJpbmciOiLsoITrnbzrgqjrj4Qg66qp7Y-s7IucIn0sImlhdCI6MTYwNjkxNDM0OCwiZXhwIjoxNjA3MzQ2MzQ4fQ.h_9x7xmGrI5YsMWzWSvQn9WHxrnTHTiKOrRrn69kXCQ"

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


