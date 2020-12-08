package com.implude.localcommunity.ui.member

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.implude.localcommunity.R
import com.implude.localcommunity.ui.home.community.Community
import com.implude.localcommunity.ui.home.community.CommunityRvAdapter
import kotlinx.android.synthetic.main.activity_member_profile.*
import kotlinx.android.synthetic.main.community_list.*

class ProfileAct : AppCompatActivity() {

    private var communityList = arrayListOf<Community>()
    private val communityListAdapter by lazy {
        CommunityRvAdapter(
            this,
            communityList
        )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_member_profile)

        val name = intent.getStringExtra("name")
        val role = intent.getStringExtra("role")
        val img = intent.getStringExtra("img")

        member_name_profileAct.setText(name.toString())
        member_role_profileAct.setText(role.toString())

        for(i in 1 until 10) {
            communityListAdapter.addItem(
                Community(
                    "김성호 통구이 커뮤니티",
                    "https://i.pinimg.com/originals/f8/77/a8/f877a8b2fb2ec67c8ce09e60ac865b9b.jpg",
                    "하남 성호를 맛있게 구워보야요",
                    "1"
                )
            )
        }

        Home_RecyclerView_CommunityList.adapter = communityListAdapter
        Home_RecyclerView_CommunityList.setHasFixedSize(true)

    }

}