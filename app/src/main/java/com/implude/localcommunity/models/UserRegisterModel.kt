package com.implude.localcommunity.models

data class UserRegisterModel(
    val email: String,
    val password: String,
    val name: String,
    val phone: String,
    val gender: String,
    val areaString: String
)