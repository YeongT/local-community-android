package com.implude.localcommunity.ui.login

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.lifecycle.lifecycleScope
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.implude.localcommunity.R
import com.implude.localcommunity.network.AuthApi
import com.implude.localcommunity.network.Network
import com.implude.localcommunity.network.models.ApiTokenResponseModel
import com.implude.localcommunity.network.models.UserLoginModel
import com.implude.localcommunity.ui.signup.SignUpActivity
import com.implude.localcommunity.util.EMAIL_REGEX
import com.implude.localcommunity.util.PASSWORD_REGEX
import com.implude.localcommunity.util.decoded
import com.implude.localcommunity.util.showToast
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.awaitResponse
import java.util.regex.Pattern

private const val KEY_TOKEN = "token"
private const val KEY_USER_JWT = "userJwt"

class LoginActivity : AppCompatActivity() {
    private val authApi: AuthApi by lazy {
        Network.retrofit.create(AuthApi::class.java)
    }

    private val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
    private val sharedPreferences: SharedPreferences by lazy {
        EncryptedSharedPreferences.create(
            "security",
            masterKeyAlias,
            this,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    fun get() = sharedPreferences.getString(KEY_TOKEN, null)
    fun set(token: String) {
        sharedPreferences.edit { putString(KEY_TOKEN, token) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setUpViews()
        checkUserJwtSaved()
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

    private fun checkUserJwtSaved() {
        val token = get() ?: "userJwt"

        Log.e("token", token)
        if (token.isBlank() || token == "userJwt") showToast("자동로그인 되지 않았습니다.")
        else {
            Network.jwtToken = token
            showToast("자동로그인 성공했습니다.")
            Log.e("token_decoded", decoded(token).toString())
            finish()
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

    private fun loginSuccessAction(response: Response<ApiTokenResponseModel>) {
        val userJwt = response.body()?.output?.token?.access ?: throw Exception()
        val user = decoded(userJwt)
        Log.e("jwt", user.toString())
        showToast(R.string.login_succeed_logintask)
        set(userJwt)
        Network.jwtToken = userJwt
    }

    private suspend fun login(id: String, pw: String) {
        val refreshToken: String = "refresh"
        val loginModel = UserLoginModel(id, pw, refreshToken)

        try {
            val response = authApi.userLogin(loginModel).awaitResponse()
            Log.e("TEST_LOGIN", response.body().toString())
            when (response.code()) {
                200 -> loginSuccessAction(response)
                409 -> showToast(R.string.login_error_authfail)
                412 -> showToast(R.string.login_error_invalid_format)
                else -> {
                    showToast(R.string.login_error_serverfail)
                }
            }
        } catch (e: Exception) {
            Log.d("testing", "exception")
            e.printStackTrace()
        }
    }


}
