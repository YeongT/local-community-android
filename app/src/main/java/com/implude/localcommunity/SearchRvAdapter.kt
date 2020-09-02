package com.implude.localcommunity

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SearchRvAdapter(val context: Context, val searchList: ArrayList<Search>) :
    RecyclerView.Adapter<SearchRvAdapter.Holder>() {

    inner class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        val search_item_image = itemView?.findViewById<ImageView>(R.id.search_item_image)
        val search_item_title = itemView?.findViewById<TextView>(R.id.search_item_title)
        val search_item_explain = itemView?.findViewById<TextView>(R.id.search_item_explain)

        fun bind(search: Search, context: Context) {
            if (search.item_image != "") {
                val resourceId = context.resources.getIdentifier(
                    search.item_image,
                    "drawable",
                    context.packageName
                )
                search_item_image?.setImageResource(resourceId)
            } else {
                search_item_image?.setImageResource(R.mipmap.ic_launcher)
            }

            search_item_title?.text = search.item_title
            search_item_explain?.text = search.item_explain
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
        holder?.bind(searchList[position], context)
    }
}