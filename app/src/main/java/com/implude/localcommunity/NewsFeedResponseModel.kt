package com.implude.localcommunity

import android.graphics.Bitmap
import com.google.gson.annotations.SerializedName

class NewsFeedResponseModel {
    @SerializedName("userjwt")
    val userJwt: String = "Undefined"

    @SerializedName("target")
    val target: String = "Undefined"

    @SerializedName("title")
    val title: String = "Undefined"

    @SerializedName("text")
    val text: String = "Undefined"

    @SerializedName("picture")
    val Picture: String = "Undefined"
}