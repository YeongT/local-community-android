package com.implude.localcommunity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        btn_test1.setOnClickListener(({
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }))
        btn_test2.setOnClickListener(({
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }))
        btn_test3.setOnClickListener(({
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }))
    }
}