package com.implude.localcommunity.ui.search

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.implude.localcommunity.R
import com.implude.localcommunity.databinding.ActivitySearchBinding
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity() {
    val selectedButton = MutableLiveData("")

    private val searchList = arrayListOf(
        Search("philip", "hello", "im gag"),
        Search("gagnammin", "gag", "im gag"),
        Search("", "hello", "nice to meet you"),
        Search("", "man", "im example"),
        Search("", "hello", "im gag")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivitySearchBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_search)
        binding.lifecycleOwner = this
        binding.activity = this

        searchRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@SearchActivity)
            addItemDecoration(SearchRvDecoration(60))
            setHasFixedSize(true)
            adapter = SearchRvAdapter(this@SearchActivity, searchList)
        }
    }

    fun onSearchButtonClicked(view: View) {
        val text = (view as? TextView)?.text ?: return
        selectedButton.value = text.toString()
    }
}
