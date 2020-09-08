package com.implude.localcommunity

data class ApiResponseModelResult (
    val statusCode: Int,
    val body: List<ApiResponseModelBody>
)