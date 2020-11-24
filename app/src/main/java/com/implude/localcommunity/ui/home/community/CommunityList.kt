package com.implude.localcommunity.ui.home.community

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CommunityList(
    val name: String,
    val image: String,
    val description: String,
    val communityId: String
) : Parcelable
