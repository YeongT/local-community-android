package com.implude.localcommunity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_home.*
import org.json.JSONObject
import java.text.SimpleDateFormat


class HomeActivity : AppCompatActivity() {

    private var newsFeedList = arrayListOf<NewsFeed?>() // 뉴스피드 데이터 리스트

    private val NEW_ARTICLE_CODE = 100

    private val newsFeedAdapter = NewsFeedRecyclerViewAdapter(
        this,
        arrayListOf<NewsFeed?>()
    ) // 홈액티비티의 context와 뉴스피드를 담은 리스트를 매개변수로 보냅니다.

    private val TAG = "HomeActivity" // 로그 태그

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        var communityJoined = false // 커뮤니티 참가 여부

        jsonObjectsExample()
        newsFeedRecyclerViewSetUp() // 리사이클러뷰 어댑터 연동

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

        if (!communityJoined) {
            Home_RelativeLayout_No_Community.visibility = View.GONE // 커뮤니티 있으면 커뮤니티 없음 레이아웃 가리고
            Home_RecyclerView_newsFeedList.visibility = View.VISIBLE // 대신 뉴스피드 리스트 표시
        } else {
            Home_RelativeLayout_No_Community.visibility = View.VISIBLE // 커뮤니티 없으면 커뮤니티 없음 레이아웃 표시
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        Log.e(TAG, "데이터는 : " + data.toString())
        if (requestCode == NEW_ARTICLE_CODE && resultCode == Activity.RESULT_OK) {
            newsFeedAdapter.addItem(data?.getParcelableExtra("dataList"))
        }
    }

    private fun newsFeedRecyclerViewSetUp() { // 뉴스피드 리사이클러뷰 초기 셋팅 메소드

        Home_RecyclerView_newsFeedList.adapter = newsFeedAdapter
        // 이 화면에 생성될 RecyclerView의 어댑터로 NewsFeedRecycleriewAdapter 형의 newsFeedAdapter를 사용하겠다...

        val lm = LinearLayoutManager(this)
        Home_RecyclerView_newsFeedList.layoutManager = lm // RecyclerView의 레이아웃을 LinearLayout으로 관리
        Home_RecyclerView_newsFeedList.setHasFixedSize(true) // RecyclerView에서 아이템이 추가, 삭제될 때 RecyclerView의 width와 height를 고정한 채로 유지
    }

    fun jsonObjectsExample() { // JSON 문자열 변환 메소드
        val jsonString = """
        {
            "name": "식빵",
            "family": "웰시코기",
            "age": 1,
            "weight": 2.14
        }
    """.trimIndent() // 문장을 일렬로 정렬하는 메소드

        val jObject = JSONObject(jsonString) // JSON 형태의 데이터를 관리하는 메소드

        // getString("key") -> key값에 해당하는 value 반환하는 메소드
        var title = jObject.getString("name")
        var url = jObject.getString("family")
        var draft = jObject.getInt("age")
        var weight = jObject.getDouble("weight")
    }
}