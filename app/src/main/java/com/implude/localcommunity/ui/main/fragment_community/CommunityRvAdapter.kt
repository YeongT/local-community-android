package com.implude.localcommunity.ui.home.community

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.implude.localcommunity.R
import kotlinx.android.synthetic.main.community_list_item.view.*

class CommunityRvAdapter(
    private val context: Context,
    private val community: ArrayList<Community>
) : RecyclerView.Adapter<CommunityRvAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.community_list_item, parent, false)
        return Holder(view)
    }

    fun addItem(community: Community) {
        this.community.add(community)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return community.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(community[position], context)
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val communityImage = itemView.Home_ImageView__CommunityImage
        private val name = itemView.Home_TextView_CommunityName
        private val des = itemView.Home_TextView_Description

        fun bind(community: Community, context: Context) {
            name.text = community.name
            des.text = community.description
            Glide.with(context).load(community.image).into(communityImage)
        }
    }
}
