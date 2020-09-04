package com.implude.localcommunity.models

data class UserLoginModel(
    val email: String,
    val password: String
)

data class UserLoginRespond(
    val result: String,
    val token: String
)