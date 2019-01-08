package com.group.nugraha.matchschedulekotlin.lastmatch

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.ProgressBar
import com.google.gson.Gson
import com.group.nugraha.matchschedulekotlin.R
import com.group.nugraha.matchschedulekotlin.api.ApiRepository
import com.group.nugraha.matchschedulekotlin.model.EventsItem
import com.group.nugraha.matchschedulekotlin.model.TeamsItem
import com.group.nugraha.matchschedulekotlin.util.invisible
import com.group.nugraha.matchschedulekotlin.util.visible

class LastActivity : AppCompatActivity(), LastView {

    private var eventsLast: MutableList<EventsItem> = mutableListOf()

    private lateinit var presenter: LastPresenter
    private lateinit var nAdapter: LastAdapter
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var recyclerViewEvent: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layouteventlast)

        progressBar = findViewById(R.id.pgBarLast)
        swipeRefresh =findViewById(R.id.swipeEventLast)
        recyclerViewEvent = findViewById(R.id.rvEventLast)

        val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(this, 1)
        recyclerViewEvent.layoutManager = layoutManager
        nAdapter = LastAdapter(this, eventsLast)
        recyclerViewEvent.adapter = nAdapter

        val apiRepository = ApiRepository()
        val gson = Gson()
        presenter = LastPresenter(this, apiRepository, gson)
        presenter.getEventLast(LastView.nomor_liga)
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLOading() {
        progressBar.invisible()
    }

    override fun showEventList(data: List<EventsItem>) {
        swipeRefresh.isRefreshing = false
        eventsLast.clear()
        eventsLast.addAll(data)
        nAdapter.notifyDataSetChanged()
    }

    override fun showTeamList(data: List<TeamsItem>?) {
    }
}