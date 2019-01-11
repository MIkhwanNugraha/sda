package com.group.nugraha.matchschedulekotlin.detail

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.ProgressBar
import com.google.gson.Gson
import com.group.nugraha.matchschedulekotlin.R
import com.group.nugraha.matchschedulekotlin.R.id.add_to_favorite
import com.group.nugraha.matchschedulekotlin.R.menu.detail_menu
import com.group.nugraha.matchschedulekotlin.api.ApiRepository
import com.group.nugraha.matchschedulekotlin.model.EventsItem
import com.group.nugraha.matchschedulekotlin.model.TeamsItem
import com.group.nugraha.matchschedulekotlin.util.invisible
import com.group.nugraha.matchschedulekotlin.util.visible
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layoutdetail.*

class DetailActivity : AppCompatActivity(), DetailView {
    var idEvent: String = ""
    var idTandang: String = ""
    var idKandang: String = ""
    var nameKandang: String = ""
    var nameTandang: String = ""

    private lateinit var progresBar: ProgressBar
    private lateinit var presenternd: DetailPresenter

    companion object {
        const val ID_EVENTS = "id_events"
        const val ID_TANDANG = "id_Tandang"
        const val ID_KANDANG = "id_Kandang"
        const val KANDANG_NAME = "kandang_name"
        const val TANDANG_NAME = "tandang_name"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layoutdetail)

        progresBar = findViewById(R.id.pg_bar)

        idEvent = intent.getStringExtra(ID_EVENTS)
        idTandang = intent.getStringExtra(ID_TANDANG)
        idKandang = intent.getStringExtra(ID_KANDANG)
        nameKandang = intent.getStringExtra(KANDANG_NAME)
        nameTandang = intent.getStringExtra(TANDANG_NAME)

        supportActionBar?.title = "This is Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val request = ApiRepository()
        val gson = Gson()
        presenternd = DetailPresenter(this, request, gson)
        presenternd.getEventDetail(idEvent, idKandang, idTandang)

        preferredState()
    }

    override fun sembunyikanLoading() {
        progresBar.invisible()
    }

    override fun showDetail(matchDetails: List<EventsItem>, homeTeams: List<TeamsItem>, awayTeams: List<TeamsItem>) {
        val events = matchDetails[0]
        val homeTeamhh = homeTeams[0]
        val awayTeam = awayTeams[0]

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

    private var menuItem: Menu? = null
    private var isPreferred: Boolean = false

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(detail_menu, menu)
        menuItem = menu
        setPreferred()
        return true
    }

    Override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }

            add_to_favorite -> {
                if (isPreferred) removeFromFavorite() else addToFavorite()
                isPreferred = !isPreferred
                setPreferred()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}