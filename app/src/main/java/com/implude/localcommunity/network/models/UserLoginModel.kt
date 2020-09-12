package com.implude.localcommunity.network.models

import com.google.gson.annotations.SerializedName

data class UserLoginModel(
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String,
)
