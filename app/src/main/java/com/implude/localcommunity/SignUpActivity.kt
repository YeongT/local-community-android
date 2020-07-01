package com.implude.localcommunity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_sign.*
import java.net.HttpURLConnection
import java.net.URL
import java.util.regex.Pattern
import javax.net.ssl.HttpsURLConnection

class SignUpActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign)

        enroll_btn.setOnClickListener {
            val email = signup_email.text.toString()
            val passwrd1 = signup_pwd.text.toString()
            val passwrd2 = signup_pwd2.text.toString()
            val nickname = signup_name.text.toString()

            if (!Pattern.matches("^[_a-zA-Z0-9-\\.]+@[\\.a-zA-Z0-9-]+\\.[a-zA-Z]+\$", email)) // 이메일
                Log.e("email", "이메일 형식이 아닙니다.")


            if (!Pattern.matches("^[a-zA-Z0-9!@#$^*&()]     {8,20}\$", passwrd1)) // 비밀번호
                Log.e("passward chk", "비밀번호 형식이 아닙니다.")


            Log.e("ID chk", email + " " + passwrd1 + " " + passwrd2 + " " + nickname)

        }
    }
}
