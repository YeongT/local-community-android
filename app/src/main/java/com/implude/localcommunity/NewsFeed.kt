package com.implude.localcommunity

import android.graphics.Bitmap
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NewsFeed(
    val target: String,
    val title: String,
    val text: String,
    val tags: String,
    val picture: Bitmap?,
    val link: String
) : Parcelable
