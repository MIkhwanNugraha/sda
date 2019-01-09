package com.group.nugraha.matchschedulekotlin.lastmatch

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.group.nugraha.matchschedulekotlin.R
import com.group.nugraha.matchschedulekotlin.model.EventsItem
import com.group.nugraha.matchschedulekotlin.detail.DetailActivity
import org.jetbrains.anko.startActivity
import java.text.SimpleDateFormat
import java.util.*

class LastAdapter (private val context: Context?, private val events: List<EventsItem>)
    : RecyclerView.Adapter<LastAdapter.LastHolder>(){

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int) =
        LastHolder(LayoutInflater.from(context).inflate(R.layout.list_item, p0, false))

    override fun getItemCount(): Int {
        return events.size
    }

    override fun onBindViewHolder(p0: LastHolder, p1: Int) {
        p0.bindItem(events [p1])
        p0.itemView.setOnClickListener{
            context?.startActivity<DetailActivity>(
                DetailActivity.ID_EVENTS to events[p1].idEvent,
                DetailActivity.ID_KANDANG to events[p1].idHomeTeam,
                DetailActivity.ID_TANDANG to events[p1].idAwayTeam,
                DetailActivity.KANDANG_NAME to events[p1].strHomeTeam,
                DetailActivity.TANDANG_NAME to events[p1].strAwayTeam)
        }
    }

    class LastHolder(view: View) : RecyclerView.ViewHolder(view){
        private val dateEvents = view.findViewById<TextView>(R.id.teks_dateEvent)
        private val strAway = view.findViewById<TextView>(R.id.strAwayTeam)
        private val strHome = view.findViewById<TextView>(R.id.strHomeTeam)
        private val homeScore = view.findViewById<TextView>(R.id.strHomeScore)
        private val awayScore = view.findViewById<TextView>(R.id.strAwayScore)

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