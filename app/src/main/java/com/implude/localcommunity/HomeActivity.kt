package com.implude.localcommunity

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    private var newsFeedList = arrayListOf<NewsFeed>(
        NewsFeed(
            "TalYeongJongChangWuMo",
            "미안하다 이거 보여줄려고 어그로 끌었다 ㄹㅇ 한소희 미모 수준 실화냐? 진짜 세계관 최강자의 얼굴이다...",
            "hansohee",
            "07/29 07:34"
        ),
        NewsFeed(
            "TalYeongJongChangWuMo",
            "미안하다 이거 보여줄려고 어그로 끌었다 ㄹㅇ 한소희 미모 수준 실화냐? 진짜 세계관 최강자의 얼굴이다...",
            "hansohee",
            "08/01 14:51"
        ),
        NewsFeed(
            "TalYeongJongChangWuMo",
            "미안하다 이거 보여줄려고 어그로 끌었다 ㄹㅇ 한소희 미모 수준 실화냐? 진짜 세계관 최강자의 얼굴이다...",
            "hansohee",
            "08/01 14:51"
        ),
        NewsFeed(
            "TalYeongJongChangWuMo",
            "미안하다 이거 보여줄려고 어그로 끌었다 ㄹㅇ 한소희 미모 수준 실화냐? 진짜 세계관 최강자의 얼굴이다...",
            "hansohee",
            "08/01 14:51"
        ),
        NewsFeed(
            "TalYeongJongChangWuMo",
            "미안하다 이거 보여줄려고 어그로 끌었다 ㄹㅇ 한소희 미모 수준 실화냐? 진짜 세계관 최강자의 얼굴이다...",
            "hansohee",
            "08/01 14:51"
        ),
        NewsFeed(
            "TalYeongJongChangWuMo",
            "미안하다 이거 보여줄려고 어그로 끌었다 ㄹㅇ 한소희 미모 수준 실화냐? 진짜 세계관 최강자의 얼굴이다...",
            "hansohee",
            "08/01 14:51"
        ),
        NewsFeed(
            "TalYeongJongChangWuMo",
            "미안하다 이거 보여줄려고 어그로 끌었다 ㄹㅇ 한소희 미모 수준 실화냐? 진짜 세계관 최강자의 얼굴이다...",
            "hansohee",
            "08/01 14:51"
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        var communityJoined = false

        newsFeedRecyclerViewSetUp() // 리사이클러뷰 어댑터 연동

        /* 프로필 버튼 */
        Home_CircleImageView_Profile.setOnClickListener(({
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }))

        if (!communityJoined) {
            Home_RelativeLayout_No_Community.visibility = View.GONE // 커뮤니티 없으면 커뮤니티 없음 레이아웃 표시
            Home_RecyclerView_newsFeedList.visibility = View.VISIBLE
        } else {
            Home_RelativeLayout_No_Community.visibility = View.VISIBLE // 커뮤니티 있으면 커뮤니티의 뉴스피드 표시
        }
    }

    private fun newsFeedRecyclerViewSetUp() {
        val newsFeedAdapter = NewsFeedRecyclerViewAdapter(
            this,
            newsFeedList
        ) // 홈액티비티의 context와 뉴스피드를 담은 리스트를 매개변수로 보냅니다.

        Home_RecyclerView_newsFeedList.adapter = newsFeedAdapter
        // 이 화면에 생성될 RecyclerView의 어댑터로 NewsFeedRecycleriewAdapter 형의 newsFeedAdapte를 사용하겠다...

        val lm = LinearLayoutManager(this)
        Home_RecyclerView_newsFeedList.layoutManager = lm // RecyclerView의 레이아웃을 LinearLayout으로 관리
        Home_RecyclerView_newsFeedList.setHasFixedSize(true) // RecyclerView에서 아이템이 추가, 삭제될 때 RecyclerView의 width와 height를 고정한 채로 유지
    }

    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }

    companion object {
        private val IMAGE_PICK_CODE = 1000
        private val PERMISSION_CODE = 1001
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            PERMISSION_CODE -> {
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    pickImageFromGallery()
                } else {
                    Toast.makeText(this, "권한이 거부되었습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE) {

        }
    }
}