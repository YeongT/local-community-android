package com.implude.localcommunity

data class ApiResponseModel(
    val statusCode: Int = 0,                            // 서버 상태 코드
    val result: List<ApiResponseModelResult>,           // 결과 리스트
    val output: Object,                                 // 출력
    val error: String                                   // 에러 문자열
)