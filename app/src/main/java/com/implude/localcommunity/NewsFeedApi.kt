package com.implude.localcommunity

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface NewsFeedApi {
    @POST("post/new-article")
    fun newArticle(@Body param: NewsFeedModel): Call<ApiResponseModel>
}