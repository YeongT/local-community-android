package com.implude.localcommunity.network.models

import com.google.gson.annotations.SerializedName

data class CreateCommunityResponseModel(
    @SerializedName("statusCode")
    val statusCode: Int,
    @SerializedName("bodymsg")
    val bodymsg: String,
    @SerializedName("output")
    val output: CreateCommunityResponseModelOutput?,
    @SerializedName("error")
    val error: String?
)

data class CreateCommunityResponseModelOutput(
    @SerializedName("community")
    val community: String
)