package com.implude.localcommunity.ui.login

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.implude.localcommunity.R
import com.implude.localcommunity.network.AuthApi
import com.implude.localcommunity.network.Network
import com.implude.localcommunity.network.models.ApiTokenResponseModel
import com.implude.localcommunity.network.models.UserLoginModel
import com.implude.localcommunity.ui.main.MainActivity
import com.implude.localcommunity.ui.signup.SignUpActivity
import com.implude.localcommunity.util.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.Response
import retrofit2.awaitResponse
import java.util.*
import java.util.regex.Pattern

class LoginActivity : AppCompatActivity() {

    private val authApi: AuthApi by lazy {
        Network.getRetrofit(this).create(AuthApi::class.java)
    }

    private val database: SharedPreferences by lazy { createSharedPreference(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        validateJwtAccessToken()
        setUpViews()
    }

    private fun validateJwtAccessToken() {
        val access: String = database.getString(KEY_ACCESS, "") ?: ""
        val refresh: String = database.getString(KEY_REFRESH, "") ?: ""

        lifecycleScope.launch {
            if (access.isNotBlank()) {
                val refreshExpireTime = JSONObject(decoded(refresh)).getLong("exp")
                if (Date(System.currentTimeMillis()) >= Date(refreshExpireTime * 1000)) {
                    val id = database.getString(USER_ID, "id") ?: "id"
                    val pw = database.getString(USER_PW, "pw") ?: "id"

                    try {
                        autoLogin(id, pw, refresh, authApi)

                    } catch (err: Exception) {
                        showToast("자동 로그인에 실패했습니다.\n 다시 로그인 해 주세요.")
                        database.edit().remove(KEY_ACCESS).apply()
                        database.edit().remove(KEY_REFRESH).apply()
                    }
                }
                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                finish()
            }
        }
    }

    private fun setUpViews() {
        Login_TextView_FindId.setOnClickListener {
            // TODO : Launch FindIdPwActivity
        }

        Login_TextView_Register.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }

        Login_Button_LoginButton.setOnClickListener {
            checkValidation()
        }
    }

    private fun checkValidation() {
        val id = Login_EditText_Id.text.toString()
        val pw = Login_EditText_Pwd.text.toString()

        if (!Pattern.matches(EMAIL_REGEX, id)) {
            showToast(R.string.login_alert_id)
        } else if (!Pattern.matches(PASSWORD_REGEX, pw)) {

            showToast(R.string.login_alert_pw)
        } else {
            lifecycleScope.launch { login(id, pw) }
        }
    }

    private suspend fun login(id: String, pw: String) {
        val refresh = database.getString(KEY_REFRESH, "")
        val loginModel = UserLoginModel(id, pw, refresh)

        try {
            val response = authApi.userLogin(loginModel).awaitResponse()
            when (response.code()) {
                200 -> loginSuccessAction(response, id, pw)
                409 -> showToast(R.string.login_error_authfail)
                412 -> showToast(R.string.login_error_invalid_format)
                else -> {
                    showToast(R.string.login_error_serverfail)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun loginSuccessAction(
        response: Response<ApiTokenResponseModel>,
        id: String,
        pw: String
    ) {
        val access = response.body()?.output?.token?.access ?: throw Exception()
        val refresh = response.body()?.output?.token?.refresh ?: throw Exception()

        showToast(R.string.login_succeed_logintask)
        database.edit().putString(USER_ID, id).apply()
        database.edit().putString(USER_PW, pw).apply()
        database.edit().putString(KEY_ACCESS, access).apply()
        database.edit().putString(KEY_REFRESH, refresh).apply()
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

}
