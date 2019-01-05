package com.group.nugraha.matchschedulekotlin.nextmatch

import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity
import android.widget.ProgressBar
import com.google.gson.Gson
import com.group.nugraha.matchschedulekotlin.R
import com.group.nugraha.matchschedulekotlin.api.ApiRepository
import com.group.nugraha.matchschedulekotlin.model.EventsItem
import com.group.nugraha.matchschedulekotlin.model.TeamsItem
import com.group.nugraha.matchschedulekotlin.util.invisible
import com.group.nugraha.matchschedulekotlin.util.visible
import kotlinx.android.synthetic.main.layoutnextdetail.*

class NextDetailActivity : AppCompatActivity(), NextView {
    var idEvent: String = ""
    var idTandang: String = ""
    var idKandang: String = ""
    var nameKandang: String = ""
    var nameTandang: String = ""

    private lateinit var progresBar: ProgressBar
    private lateinit var presenternd: NextDetailPresenter
    private lateinit var events: EventsItem
    private lateinit var teams: TeamsItem

    companion object {
        const val ID_EVENTS = "id_events"
        const val ID_TANDANG = "id_Tandang"
        const val ID_KANDANG = "id_Kandang"
        const val KANDANG_NAME = "kandang_name"
        const val TANDANG_NAME = "tandang_name"
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

        val request = ApiRepository()
        val gson = Gson()
        presenternd = NextDetailPresenter(this, request, gson)
        presenternd.getLookUpEvents(idEvent)

        PenempelSimbol().loadSimbol(idKandang, img_home)
        PenempelSimbol().loadSimbol(idTandang, img_away)
    }

    override fun hideLOading() {
        progresBar.invisible()
    }

    override fun showEventList(data: List<EventsItem>) {
        events = data[0]

        txt_home_name_club.text = nameKandang
        txt_away_name_club.text = nameTandang

        txt_home_defense.text = events.strHomeLineupDefense?.replace(";", "\n")
        txt_away_defense.text = events.strAwayLineupDefense?.replace(";", "\n")

        txt_home_formation.text = events.strHomeFormation?.replace(";", "\n")
        txt_away_formation.text = events.strAwayFormation?.replace(";", "\n")
    }

    override fun showLoading() {
        progresBar.visible()
    }


    override fun showTeamList(data: List<TeamsItem>?) {

    }
}