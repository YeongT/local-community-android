package com.implude.localcommunity.util

import android.content.Context
import android.util.Base64
import android.util.Log
import android.widget.Toast
import androidx.annotation.StringRes
import java.io.UnsupportedEncodingException

const val EMAIL_REGEX =
    "^[0-9a-zA-Z\\_\\-\\.]+@[a-zA-Z\\_\\-\\.]+?\\.[a-zA-Z]{2,3}\$"
const val PASSWORD_REGEX = "^.*(?=^.{8,15}\$)(?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@#\$%^&+=]).*\$"
const val PHONE_REGEX = "^(?:(010-?\\d{4})|(01[1|6|7|8|9]-?\\d{3,4}))-?\\d{4}\$"

fun Context.showToast(@StringRes stringId: Int) =
    Toast.makeText(this, stringId, Toast.LENGTH_SHORT).show()

fun Context.showToast(text: String) =
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()

fun decoded(JWTEncoded: String) {
    try {
        val split = JWTEncoded.split("\\.".toRegex()).toTypedArray()
        Log.d("JWT_DECODED", "output: " + getJson(split[2]))
    } catch (e: UnsupportedEncodingException) {
        e.printStackTrace()
    }
}

private fun getJson(strEncoded: String): String {
    val decodedBytes: ByteArray = Base64.decode(strEncoded, Base64.URL_SAFE)
    return String(decodedBytes)
}
