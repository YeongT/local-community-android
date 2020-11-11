package com.implude.localcommunity.network.models

import com.google.gson.annotations.SerializedName
import java.util.*

data class NewsFeedResponseModel(
    @SerializedName("statusCode")
    val statusCode: Int,
    @SerializedName("bodymsg")
    val bodymsg: String,
    @SerializedName("output")
    val output: NewsFeedResponseModelOutput?,
    @SerializedName("error")
    val error: String?
)

data class NewsFeedResponseModelOutput(
    val output: Objects
)