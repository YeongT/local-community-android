package com.implude.localcommunity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        Login_TextView_Register.setOnClickListener(({
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }))
    }
}
