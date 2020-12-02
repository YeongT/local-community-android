package com.implude.localcommunity.ui.member

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.implude.localcommunity.R


class RegisteredCommunityAdapter (val context: Context, val RegisteredCommunityList : ArrayList<RegisteredCommunity>, val itemClick: (RegisteredCommunity) -> Unit):
        RecyclerView.Adapter<RegisteredCommunityAdapter.RcHolder>(){

        inner class RcHolder(itemView: View, itemClick:(RegisteredCommunity) ->Unit) : RecyclerView.ViewHolder(itemView){

            val RegisteredCommunityImage = itemView.findViewById<ImageView>(R.id.registered_community_image)
            val RegisteredCommunityName = itemView.findViewById<TextView>(R.id.registered_community_name)
            val RegisteredCommunityRole = itemView.findViewById<TextView>(R.id.registered_community_role)


            fun bind(rc: RegisteredCommunity, context: Context){
                if(rc.communityImg != " "){
                    val resourceId = context.resources.getIdentifier(rc.communityImg, "drawable",context.packageName)
                    RegisteredCommunityImage?.setImageResource(resourceId)

                }
                else {
                    RegisteredCommunityImage?.setImageResource(R.mipmap.ic_launcher)
                }
                RegisteredCommunityName?.text = rc.name
                RegisteredCommunityRole?.text = rc.info


                itemView.setOnClickListener {
                    itemClick(rc)
                }


            }

        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RcHolder {
            val view = LayoutInflater.from(context).inflate(R.layout.registeredcommunity,parent,false)

            return RcHolder(view,itemClick)
        }




        override fun getItemCount(): Int {
            return RegisteredCommunityList.size
        }

    override fun onBindViewHolder(holder: RcHolder, position: Int) {
        holder.bind(RegisteredCommunityList[position],context)
    }
}


