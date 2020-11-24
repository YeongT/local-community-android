package com.implude.localcommunity.ui.home.community

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.implude.localcommunity.R
import kotlinx.android.synthetic.main.community_list_item.view.*

class CommunityListRecyclerViewAdapter(
    private val context: Context,
    private val communityList: ArrayList<CommunityList>
) : RecyclerView.Adapter<CommunityListRecyclerViewAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.community_list_item, parent, false)
        return Holder(view)
    }

    fun addItem(communityList: CommunityList) {
        this.communityList.add(communityList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return communityList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(communityList[position], context)
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val communityImage = itemView.Home_ImageView__CommunityImage
        private val name = itemView.Home_TextView_CommunityName
        private val des = itemView.Home_TextView_Description

        fun bind(communityList: CommunityList, context: Context) {
            name.text = communityList.name
            des.text = communityList.description
            Glide.with(context).load(communityList.image).into(communityImage)
        }
    }
}
