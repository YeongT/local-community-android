package com.implude.localcommunity

import com.implude.localcommunity.models.UserLoginModel
import com.implude.localcommunity.models.UserLoginRespond
import com.implude.localcommunity.models.UserRegisterModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST


interface APIConnect {
    @POST("auth/signup")
    fun userSignUp(@Body param: UserRegisterModel): Call<String>

    @POST("auth/login")
    fun userLogin(@Body param: UserLoginModel): Call<UserLoginRespond>
}