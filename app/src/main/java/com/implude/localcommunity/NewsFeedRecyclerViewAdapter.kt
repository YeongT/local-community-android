package com.implude.localcommunity

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NewsFeedRecyclerViewAdapter(val context: Context, val NewsFeedList: ArrayList<NewsFeed>) :
    RecyclerView.Adapter<NewsFeedRecyclerViewAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.news_feed_item, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return NewsFeedList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(NewsFeedList[position], context)
    }

    inner class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        private val userLink = itemView?.findViewById<TextView>(R.id.Home_TextView_newsFeedUserLink)
        private val time = itemView?.findViewById<TextView>(R.id.Home_TextView_newsFeedTime)
        private val content = itemView?.findViewById<TextView>(R.id.Home_TextView_newFeedContent)

        fun bind(newsFeed: NewsFeed, context: Context) {
            userLink?.text = newsFeed.userLink
            time?.text = newsFeed.time
            content?.text = newsFeed.content
        }
    }
}