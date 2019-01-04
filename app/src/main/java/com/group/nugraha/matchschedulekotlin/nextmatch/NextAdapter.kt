package com.group.nugraha.matchschedulekotlin.nextmatch

import android.content.Context
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.group.nugraha.matchschedulekotlin.R
import com.group.nugraha.matchschedulekotlin.model.EventsItem
import org.jetbrains.anko.startActivity
import java.text.SimpleDateFormat
import java.util.*


class NextAdapter (private val context: Context?, private val events: List<EventsItem>)
    : RecyclerView.Adapter<NextAdapter.NextHolder>(){

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int) =
        NextHolder(LayoutInflater.from(context).inflate(R.layout.list_item, p0, false))


    override fun getItemCount(): Int = events.size

    //@RequiresApi(Build.VERSION_CODES.0)
    override fun onBindViewHolder(p0: NextHolder, p1: Int) {
        p0.bindItem(events [p1])
        p0.itemView.setOnClickListener{
            context?.startActivity<NextDetailActivity>(
                NextDetailActivity.ID_EVENTS to events[p1].idEvent
                )
        }
    }

    class NextHolder(view:View) :RecyclerView.ViewHolder(view){
        private val dateEvents = view.findViewById<TextView>(R.id.txt_dateEvent)
        private val strAway = view.findViewById<TextView>(R.id.strAwayTeam)
        private val strHome = view.findViewById<TextView>(R.id.strHomeTeam)
        private val homeScore = view.findViewById<TextView>(R.id.strHomeScore)
        private val awayScore = view.findViewById<TextView>(R.id.strAwayScore)

        //@RequiresApi(Build.VERSION_CODES.0)
        fun bindItem(events: EventsItem){
            val formatDate = SimpleDateFormat("yyy-MM-dd", Locale.getDefault())
            val date = formatDate.parse(events.dateEvent)
            val dateText = SimpleDateFormat("EEEE, dd-MM-yyyy", Locale.getDefault())
                .format(date).toString()
            dateEvents.text = dateText
            strAway.text = events.strAwayTeam
            strHome.text = events.strHomeTeam
            homeScore.text = events.intHomeScore
            awayScore.text = events.intAwayScore


        }

    }
}