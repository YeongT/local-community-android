package com.implude.localcommunity

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NewsFeedRecyclerViewAdapter(
    private val context: Context,
    private val NewsFeedList: ArrayList<NewsFeed>
) :
    RecyclerView.Adapter<NewsFeedRecyclerViewAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.news_feed_item, parent, false)
        return Holder(view)
    }

    fun addItem(newsFeed: NewsFeed) {
        NewsFeedList.add(newsFeed)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return NewsFeedList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(NewsFeedList[position], context)
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val userLink = itemView.findViewById<TextView>(R.id.Home_TextView_newsFeedUserLink)
        private val title = itemView.findViewById<TextView>(R.id.Home_TextView_NewsFeedTitle)
        private val text = itemView.findViewById<TextView>(R.id.Home_TextView_newFeedContent)
        private val picture = itemView.findViewById<ImageView>(R.id.Home_ImageView_newsFeedImage)

        fun bind(newsFeed: NewsFeed, context: Context) {
            title.text = newsFeed.title                                     // 제목
            text.text = newsFeed.text                                       // 내용
            if (picture != null)                                            // 이미지가 비어있지 않다면
                Glide.with(context).load(newsFeed.picture).into(picture)    // 파이어베이스 링크를 이미지로 변환
        }
    }
}