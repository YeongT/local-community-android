package com.implude.localcommunity

data class NewsFeedModel(
    val userjwt: String,
    val target: String,
    val title: String,
    val text: String,
    val tags: String,
    val picture: String,
    val link: String
)