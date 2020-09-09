package com.implude.localcommunity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    private var newsFeedList = arrayListOf<NewsFeed>()          // 뉴스피드 데이터 리스트

    private var communityJoined = false                         // 커뮤니티 참가 여부

    private val NEW_ARTICLE_CODE = 100                          // 게시물 코드
    private val TAG = "HomeActivity"

    /* 홈액티비티의 context 와 뉴스피드를 담은 리스트를 매개변수로 보냅니다. */
    private val newsFeedAdapter = NewsFeedRecyclerViewAdapter(
        this,
        arrayListOf<NewsFeed>()
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        newsFeedRecyclerViewSetUp()                             // 리사이클러뷰 어댑터 연동

        /* UserJwt 발급 */
        LoginTest.setOnClickListener(({
            val intent = Intent(this@HomeActivity, LoginActivity::class.java)
            startActivity(intent)
        }))

        /* 새 게시물 버튼 */
        Home_RelativeLayout_newArticle.setOnClickListener(({
            val intent = Intent(this, ArticleAddActivity::class.java)
            startActivityForResult(intent, NEW_ARTICLE_CODE)
        }))

        /* 프로필 버튼 */
        Home_CircleImageView_Profile.setOnClickListener(({
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }))

        /* 프로필 액티비티 */
        btn_test4.setOnClickListener(( {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }))

        if (!communityJoined) {
            Home_RelativeLayout_No_Community.visibility = View.GONE             // 커뮤니티 있으면 커뮤니티 없음 레이아웃 가리고
            Home_RecyclerView_newsFeedList.visibility = View.VISIBLE            // 대신 뉴스피드 리스트 표시
        } else {
            Home_RelativeLayout_No_Community.visibility = View.VISIBLE          // 커뮤니티 없으면 커뮤니티 없음 레이아웃 표시
        }
    }

    /* 새 게시물 응답 메소드 */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        Log.e(TAG, "data : " + data.toString())                           // 데이터 상태 확인
        if (requestCode == NEW_ARTICLE_CODE && resultCode == Activity.RESULT_OK) {
            newsFeedAdapter.addItem(data?.getParcelableExtra("dataList") ?: return)
        }
    }

    /* 뉴스피드 리사이클러뷰 초기 셋팅 메소드 */
    private fun newsFeedRecyclerViewSetUp() {
        Home_RecyclerView_newsFeedList.adapter = newsFeedAdapter    // 뉴스피드 리시아클러뷰 어댑터로 NewsFeedRecyclerViewAdapter 사용
        Home_RecyclerView_newsFeedList.setHasFixedSize(true)        // RecyclerView 에서 아이템 변동 있을 때마다 RecyclerView 의 width 와 height 를 고정한 채로 유지
    }
}