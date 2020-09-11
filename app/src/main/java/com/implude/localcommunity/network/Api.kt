package com.implude.localcommunity.network

import com.implude.localcommunity.network.models.ApiResponse
import com.implude.localcommunity.network.models.UserRegisterModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface Api {
    @POST("auth/signup")
    fun signUp(@Body param: UserRegisterModel): Call<ApiResponse>
}
