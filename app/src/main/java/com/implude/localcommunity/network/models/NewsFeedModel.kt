package com.implude.localcommunity.network.models

import com.google.gson.annotations.SerializedName

/* --- 서버용 게시물 데이터 모델 --- */
data class NewsFeedModel(
    @SerializedName("target")
    val target: String,                 // 커뮤니티 식별키
    @SerializedName("title")
    val title: String,                  // 게시물 제목
    @SerializedName("text")
    val content: String,                // 게시물 내용
    @SerializedName("tags")
    val tags: String,                   // 게시물 태그
    @SerializedName("picture")
    val img: String,                    // 게시물 사진
    @SerializedName("link")
    val link: String                    // 게시물 첨부 링크(맛집 등)
)
