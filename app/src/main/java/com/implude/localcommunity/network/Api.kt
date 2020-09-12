package com.implude.localcommunity.network

import com.implude.localcommunity.network.models.ApiTokenResponseModel
import com.implude.localcommunity.network.models.NewsFeedModel
import com.implude.localcommunity.network.models.UserLoginModel
import com.implude.localcommunity.network.models.UserRegisterModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.PUT

interface AuthApi {
    @POST("auth/signup")
    fun signUp(@Body param: UserRegisterModel): Call<ApiTokenResponseModel>

    @POST("auth/login")
    fun userLogin(@Body param: UserLoginModel): Call<ApiTokenResponseModel>
}

interface NewsFeedApi {
    @PUT("post/new-article")
    fun newArticle(@Body param: NewsFeedModel): Call<ApiTokenResponseModel>
}
