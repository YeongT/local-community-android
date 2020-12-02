package com.implude.localcommunity.network.models

import com.google.gson.annotations.SerializedName

data class ApiTokenResponseModel(
    @SerializedName("statusCode")
    val statusCode: Int,
    @SerializedName("bodymsg")
    val bodymsg: String,
    @SerializedName("output")
    val output: ApiResponseModelToken?,
    @SerializedName("error")
    val error: String?
)

data class ApiResponseModelToken(
    @SerializedName("token")
    val token: ApiResponseModelOutput?,
)

data class ApiResponseModelOutput(
    @SerializedName("access")
    val access: String,
    @SerializedName("refresh")
    val refresh: String
)
