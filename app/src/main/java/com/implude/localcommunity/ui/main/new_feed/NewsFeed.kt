package com.implude.localcommunity.ui.main.new_feed

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/* --- 안드로이드용 게시물 데이터 모델 --- */
@Parcelize
data class NewsFeed(
    val target: String,             // 커뮤니티 식별키
    val title: String,              // 게시물 제목
    val text: String,               // 게시물 내용
    val tags: String,               // 게시물 태그
    val picture: String,            // 게시물 사진
    val link: String                // 게시물 첨부 링크(맛집 등)
) : Parcelable
