package com.implude.localcommunity.ui.member

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import com.implude.localcommunity.R
import kotlinx.android.synthetic.main.fragment_member.*
import kotlinx.android.synthetic.main.fragment_member.view.*

class Community_member_list : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_community_member_list)


        val madapter  = memberAdapter(
            this,
            memberList
        ) { member ->
            val profileIntent = Intent(this, ProfileAct::class.java)
            profileIntent.putExtra("name", member.name)
            profileIntent.putExtra("role", member.role)
            profileIntent.putExtra("img", member.member_image)
            startActivity(profileIntent)
        }

        member_list.adapter = madapter

        val lm = LinearLayoutManager(this)
        member_list.layoutManager = lm
        member_list.setHasFixedSize(true)
    }
}