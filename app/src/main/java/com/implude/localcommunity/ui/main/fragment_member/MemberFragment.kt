package com.implude.localcommunity.ui.main.fragment_member

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.implude.localcommunity.R
import com.implude.localcommunity.ui.member.Community_member_list
import com.implude.localcommunity.ui.member.ProfileAct
import com.implude.localcommunity.ui.member.memberAdapter
import com.implude.localcommunity.ui.member.memberList
import kotlinx.android.synthetic.main.fragment_member.*
import kotlinx.android.synthetic.main.fragment_member.view.*

class     MemberFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val layout = inflater.inflate(R.layout.fragment_member, container, false)

        layout.member_list_button.setOnClickListener {



            val intent = Intent(this.requireContext(),Community_member_list::class.java)
            startActivity(intent)
        }
        return layout



    }


}