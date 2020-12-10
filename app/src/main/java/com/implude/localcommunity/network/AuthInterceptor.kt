package com.implude.localcommunity.network

import android.content.Context
import com.implude.localcommunity.BuildConfig.LOCAL_COMMUNITY_API_KEY
import com.implude.localcommunity.util.*
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException
import java.util.*

class AuthInterceptor(context: Context) : Interceptor {

    private val authApi: AuthApi by lazy {
        Network.getRetrofit(context).create(AuthApi::class.java)
    }

    private val database = createSharedPreference(context)

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        var authHeader = "Basic $LOCAL_COMMUNITY_API_KEY"
        val requestBuilder =
            builder.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8")

        val useJwtToken: Boolean =
            chain.request().url().toString().contains("/post") || chain.request().url().toString()
                .contains("/group")

        return runBlocking {
            val access: String = database.getString(KEY_ACCESS, "") ?: ""
            val refresh: String = database.getString(KEY_REFRESH, "") ?: ""
            if (useJwtToken) {
                authHeader = access
                val accessExpireTime = JSONObject(decoded(access)).getLong("exp")
                val refreshExpireTime = JSONObject(decoded(refresh)).getLong("exp")
                if (Date(System.currentTimeMillis()) >= Date(refreshExpireTime * 1000)) {
                    val id = database.getString(USER_ID, "") ?: ""
                    val pw = database.getString(USER_PW, "") ?: ""
                    authHeader = try {
                        autoLogin(id, pw, refresh, authApi)
                    } catch (err: Exception) {
                        access
                    }
                } else if (Date(System.currentTimeMillis()) >= Date(accessExpireTime * 1000))
                    requestBuilder.addHeader("refreshToken", refresh)
            }
            chain.proceed(requestBuilder.addHeader("Authorization", authHeader).build())
        }
    }
}
