package com.implude.localcommunity.ui.search

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.implude.localcommunity.R
import kotlinx.android.synthetic.main.search_item.view.*

class SearchRvAdapter(val context: Context, private val searchList: ArrayList<Search>) :
    RecyclerView.Adapter<SearchRvAdapter.Holder>() {

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val searchItemImage = itemView.search_item_image
        private val searchItemTitle = itemView.search_item_title
        private val searchItemExplain = itemView.search_item_explain

        fun bind(search: Search, context: Context) {
            val imageResource = if (search.item_image != "")
                context.resources.getIdentifier(search.item_image, "drawable", context.packageName)
            else R.mipmap.ic_launcher

            searchItemImage.setImageResource(imageResource)
            searchItemTitle.text = search.item_title
            searchItemExplain.text = search.item_explain
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.search_item, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return searchList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(searchList[position], context)
    }
}
