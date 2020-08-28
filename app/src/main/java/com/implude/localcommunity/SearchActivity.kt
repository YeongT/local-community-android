package com.implude.localcommunity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

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
