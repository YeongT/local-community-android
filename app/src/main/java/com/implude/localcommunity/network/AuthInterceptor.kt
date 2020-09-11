package com.implude.localcommunity.network

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class AuthInterceptor : Interceptor {
    private val jwtHeader = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJjb21tdW5pdHkiOltdLCJlbmFibGUiOnRydWUsIl9pZCI6IjVmNTRjNWUzMzgxYTIwYjcwNzM1NTIyMyIsImVtYWlsIjoiYnlzc3VkbzYyQGRpbWlnb2guZ29lLmdvLmtyIiwibmFtZSI6IuycpOyYgeywve2FjOyKpO2KuCIsImdlbmRlciI6MSwicGhvbmUiOiJhMmJlMzU4ODVjZmVkNTUyOTQwNmM5ODQyYmFkMDBiOTozNmU0OTE4ZGZhNDIxZDZhMjExNjBiNzJjYTNjZmFkMSIsImFyZWFTdHJpbmciOiLsoITrnbzrgqjrj4Qg66qp7Y-s7IucIiwibGFzdGxvZ2luIjoiMjAyMC0wOS0wNyAxMDo1ODo0MiIsImlhdCI6MTU5OTYzODg0OSwiZXhwIjoxNTk5NjQ2MDQ5fQ.jpM376Gc1A5yMYtpZ6wPgskmQPl7lnRC-AFtPSrWIkE"

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        val request = builder
            .addHeader("Authorization", jwtHeader)
            .addHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8")
            .build()

        return chain.proceed(request)
    }
}
