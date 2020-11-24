package com.implude.localcommunity.ui.home

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.implude.localcommunity.R
import com.implude.localcommunity.ui.article_add.ArticleAddActivity
import com.implude.localcommunity.ui.home.community.CommunityList
import com.implude.localcommunity.ui.home.community.CommunityListRecyclerViewAdapter
import com.implude.localcommunity.ui.home.new_feed.NewsFeed
import com.implude.localcommunity.ui.home.new_feed.NewsFeedRecyclerViewAdapter
import com.implude.localcommunity.ui.login.LoginActivity
import com.implude.localcommunity.ui.profile.ProfileActivity
import kotlinx.android.synthetic.main.activity_home.*

private const val REQUEST_NEW_ARTICLE = 100
private const val TAG = "HomeActivity"

class HomeActivity : AppCompatActivity() {
    private var newsFeedList = arrayListOf<NewsFeed>()
    private var communityJoined = false
    private val newsFeedAdapter = NewsFeedRecyclerViewAdapter(this, newsFeedList)

    private var communityList = arrayListOf<CommunityList>()
    private val communityListAdapter = CommunityListRecyclerViewAdapter(this, communityList)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setUpCommunityListRecyclerView()
        setUpNewFeedRecyclerView()

        communityListAdapter.addItem(
            CommunityList(
                "김성호 통구이 커뮤니티",
                "https://i.pinimg.com/originals/f8/77/a8/f877a8b2fb2ec67c8ce09e60ac865b9b.jpg",
                "하남 성호를 맛있게 구워보야요",
                "1"
            )
        )
        communityListAdapter.addItem(
            CommunityList(
                "김성호 통구이 커뮤니티",
                "https://i.pinimg.com/originals/f8/77/a8/f877a8b2fb2ec67c8ce09e60ac865b9b.jpg",
                "하남 성호를 맛있게 구워보야요",
                "1"
            )
        )
        communityListAdapter.addItem(
            CommunityList(
                "김성호 통구이 커뮤니티",
                "https://i.pinimg.com/originals/f8/77/a8/f877a8b2fb2ec67c8ce09e60ac865b9b.jpg",
                "하남 성호를 맛있게 구워보야요",
                "1"
            )
        )
        communityListAdapter.addItem(
            CommunityList(
                "김성호 통구이 커뮤니티",
                "https://i.pinimg.com/originals/f8/77/a8/f877a8b2fb2ec67c8ce09e60ac865b9b.jpg",
                "하남 성호를 맛있게 구워보야요",
                "1"
            )
        )
        communityListAdapter.addItem(
            CommunityList(
                "김성호 통구이 커뮤니티",
                "https://i.pinimg.com/originals/f8/77/a8/f877a8b2fb2ec67c8ce09e60ac865b9b.jpg",
                "하남 성호를 맛있게 구워보야요",
                "1"
            )
        )

        LoginTest.setOnClickListener {
            val intent = Intent(this@HomeActivity, LoginActivity::class.java)
            startActivity(intent)
        }

        Home_RelativeLayout_newArticle.setOnClickListener {
            val intent = Intent(this, ArticleAddActivity::class.java)
            startActivityForResult(intent, REQUEST_NEW_ARTICLE)
        }

        Home_CircleImageView_Profile.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        Home_ImageView_noticeIcon.setOnClickListener {
            communityJoined = !communityJoined
            toggleCommunityLayout()
        }

        toggleCommunityLayout()
    }

    private fun setUpNewFeedRecyclerView() {
        Home_RecyclerView_newsFeedList.adapter = newsFeedAdapter
        Home_RecyclerView_newsFeedList.setHasFixedSize(true)
    }

    private fun setUpCommunityListRecyclerView() {
        Home_RecyclerView_CommunityList.adapter = communityListAdapter
        Home_RecyclerView_CommunityList.setHasFixedSize(true)
    }

    private fun toggleCommunityLayout() {
        if (communityJoined) {
            Home_RelativeLayout_No_Community.visibility = View.GONE
            Home_RelativeLayout_Community.visibility = View.VISIBLE
        } else {
            Home_RelativeLayout_No_Community.visibility = View.VISIBLE
            Home_RelativeLayout_Community.visibility = View.GONE
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        Log.e(TAG, "data : " + data.toString())
        if (requestCode == REQUEST_NEW_ARTICLE && resultCode == Activity.RESULT_OK) {
            newsFeedAdapter.addItem(data?.getParcelableExtra("dataList") ?: return)
        }
    }
}
