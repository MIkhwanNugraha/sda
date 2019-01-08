package com.group.nugraha.matchschedulekotlin.lastmatch

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.group.nugraha.matchschedulekotlin.R
import com.group.nugraha.matchschedulekotlin.model.EventsItem
import com.group.nugraha.matchschedulekotlin.nextmatch.detail.NextDetailActivity
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

    //@RequiresApi(Build.VERSION_CODES.0)
    override fun onBindViewHolder(p0: LastHolder, p1: Int) {
        p0.bindItem(events [p1])
        p0.itemView.setOnClickListener{
            context?.startActivity<NextDetailActivity>(
                NextDetailActivity.ID_EVENTS to events[p1].idEvent,
                NextDetailActivity.ID_KANDANG to events[p1].idHomeTeam,
                NextDetailActivity.ID_TANDANG to events[p1].idAwayTeam,
                NextDetailActivity.KANDANG_NAME to events[p1].strHomeTeam,
                NextDetailActivity.TANDANG_NAME to events[p1].strAwayTeam
                //NextDetailActivity.TANDANG_SUBTITUTE to events[p1].strAwayLineupSubtitutes
            )
        }
    }

    class LastHolder(view: View) : RecyclerView.ViewHolder(view){
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