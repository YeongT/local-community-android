package com.implude.localcommunity.ui.member

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.implude.localcommunity.R

class MemberAdapter(
    val context: Context,
    private val MemberList: ArrayList<Member>,
    val itemClick: (Member) -> Unit
) : RecyclerView.Adapter<MemberAdapter.Holder>() {

    inner class Holder(itemView: View, itemClick: (Member) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        private val memberImage: ImageView = itemView.findViewById<ImageView>(R.id.member_image)
        private val memberName: TextView = itemView.findViewById<TextView>(R.id.member_name)
        private val memberRole: TextView = itemView.findViewById<TextView>(R.id.member_role)

        fun bind(mem: Member, context: Context) {
            if (mem.member_image != " ") {
                val resourceId = context.resources.getIdentifier(
                    mem.member_image,
                    "drawable",
                    context.packageName
                )
                memberImage.setImageResource(resourceId)
            } else {
                memberImage.setImageResource(R.drawable.arin)
            }
            memberName.text = mem.name
            memberRole.text = mem.role
            itemView.setOnClickListener {
                itemClick(mem)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.member_layout, parent, false)
        val m: Member
        return Holder(view, itemClick)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(MemberList[position], context)
    }

    override fun getItemCount(): Int {
        return MemberList.size
    }


}