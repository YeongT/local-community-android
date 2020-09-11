package com.implude.localcommunity.network

import com.implude.localcommunity.network.models.UserLoginModel
import com.implude.localcommunity.network.models.UserLoginRespond
import com.implude.localcommunity.network.models.UserRegisterModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST


interface APIConnect {
    @POST("auth/signup")
    fun userSignUp(@Body param: UserRegisterModel): Call<String>

    @POST("auth/login")
    fun userLogin(@Body param: UserLoginModel): Call<UserLoginRespond>
}
