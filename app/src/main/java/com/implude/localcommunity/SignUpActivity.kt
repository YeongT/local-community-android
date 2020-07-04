package com.implude.localcommunity

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.implude.localcommunity.models.UserRegisterModel
import kotlinx.android.synthetic.main.activity_sign.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.regex.Pattern

const val BASE_URL = "https://api.hakbong.me/"

class SignUpActivity : AppCompatActivity() {
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign)

        enroll_btn.setOnClickListener {
            val email = signup_email.text.toString()
            val password1 = signup_pwd.text.toString()
            val password2 = signup_pwd2.text.toString()
            val nickname = signup_name.text.toString()

//            if (!Pattern.matches("^[_a-zA-Z0-9-\\.]+@[\\.a-zA-Z0-9-]+\\.[a-zA-Z]+\$", email)) // 이메일
//                Log.e("email", "이메일 형식이 아닙니다.")
//
//
//            if (!Pattern.matches("^[a-zA-Z0-9!@#$^*&()]     {8,20}\$", password1)) // 비밀번호
//                Log.e("passward chk", "비밀번호 형식이 아닙니다.")
//
//            if (!password1.equals(password2))
//                Log.e("passward chk", "비밀번호와 비밀번호 확인이 동일하지 않습니다.")


            val model = UserRegisterModel(
                email,
                password1,
                nickname,
                "010-1234-5678",
                "1",
                "{\"state\":\"경기도\",\"city\":\"안산시\",\"dong\":\"와동\"}\n"
            )

            val api = retrofit.create(Api::class.java)
            api.signUp(model).enqueue(object : Callback<String> {
                override fun onFailure(call: Call<String>, t: Throwable) {
                    Log.e("APITEST_FAIL",t.message.toString())
                    t.printStackTrace()

                    Toast.makeText(this@SignUpActivity,"오류",Toast.LENGTH_LONG).show()

                }

                override fun onResponse(call: Call<String>, response: Response<String>) {
                    Log.e("APITEST-SUC", response.code().toString())

                    if(response.code().toString() != "200") {
                        val toast = Toast.makeText(this@SignUpActivity, "오류", Toast.LENGTH_LONG)
                        toast.setGravity(
                            Gravity.CENTER,
                            Gravity.CENTER_HORIZONTAL,
                            Gravity.CENTER_VERTICAL
                        )

                        toast.show()
                    }
                }

            })
        }
    }

}
