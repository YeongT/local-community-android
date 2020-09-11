package com.implude.localcommunity.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.implude.localcommunity.R
import com.implude.localcommunity.network.AuthApi
import com.implude.localcommunity.network.Network
import com.implude.localcommunity.network.models.UserLoginModel
import com.implude.localcommunity.ui.signup.SignUpActivity
import com.implude.localcommunity.util.EMAIL_REGEX
import com.implude.localcommunity.util.PASSWORD_REGEX
import com.implude.localcommunity.util.showToast
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.launch
import retrofit2.awaitResponse
import java.util.regex.Pattern

class LoginActivity : AppCompatActivity() {
    private val authApi: AuthApi by lazy {
        Network.retrofit.create(AuthApi::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setUpViews()
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

        if (Pattern.matches(EMAIL_REGEX, id)) {
            showToast(R.string.login_alert_id)
        } else if (!Pattern.matches(PASSWORD_REGEX, pw)) {
            showToast(R.string.login_alert_pw)
        } else {
            lifecycleScope.launch { login(id, pw) }
        }
    }

    private suspend fun login(id: String, pw: String) {
        val loginModel = UserLoginModel(id, pw)

        try {
            val response = authApi.userLogin(loginModel).awaitResponse()
            when (response.code()) {
                200 -> showToast(R.string.login_succeed_logintask)
                409 -> showToast(R.string.login_error_authfail)
                412 -> showToast(R.string.login_error_invalid_format)
                else -> {
                    showToast(R.string.login_error_serverfail)
                    Log.d("testing", response.code().toString())
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
