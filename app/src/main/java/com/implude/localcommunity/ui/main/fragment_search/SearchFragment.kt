package com.implude.localcommunity.ui.main.fragment_search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.implude.localcommunity.R
import com.implude.localcommunity.databinding.FragmentSearchBinding
import com.implude.localcommunity.ui.search.Search
import com.implude.localcommunity.ui.search.SearchRvAdapter
import com.implude.localcommunity.ui.search.SearchRvDecoration
import kotlinx.android.synthetic.main.fragment_search.*

class SearchFragment : Fragment() {

    val selectedButton = MutableLiveData("")

    private val searchList = arrayListOf(
        Search("philip", "hello", "im gag"),
        Search("gagnammin", "gag", "im gag"),
        Search("", "hello", "nice to meet you"),
        Search("", "man", "im example"),
        Search("", "hello", "im gag")
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentSearchBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false);
        binding.lifecycleOwner = this
        binding.fragment = this

        binding.searchButtonContentbtn.setOnClickListener {
            onSearchButtonClicked(binding.searchButtonContentbtn)
        }

        binding.searchButtonTagbtn.setOnClickListener {
            onSearchButtonClicked(binding.searchButtonTagbtn)
        }

        binding.searchButtonTitlebtn.setOnClickListener {
            onSearchButtonClicked(binding.searchButtonTitlebtn)
        }

        binding.searchButtonWriterbtn.setOnClickListener {
            onSearchButtonClicked(binding.searchButtonWriterbtn)
        }

        binding.searchRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@SearchFragment.context)
            addItemDecoration(SearchRvDecoration(60))
            setHasFixedSize(true)
            adapter = SearchRvAdapter(this@SearchFragment.requireContext(), searchList)
            return binding.root
        }
    }

    fun onSearchButtonClicked(view: View) {
        val text = (view as? TextView)?.text ?: return
        selectedButton.value = text.toString()
    }

}