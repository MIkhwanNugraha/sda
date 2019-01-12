package com.group.nugraha.matchschedulekotlin.preferred

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.group.nugraha.matchschedulekotlin.R
import com.group.nugraha.matchschedulekotlin.db.Preferred
import com.group.nugraha.matchschedulekotlin.detail.DetailActivity
import org.jetbrains.anko.startActivity
import java.text.SimpleDateFormat
import java.util.*

class PreferredAdapter (private val context: Context?, private val preferredz: List<Preferred>)
    : RecyclerView.Adapter<PreferredAdapter.PLastHolder>(){

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int) =
        PLastHolder(LayoutInflater.from(context).inflate(R.layout.list_item, p0, false))

    override fun getItemCount(): Int {
        return preferredz.size
    }

    override fun onBindViewHolder(p0: PLastHolder, p1: Int) {
        p0.bindItem(preferredz [p1])
        p0.itemView.setOnClickListener{
            context?.startActivity<DetailActivity>(
                DetailActivity.ID_EVENTS to preferredz[p1].eventId,
                DetailActivity.ID_KANDANG to preferredz[p1].homeId,
                DetailActivity.ID_TANDANG to preferredz[p1].awayId,
                DetailActivity.KANDANG_NAME to preferredz[p1].homeName,
                DetailActivity.TANDANG_NAME to preferredz[p1].awayName)
        }
    }

    class PLastHolder(view: View) : RecyclerView.ViewHolder(view){
        private val dateEvents = view.findViewById<TextView>(R.id.teks_dateEvent)
        private val strAway = view.findViewById<TextView>(R.id.strAwayTeam)
        private val strHome = view.findViewById<TextView>(R.id.strHomeTeam)
        private val homeScore = view.findViewById<TextView>(R.id.strHomeScore)
        private val awayScore = view.findViewById<TextView>(R.id.strAwayScore)

        fun bindItem(preferredy: Preferred){
            val formatDate = SimpleDateFormat("yyy-MM-dd", Locale.getDefault())
            val date = formatDate.parse(preferredy.eventDate)
            val dateText = SimpleDateFormat("EEEE, dd-MM-yyyy", Locale.getDefault())
                .format(date).toString()
            dateEvents.text = dateText
            strAway.text = preferredy.awayName
            strHome.text = preferredy.homeName
            homeScore.text = preferredy.homeScore
            awayScore.text = preferredy.awayScore
        }
    }
}