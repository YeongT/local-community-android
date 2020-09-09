package com.implude.localcommunity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.implude.localcommunity.models.UserLoginModel
import com.implude.localcommunity.models.UserLoginRespond
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.regex.Pattern


class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val retrofit = Retrofit.Builder()
            .baseUrl(getString(R.string.app_api_server_url))
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        Login_TextView_FindId.setOnClickListener(({
            /*#SHOULD LAUNCH FINDIDPW ACTIVITY*/
        }))

        Login_TextView_Register.setOnClickListener(({
            startActivity(Intent(this, SignUpActivity::class.java))
        }))

        Login_Button_LoginButton.setOnClickListener(({
            val passwordChk =
                Pattern.compile("^.*(?=^.{8,15}\$)(?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@#\$%^&+=]).*")
            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(Login_EditText_Id.text.toString())
                    .matches()
            ) Toast.makeText(this, getString(R.string.login_alert_id), Toast.LENGTH_SHORT).show()
            else if (!passwordChk.toRegex()
                    .matches(Login_EditText_Pwd.text.toString())
            ) Toast.makeText(this, getString(R.string.login_alert_pw), Toast.LENGTH_SHORT).show()
            else {
                val loginModel = UserLoginModel(
                    Login_EditText_Id.text.toString(),
                    Login_EditText_Pwd.text.toString()
                )

                val api = retrofit.create(APIConnect::class.java)
                api.userLogin(loginModel).enqueue(object : Callback<UserLoginRespond> {
                    override fun onResponse(
                        call: Call<UserLoginRespond>,
                        response: Response<UserLoginRespond>
                    ) {
                        when (response.code()) {
                            200 -> {
                                val temp = Toast.makeText(
                                    this@LoginActivity,
                                    getString(R.string.login_succeed_logintask),
                                    Toast.LENGTH_SHORT
                                )
                                temp.setGravity(
                                    Gravity.CENTER,
                                    Gravity.TOP,
                                    Gravity.CENTER
                                )
                                temp.show()
                            }
                            409 -> Toast.makeText(
                                this@LoginActivity,
                                getString(R.string.login_error_authfail),
                                Toast.LENGTH_SHORT
                            ).show()
                            412 -> Toast.makeText(
                                this@LoginActivity,
                                getString(R.string.login_error_invalid_format),
                                Toast.LENGTH_SHORT
                            ).show()
                            else -> Toast.makeText(
                                this@LoginActivity,
                                getString(R.string.login_error_serverfail),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                    override fun onFailure(call: Call<UserLoginRespond>, t: Throwable) {
                        Log.e("Login_err", t.message.toString())
                        t.printStackTrace()
                    }

                })
            }
        }))

    }
}
