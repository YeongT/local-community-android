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
    private val NewsFeedList: ArrayList<NewsFeed>,
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
        private val title = itemView.findViewById<TextView>(R.id.Home_TextView_NewsFeedTitle)
        private val text = itemView.findViewById<TextView>(R.id.Home_TextView_newFeedContent)
        private val img = itemView.findViewById<ImageView>(R.id.Home_ImageView_newsFeedImage)

        fun bind(newsFeed: NewsFeed, context: Context) {
            title.text = newsFeed.title                                     // 제목
            text.text = newsFeed.text                                       // 내용
            if (newsFeed.picture.isBlank()) img.visibility = View.GONE      // 이미지가 비어있다면 ImageView 없애기
            else Glide.with(context).load(newsFeed.picture).into(img)       // 이미지가 있다면 ImageView에 이미지 넣기
        }
    }
}