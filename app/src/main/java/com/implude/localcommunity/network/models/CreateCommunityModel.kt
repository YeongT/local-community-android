package com.implude.localcommunity.network.models

import com.google.gson.annotations.SerializedName

data class CreateCommunityModel(
    @SerializedName("groupname")
    val groupname: String,                 // 커뮤니티 이름
    @SerializedName("groupimage")
    val groupimage: String,                  // 커뮤니티 사진
    @SerializedName("description")
    val description: String,                // 커뮤니티 소개
    @SerializedName("areaString")
    val areaString: String,                   // 커뮤니티 위치
    @SerializedName("tags")
    val tags: String                    // 커뮤니티 태그
)