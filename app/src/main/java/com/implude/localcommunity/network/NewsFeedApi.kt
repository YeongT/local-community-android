package com.implude.localcommunity.network

import com.implude.localcommunity.network.models.ApiResponseModel
import com.implude.localcommunity.network.models.NewsFeedModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.PUT

interface NewsFeedApi {
    @PUT("post/new-article")
    fun newArticle(@Body param: NewsFeedModel): Call<ApiResponseModel>
}
