package com.implude.localcommunity.ui.main.fragment_messenger

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.implude.localcommunity.R
import com.implude.localcommunity.ui.article.add.ArticleAddActivity

class MessengerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        startActivity(Intent(requireContext(), ArticleAddActivity::class.java))
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_messenger, container, false)
    }
}