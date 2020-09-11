package com.implude.localcommunity.ui.search

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.implude.localcommunity.R
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity() {

    val searchList = arrayListOf<Search>(
        Search("philip", "hello", "im gag"),
        Search("gagnammin", "gag", "im gag"),
        Search("", "hello", "nice to meet you"),
        Search("", "man", "im example"),
        Search("", "hello", "im gag")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val mAdapter = SearchRvAdapter(this, searchList)
        searchRecyclerView.adapter = mAdapter

        val mDecoration = SearchRvDecoration(60)
        searchRecyclerView.addItemDecoration(mDecoration)

        val lm = LinearLayoutManager(this)
        searchRecyclerView.layoutManager = lm
        searchRecyclerView.setHasFixedSize(true)

        var contentbtn_buttonIsClicked = false
        var tagbtn_buttonIsClicked = false
        var titlebtn_buttonIsClicked = false
        var writerbtn_buttonIsClicked = false

        search_button_contentbtn.setOnClickListener {
            if (!contentbtn_buttonIsClicked) {
                search_button_contentbtn.setBackgroundResource(R.drawable.button_gray)
                contentbtn_buttonIsClicked = false
                search_button_tagbtn.setBackgroundResource(R.drawable.button_gray)
                tagbtn_buttonIsClicked = false
                search_button_titlebtn.setBackgroundResource(R.drawable.button_gray)
                titlebtn_buttonIsClicked = false
                search_button_writerbtn.setBackgroundResource(R.drawable.button_gray)
                writerbtn_buttonIsClicked = false

                search_button_contentbtn.setBackgroundResource(R.drawable.button_black)
                contentbtn_buttonIsClicked = true
            } else {
                search_button_contentbtn.setBackgroundResource(R.drawable.button_gray)
                contentbtn_buttonIsClicked = false
            }
        }
        search_button_tagbtn.setOnClickListener {
            if (!tagbtn_buttonIsClicked) {
                search_button_contentbtn.setBackgroundResource(R.drawable.button_gray)
                contentbtn_buttonIsClicked = false
                search_button_tagbtn.setBackgroundResource(R.drawable.button_gray)
                tagbtn_buttonIsClicked = false
                search_button_titlebtn.setBackgroundResource(R.drawable.button_gray)
                titlebtn_buttonIsClicked = false
                search_button_writerbtn.setBackgroundResource(R.drawable.button_gray)
                writerbtn_buttonIsClicked = false

                search_button_tagbtn.setBackgroundResource(R.drawable.button_black)
                tagbtn_buttonIsClicked = true
            } else {
                search_button_tagbtn.setBackgroundResource(R.drawable.button_gray)
                tagbtn_buttonIsClicked = false
            }
        }
        search_button_titlebtn.setOnClickListener {
            if (!titlebtn_buttonIsClicked) {
                search_button_contentbtn.setBackgroundResource(R.drawable.button_gray)
                contentbtn_buttonIsClicked = false
                search_button_tagbtn.setBackgroundResource(R.drawable.button_gray)
                tagbtn_buttonIsClicked = false
                search_button_titlebtn.setBackgroundResource(R.drawable.button_gray)
                titlebtn_buttonIsClicked = false
                search_button_writerbtn.setBackgroundResource(R.drawable.button_gray)
                writerbtn_buttonIsClicked = false

                search_button_titlebtn.setBackgroundResource(R.drawable.button_black)
                titlebtn_buttonIsClicked = true
            } else {
                search_button_titlebtn.setBackgroundResource(R.drawable.button_gray)
                titlebtn_buttonIsClicked = false
            }
        }
        search_button_writerbtn.setOnClickListener {
            if (!writerbtn_buttonIsClicked) {
                search_button_contentbtn.setBackgroundResource(R.drawable.button_gray)
                contentbtn_buttonIsClicked = false
                search_button_tagbtn.setBackgroundResource(R.drawable.button_gray)
                tagbtn_buttonIsClicked = false
                search_button_titlebtn.setBackgroundResource(R.drawable.button_gray)
                titlebtn_buttonIsClicked = false
                search_button_writerbtn.setBackgroundResource(R.drawable.button_gray)
                writerbtn_buttonIsClicked = false

                search_button_writerbtn.setBackgroundResource(R.drawable.button_black)
                writerbtn_buttonIsClicked = true
            } else {
                search_button_writerbtn.setBackgroundResource(R.drawable.button_gray)
                writerbtn_buttonIsClicked = false
            }
        }

    }
}
