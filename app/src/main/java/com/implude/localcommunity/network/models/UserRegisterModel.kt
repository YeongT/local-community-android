package com.implude.localcommunity.network.models

import com.google.gson.annotations.SerializedName

data class UserRegisterModel(
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("gender")
    val gender: Int,
    @SerializedName("areaString")
    val areaString: String
)
