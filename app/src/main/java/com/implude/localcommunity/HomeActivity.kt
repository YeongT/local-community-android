package com.implude.localcommunity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        /* 프로필 버튼 */
        Home_CircleImageView_Profile.setOnClickListener(({
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }))
    }
}