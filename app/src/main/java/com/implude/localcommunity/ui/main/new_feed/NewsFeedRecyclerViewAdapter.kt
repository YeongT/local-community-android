package com.implude.localcommunity.ui.main.new_feed

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.implude.localcommunity.R
import kotlinx.android.synthetic.main.news_feed_item.view.*

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
        private val title = itemView.Home_TextView_NewsFeedTitle
        private val text = itemView.Home_TextView_newFeedContent
        private val img = itemView.Home_ImageView_newsFeedImage

        fun bind(newsFeed: NewsFeed, context: Context) {
            title.text = newsFeed.title
            text.text = newsFeed.text
            if (newsFeed.picture.isBlank()) img.visibility = View.GONE
            else Glide.with(context).load(newsFeed.picture).into(img)
        }
    }
}
