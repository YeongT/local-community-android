package com.implude.localcommunity

import android.graphics.Bitmap
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NewsFeed(
    val userLink: String,
    val content: String,
    val img: Bitmap?,
    val time: String
) : Parcelable
