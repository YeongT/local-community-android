package com.implude.localcommunity

import com.implude.localcommunity.models.UserRegisterModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface Api {
    @POST("auth/signup")
    fun signUp(@Body param: UserRegisterModel): Call<String>
}