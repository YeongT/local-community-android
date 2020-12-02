package com.implude.localcommunity.ui.member

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.implude.localcommunity.R

class memberAdapter(val context: Context, val memberList : ArrayList<member>, val itemClick: (member) -> Unit):RecyclerView.Adapter<memberAdapter.Holder>(){

    inner class Holder(itemView: View,itemClick:(member) ->Unit) : RecyclerView.ViewHolder(itemView){

    val memberImage = itemView.findViewById<ImageView>(R.id.member_image)
        val memberName = itemView.findViewById<TextView>(R.id.member_name)
        val memberRole = itemView.findViewById<TextView>(R.id.member_role)


        fun bind(mem : member, context: Context){
            if(mem.member_image != " "){
                val resourceId = context.resources.getIdentifier(mem.member_image, "drawable",context.packageName)
                memberImage?.setImageResource(resourceId)

            }
            else {
                memberImage?.setImageResource(R.mipmap.ic_launcher)
            }
            memberName?.text = mem.name
            memberRole?.text = mem.role


            itemView.setOnClickListener {
                itemClick(mem)
            }


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
      val view = LayoutInflater.from(context).inflate(R.layout.member_layout,parent,false)
        val m : member
        return Holder(view,itemClick)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(memberList[position],context)
    }

    override fun getItemCount(): Int {
       return memberList.size
    }




}