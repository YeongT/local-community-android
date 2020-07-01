package com.implude.localcommunity

import android.content.Intent
import android.os.Bundle
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity


class SplashActivity : AppCompatActivity() {
    override fun onCreate(@NonNull savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }
}