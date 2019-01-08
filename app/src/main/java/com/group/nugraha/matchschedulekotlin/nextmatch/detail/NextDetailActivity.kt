package com.group.nugraha.matchschedulekotlin.nextmatch.detail

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ProgressBar
import com.google.gson.Gson
import com.group.nugraha.matchschedulekotlin.R
import com.group.nugraha.matchschedulekotlin.api.ApiRepository
import com.group.nugraha.matchschedulekotlin.model.EventsItem
import com.group.nugraha.matchschedulekotlin.model.TeamsItem
import com.group.nugraha.matchschedulekotlin.nextmatch.NextView
import com.group.nugraha.matchschedulekotlin.util.invisible
import com.group.nugraha.matchschedulekotlin.util.visible
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layoutnextdetail.*

class NextDetailActivity : AppCompatActivity(), NextDetailView {
    var idEvent: String = ""
    var idTandang: String = ""
    var idKandang: String = ""
    var nameKandang: String = ""
    var nameTandang: String = ""
    //var subtituteTandang: String =""

    private lateinit var progresBar: ProgressBar
    private lateinit var presenternd: NextDetailPresenter
    private lateinit var event: EventsItem
    private lateinit var teams: TeamsItem

    companion object {
        const val ID_EVENTS = "id_events"
        const val ID_TANDANG = "id_Tandang"
        const val ID_KANDANG = "id_Kandang"
        const val KANDANG_NAME = "kandang_name"
        const val TANDANG_NAME = "tandang_name"
        //const val TANDANG_SUBTITUTE = "tandang_subtitute"
    }

    //@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layoutnextdetail)

        progresBar = findViewById(R.id.pg_bar)

        val intent = intent
        idEvent = intent.getStringExtra(ID_EVENTS)
        idTandang = intent.getStringExtra(ID_TANDANG)
        idKandang = intent.getStringExtra(ID_KANDANG)
        nameKandang = intent.getStringExtra(KANDANG_NAME)
        nameTandang = intent.getStringExtra(TANDANG_NAME)
        //subtituteTandang = intent.getStringExtra(TANDANG_SUBTITUTE)

        val request = ApiRepository()
        val gson = Gson()
        presenternd = NextDetailPresenter(this, request, gson)
        presenternd.getEventDetail(idEvent, idKandang, idTandang)


    }

    override fun sembunyikanLoading() {
        progresBar.invisible()
    }

    override fun showDetail(matchDetails: List<EventsItem>, homeTeams: List<TeamsItem>, awayTeams: List<TeamsItem>) {
        val events = matchDetails.get(0)
        val homeTeamhh = homeTeams.get(0)
        val awayTeam = awayTeams.get(0)

        Picasso.get().load(homeTeamhh.strTeamBadge).into(img_home)
        Picasso.get().load(awayTeam.strTeamBadge).into(img_away)
        teks_home_name_club.text = nameKandang
        teks_away_name_club.text = nameTandang

        teks_home_goals.text = events.strHomeGoalDetails?.replace(";", "\n")
        teks_away_goals.text = events.strAwayGoalsDetails?.replace(";", "\n")

        teks_home_defense.text = events.strHomeLineupDefense?.replace(";", "\n")
        teks_away_defense.text = events.strAwayLineupDefense?.replace(";", "\n")



        teks_away_subtitutes.text = events.strAwayLineupSubtitutes
        teks_home_substitutes.text = events.strHomeLineupSubtitutes

        sembunyikanLoading()
    }

    override fun tunjukkanLoading() {
        progresBar.visible()
    }



}