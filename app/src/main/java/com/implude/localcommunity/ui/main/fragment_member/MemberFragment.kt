package com.implude.localcommunity.ui.main.fragment_member

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.implude.localcommunity.R
import com.implude.localcommunity.ui.home.community.Community
import com.implude.localcommunity.ui.home.community.CommunityRvAdapter
import com.implude.localcommunity.ui.member.*
import kotlinx.android.synthetic.main.community_list.view.*
import kotlinx.android.synthetic.main.fragment_member.*
import kotlinx.android.synthetic.main.fragment_member.view.*

class MemberFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_member, container, false)

        val memberList = arrayListOf<Member>(
            Member("김성호", "리더", " "),
            Member("김재우", "리더", " "),
            Member("한경민", "리더", " "),
            Member("장종우", "리더", " "),
            Member("윤영창", "리더", " "),
            Member("일성호", "일꾼", " "),
            Member("일재우", "일꾼", " "),
            Member("일경민", "일꾼", " "),
            Member("일종우", "일꾼", " "),
            Member("일영창", "일꾼", " "),
        )

        val memberAdapter = MemberAdapter(
            requireContext(),
            memberList
        ) { member ->
            val profileIntent = Intent(context, ProfileAct::class.java)
            profileIntent.putExtra("name", member.name)
            profileIntent.putExtra("role", member.role)
            profileIntent.putExtra("img", member.member_image)
            startActivity(profileIntent)
        }
        
        val lm = LinearLayoutManager(requireContext())
        member_list.layoutManager = lm
        view.member_list.adapter = memberAdapter;
        member_list.setHasFixedSize(true)
        return view
    }

}