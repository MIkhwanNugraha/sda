package com.group.nugraha.matchschedulekotlin.nextmatch

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.group.nugraha.matchschedulekotlin.R

import com.group.nugraha.matchschedulekotlin.model.EventsItem


class NextAdapter (private val context: Context?, private val events: List<EventsItem>)
    : RecyclerView.Adapter<NextAdapter.NextHolder>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int)= NextHolder(LayoutInflater.from(context).inflate(R.layout.list_item, p0, false)) {
        }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(p0: NextHolder, p1: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    class NextHolder(view:View) :RecyclerView.ViewHolder(view){

    }
}