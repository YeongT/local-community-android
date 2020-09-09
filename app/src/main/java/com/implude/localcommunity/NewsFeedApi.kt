package com.implude.localcommunity

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.PUT

interface NewsFeedApi {
    @PUT("post/new-article")
    fun newArticle(@Body param: NewsFeedModel): Call<ApiResponseModel>
}