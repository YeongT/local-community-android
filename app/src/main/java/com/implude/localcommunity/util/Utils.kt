package com.implude.localcommunity.util

import android.content.Context
import android.content.SharedPreferences
import android.util.Base64
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.implude.localcommunity.network.AuthApi
import com.implude.localcommunity.network.models.UserLoginModel
import retrofit2.awaitResponse
import java.io.UnsupportedEncodingException

const val EMAIL_REGEX =
    "^[0-9a-zA-Z\\_\\-\\.]+@[a-zA-Z\\_\\-\\.]+?\\.[a-zA-Z]{2,3}\$"
const val PASSWORD_REGEX = "^.*(?=^.{8,15}\$)(?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@#\$%^&+=]).*\$"
const val PHONE_REGEX = "^(?:(010-?\\d{4})|(01[1|6|7|8|9]-?\\d{3,4}))-?\\d{4}\$"

const val USER_ID = "userId"
const val USER_PW = "userPw"

const val KEY_ACCESS = "accessToken"
const val KEY_REFRESH = "refreshToken"

fun Context.showToast(@StringRes stringId: Int) =
    Toast.makeText(this, stringId, Toast.LENGTH_SHORT).show()

fun Context.showToast(text: String) =
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()

@Throws(Exception::class)
fun decoded(JWTEncoded: String): String {
    return try {
        val split: List<String> = JWTEncoded.split("\\.".toRegex())
        val decodedBytes = Base64.decode(split[1], Base64.URL_SAFE)
        String(decodedBytes, Charsets.UTF_8)
    } catch (e: UnsupportedEncodingException) {
        e.printStackTrace()
        e.message!!
    }
}

private val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)

fun createSharedPreference(context: Context): SharedPreferences =
    EncryptedSharedPreferences.create(
        "security",
        masterKeyAlias,
        context,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

suspend fun autoLogin(userId: String, userPw: String, authApi: AuthApi): String {
    val loginModel = UserLoginModel(userId, userPw)
    val response = authApi.userLogin(loginModel).awaitResponse()
    if (response.code() == 200 && response.body()?.output?.token?.access !== null) return response.body()!!.output!!.token.access
    else throw Error("Error : server responded correctly but accessToken body is invalid")
}