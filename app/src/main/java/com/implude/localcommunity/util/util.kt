package com.implude.localcommunity.util

import android.util.Base64

fun String.toBase64(): String = Base64.encodeToString(this.toByteArray(), Base64.NO_WRAP)