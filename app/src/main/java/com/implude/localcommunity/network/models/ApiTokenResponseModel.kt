package com.implude.localcommunity.network.models

import com.google.gson.annotations.SerializedName

data class ApiTokenResponseModel(
    @SerializedName("statusCode")
    val statusCode: Int,
    @SerializedName("bodymsg")
    val bodymsg: String,
    @SerializedName("output")
    val output: ApiResponseModelOutput?,
    @SerializedName("error")
    val error: String?
)

data class ApiResponseModelOutput(
    @SerializedName("token")
    val token: String
)
