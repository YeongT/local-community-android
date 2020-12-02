package com.implude.localcommunity.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import com.implude.localcommunity.ui.community.create.CreateCommunityActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(@NonNull savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = Intent(this, CreateCommunityActivity::class.java)
        startActivity(intent)
        finish()
    }
}
