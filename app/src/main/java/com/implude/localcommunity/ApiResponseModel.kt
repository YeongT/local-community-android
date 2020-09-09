package com.implude.localcommunity

data class ApiResponseModel(
    val statusCode: Int = 0,
    val error: String,
    val output: ApiResponseModelOutput,
    val bodymsg: String
)

data class ApiResponseModelOutput (
    val token: String?,
)
