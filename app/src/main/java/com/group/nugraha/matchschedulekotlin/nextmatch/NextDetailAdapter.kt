package com.group.nugraha.matchschedulekotlin.nextmatch

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.group.nugraha.matchschedulekotlin.R
import com.group.nugraha.matchschedulekotlin.model.TeamsItem

class NextDetailAdapter (private val context: Context, private val item: List<TeamsItem>,

                         private val listener: (TeamsItem) -> Unit)

    : RecyclerView.Adapter<NextDetailAdapter.ViewHolder>() {





    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val name = view.findViewById<TextView>(R.id.txt_away_name_club)

        private val image = view.findViewById<ImageView>(R.id.img_away)



        fun bindItem(item: TeamsItem, listener: (TeamsItem) -> Unit) {

            name.text = item.teamName

            Glide.with(itemView.context).load(item.strTeamBadge).into(image)

            itemView.setOnClickListener{

                listener(item)

            }

        }



    }





    override fun onBindViewHolder(holder: NextDetailAdapter.ViewHolder, position: Int) {

        holder.bindItem(item[position], listener)

    }





    override fun getItemCount(): Int = item.size







    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =

        ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item, parent, false))



}