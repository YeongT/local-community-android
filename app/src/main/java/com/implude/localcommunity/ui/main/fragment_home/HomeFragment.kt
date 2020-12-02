package com.implude.localcommunity.ui.main.fragment_home

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.implude.localcommunity.R
import com.implude.localcommunity.ui.article.add.ArticleAddActivity
import com.implude.localcommunity.ui.community.create.CreateCommunityActivity
import com.implude.localcommunity.ui.home.community.Community
import com.implude.localcommunity.ui.home.community.CommunityRvAdapter
import com.implude.localcommunity.ui.main.new_feed.NewsFeed
import com.implude.localcommunity.ui.main.new_feed.NewsFeedRecyclerViewAdapter
import com.implude.localcommunity.ui.login.LoginActivity
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*

private const val REQUEST_NEW_ARTICLE = 100
private const val TAG = "HomeActivity"

class HomeFragment : Fragment() {
    private var newsFeedList = arrayListOf<NewsFeed>()
    private var communityJoined = false
    private val newsFeedAdapter by lazy {
        NewsFeedRecyclerViewAdapter(
            requireContext(),
            newsFeedList
        )
    }
    private var communityList = arrayListOf<Community>()
    private val communityListAdapter by lazy {
        CommunityRvAdapter(
            requireContext(),
            communityList
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_home, container, false)
        setUpNewFeedRecyclerView(view)
        setUpCommunityListRecyclerView(view)

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

        view.LoginTest.setOnClickListener {
            val intent = Intent(context, LoginActivity::class.java)
            startActivity(intent)
        }

        view.Home_RelativeLayout_newArticle.setOnClickListener {
            val intent = Intent(context, ArticleAddActivity::class.java)
            startActivityForResult(
                intent,
                REQUEST_NEW_ARTICLE
            )
        }

        view.Home_ImageView_noticeIcon.setOnClickListener {
            communityJoined = !communityJoined
            toggleCommunityLayout(view)
        }

        view.Home_ImageButton_CreateCommunity.setOnClickListener {
            val intent = Intent(context, CreateCommunityActivity::class.java)
            startActivity(intent)
        }

        toggleCommunityLayout(view)

        return view
    }

    private fun setUpNewFeedRecyclerView(view: View) {
        view.Home_RecyclerView_newsFeedList.adapter = newsFeedAdapter
        view.Home_RecyclerView_newsFeedList.setHasFixedSize(true)
    }

    private fun setUpCommunityListRecyclerView(view: View) {
        view.Home_RecyclerView_CommunityList.adapter = communityListAdapter
        view.Home_RecyclerView_CommunityList.setHasFixedSize(true)
    }

    private fun toggleCommunityLayout(view: View) {
        if (communityJoined) {
            view.Home_RelativeLayout_No_Community.visibility = View.GONE
            view.Home_RelativeLayout_In_Community.visibility = View.VISIBLE
        } else {
            view.Home_RelativeLayout_No_Community.visibility = View.VISIBLE
            view.Home_RelativeLayout_In_Community.visibility = View.GONE
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
