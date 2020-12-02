package com.implude.localcommunity.ui.member

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.implude.localcommunity.R
import com.implude.localcommunity.ui.member.RegisteredCommunityAdapter
import com.implude.localcommunity.ui.member.RegisteredCommunityList
import kotlinx.android.synthetic.main.activity_member_profile.*

class ProfileAct : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_member_profile)

        val name = intent.getStringExtra("name")
        val role = intent.getStringExtra("role")
        val img = intent.getStringExtra("img")



        member_name_profileAct.setText(name.toString())
        member_role_profileAct.setText(role.toString())


        val madapter2  =
            RegisteredCommunityAdapter(
                this,
                RegisteredCommunityList
            ) { RegisteredCommunity ->

            }
        community_list.adapter = madapter2

        val lm = LinearLayoutManager(this)
        community_list.layoutManager = lm
        community_list.setHasFixedSize(true)
    }
}