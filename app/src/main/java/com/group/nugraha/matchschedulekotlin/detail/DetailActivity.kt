package com.group.nugraha.matchschedulekotlin.detail

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.ProgressBar
import com.google.gson.Gson
import com.group.nugraha.matchschedulekotlin.R
import com.group.nugraha.matchschedulekotlin.R.drawable.ic_add_to_favorites
import com.group.nugraha.matchschedulekotlin.R.drawable.ic_added_to_favorites
import com.group.nugraha.matchschedulekotlin.R.id.add_to_favorite
import com.group.nugraha.matchschedulekotlin.R.menu.detail_menu
import com.group.nugraha.matchschedulekotlin.api.ApiRepository
import com.group.nugraha.matchschedulekotlin.db.Preferred
import com.group.nugraha.matchschedulekotlin.db.database
import com.group.nugraha.matchschedulekotlin.model.EventsItem
import com.group.nugraha.matchschedulekotlin.model.TeamsItem
import com.group.nugraha.matchschedulekotlin.util.invisible
import com.group.nugraha.matchschedulekotlin.util.visible
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layoutdetail.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar

class DetailActivity : AppCompatActivity(), DetailView {
    private var idEvent: String = ""
    var idTandang: String = ""
    var idKandang: String = ""
    var nameKandang: String = ""
    var nameTandang: String = ""

    private lateinit var progresBar: ProgressBar
    private lateinit var presenternd: DetailPresenter
    private lateinit var events: EventsItem

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

    //for submission 3

    private var menuItem: Menu? = null
    private var isPreferred: Boolean = false



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(detail_menu, menu)
        menuItem = menu
        setPreferred()
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }

            add_to_favorite -> {
                if (isPreferred) removeFromPreferred() else addToPreferred()
                isPreferred = !isPreferred
                setPreferred()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun preferredState(){
        database.use {
            val result = select(Preferred.TABLE_PREFERRED)
                .whereArgs("(ID_EVENT = {id})",
                    "id" to idEvent)
            val mypreferred = result.parseList(classParser<Preferred>())
            if (!mypreferred.isEmpty()) isPreferred = true
        }
    }

    private fun addToPreferred(){
        try {
            database.use {
                insert(Preferred.TABLE_PREFERRED,
                    Preferred.ID_EVENT to events.idEvent,
                    Preferred.DATE_EVENT to events.dateEvent,
                    Preferred.ID_HOME to events.idHomeTeam,
                    Preferred.HOME_NAME to events.strHomeTeam,
                    Preferred.HOME_SCORE to events.intHomeScore,
                    Preferred.ID_AWAY to events.idAwayTeam,
                    Preferred.AWAY_SCORE to events.intAwayScore,
                    Preferred.AWAY_NAME to events.strAwayTeam)
            }
            progresBar.snackbar("Added to Preferred").show()
        } catch (e: SQLiteConstraintException){
            progresBar.snackbar(e.localizedMessage).show()
        }
    }

    private fun removeFromPreferred(){
        try {
            database.use {
                delete(Preferred.TABLE_PREFERRED,
                    "(ID_EVENT = {id})",
                    "id" to idEvent )
            }
            progresBar.snackbar("Removed from Preferred").show()
        } catch (e: SQLiteConstraintException){
            progresBar.snackbar(e.localizedMessage).show()
        }
    }

    private fun setPreferred() {
        if (isPreferred)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_add_to_favorites)
    }

}